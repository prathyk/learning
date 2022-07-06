module hello

go 1.15

replace example.com/greetings => ../greetings

require (
	example.com/greetings v0.0.0-00010101000000-000000000000
	golang.org/x/tour v0.0.0-20201207214521-004403599411 // indirect
)
