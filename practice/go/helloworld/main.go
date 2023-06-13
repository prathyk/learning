package main

import "fmt"

func main() {
	fmt.Println("Hello World?")


}

func notNull(f func(string) *string, rest func(string) *string) *string {
  var value = f("hello")

  if(value != nil){
    return rest(*value)
  }
  return nil
}


func eachResult(f func() []int, rest func(int) int) {

  var l = f()
  for _, num := range l {
    rest(num)
  }

}
