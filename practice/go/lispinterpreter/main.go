package main

import (
	"bytes"
	"encoding/json"
)

func main() {

	myJsonString := `[
		"do",
		[
		  "def",
		  "drawTriangle",
		  [
			"fn",
			["left", "top", "right", "color"],
			[
			  "do",
			  ["drawLine", "left", "top", "color"],
			  ["drawLine", "top", "right", "color"],
			  ["drawLine", "left", "right", "color"]
			]
		  ]
		],
		["drawTriangle", "one", "two", "thr", "blue"],
		["drawTriangle", "a", "b", "c", "purple"]
	  ]
	  `

	execute(myJsonString)
}

type Instruction struct {
	FuncName string   `json:"funcName"`
	Args     []string `json:"args"`
}

var fns = map[string]interface{}{
	"drawLine": drawLine,
	"drawDot":  drawDot,
	"def":      def,
	"do":       do}
var vars = make(map[string]interface{})

func execute(myJsonString string) {
	var result []interface{}
	json.Unmarshal([]byte(myJsonString), &result)

	println(parseIns(result, vars).(string))
}

func mapArgsWithValues(args []interface{}, values []interface{}) map[string]interface{} {
	var result = make(map[string]interface{})
	for k, v := range args {
		result[v.(string)] = values[k]
	}
	return result
}

func parseFnIns(args []interface{}, body interface{}, oldVars map[string]interface{}) interface{} {
	return func(values ...interface{}) interface{} {
		var newVars = make(map[string]interface{})
		for k, v := range oldVars {
			newVars[k] = v
		}
		argVals := mapArgsWithValues(args, values)
		for k, v := range argVals {
			newVars[k] = v
		}
		return parseIns(body, newVars)
	}
}

func parseIns(ins interface{}, vars map[string]interface{}) interface{} {

	switch x := ins.(type) {
	case string:
		if val, ok := vars[x]; ok {
			return val
		}
		return ins
	case []interface{}:
		name, args := x[0].(string), x[1:]
		if name == "fn" {
			return parseFnIns(args[0].([]interface{}), args[1], vars)
		}
		argsR := make([]interface{}, len(args))
		for i, v := range args {
			argsR[i] = parseIns(v, vars)
		}
		return callFunc(name, argsR, vars)
	default:
		return ins
	}
}

func callFunc(funcName string, args []interface{}, vars map[string]interface{}) interface{} {
	var funct func(...interface{}) interface{}
	if _, ok := fns[funcName]; ok {
		funct = fns[funcName].(func(...interface{}) interface{})
	} else {
		funct = vars[funcName].(func(...interface{}) interface{})
	}
	return funct(args...)
}

func do(args ...interface{}) interface{} {
	return args[len(args)-1]
}

func def(args ...interface{}) interface{} {
	vars[args[0].(string)] = args[1]
	return nil
}
func drawLine(args ...interface{}) interface{} {
	var b bytes.Buffer
	b.WriteString("drawLine(")
	for _, v := range args {
		b.WriteString(v.(string))
		b.WriteString(",")
	}
	b.WriteString(")")
	ret := b.String()
	println(ret)
	return ret
}
func drawDot(args ...interface{}) interface{} {
	var b bytes.Buffer
	b.WriteString("drawDot(")
	for _, v := range args {
		b.WriteString(v.(string))
		b.WriteString(",")
	}
	b.WriteString(")")
	return b.String()
}
