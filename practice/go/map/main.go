package main

import "fmt"

type bot interface{
	getGreeting() string
}
type englishBot struct{}
type spanishBot struct{}

func (e englishBot) getGreeting() string {
	return "Hello there"
}

func (e spanishBot) getGreeting() string {
	return "Hello spain"
}

func printGreeting(b bot) {
	fmt.Println(b.getGreeting())
}

func main() {
	b := englishBot{}
	sb := spanishBot{}
	printGreeting(b)
	printGreeting(sb)
}
