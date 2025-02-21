package main

import (
	"fmt"
	"github.com/go-vgo/robotgo"
	"time"
)

func main() {

	time.Sleep(2 * time.Second) 
	robotgo.TypeStr("Texto de prueba")


	robotgo.Click(100, 200) 


	fmt.Println("Pruebas completadas")
}