package main

import "fmt"

func main() {
	cards := newDeck()
	// cards.saveToFile("tempCards")
	// newDeckFromFile("tempCards").Print()
	cards.shuffle()
	cards.Print()
	fmt.Println(cards[1] + "is what")
}
