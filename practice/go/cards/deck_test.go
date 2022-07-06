package main

import (
	"fmt"
	"os"
	"reflect"
	"testing"
)

func Test_deck_Print(t *testing.T) {
	tests := []struct {
		name string
		d    deck
	}{}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			tt.d.Print()
		})
	}
}

func Test_newDeck(t *testing.T) {
	got := newDeck()
	t.Log(fmt.Println(got[0]))
	if len(got) != 16 {
		t.Errorf("newDeck() = %v, want %v", len(got), 16)
	}

}

func Test_SaveToFileAndRecreateFromIt(t *testing.T) {
	pig := newDeck()
	os.Remove("_decktesting")
	pig.saveToFile("_decktesting")

	loadedPig := newDeckFromFile("_decktesting")

	if !reflect.DeepEqual(pig, loadedPig) {
		t.Errorf("Failed to equal")
	}
}
