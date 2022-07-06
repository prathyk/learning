package main

import (
	"fmt"
	"net/http"
	"time"
)

func main() {
	urls := []string{
		"http://google.com",
		"http://facebook.com",
		"http://stackoverflow.com",
		"http://golang.org",
	}

	c := make(chan string)

	for _, link := range urls {
		go checkLink(link, c)
	}

	for l := range c {
		go func(lin string) {
			time.Sleep(5 * time.Second)
			checkLink(lin, c)
		}(l)
	}

}

func checkLink(link string, c chan string) {
	r, e := http.Get(link)
	if e != nil {
		fmt.Println("link failed: ", link)
		c <- link
		return
	}
	if r.StatusCode != 200 {
		fmt.Println("link failed: ", link, r.StatusCode)
		c <- link
		return
	}
	fmt.Println("link ok", link)
	c <- link
}
