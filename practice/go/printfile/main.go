package main

import (
	"fmt"
	"io"
	"os"
)

func main() {
	fileName := os.Args[1]

	file, err := os.Open(fileName)
	if err != nil {
		fmt.Println("Error: ", err)
	}

	io.Copy(os.Stdout, file)
}
