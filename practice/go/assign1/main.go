package main

import "fmt"

type contactInfo struct {
	email string
	zip   int
}
type person struct {
	firstName string
	lastName  string
	contact   contactInfo
}

func (p person) print() {
	fmt.Printf("%+v", p)
}

func (p *person) updateName(newFirstName string) {
	p.firstName = newFirstName
}
func main() {
	p := person{
		lastName:  "test",
		firstName: "last",
		contact: contactInfo{
			email: "ts@@",
			zip:   23499,
		},
	}
	p.print()
	p.updateName("jim")
	p.print()

	var alex person
	alex.lastName = "lasthere"
	fmt.Printf("%+v", alex)

}
