package hackerrank.functionalprogramming.introduction

import scala.io.StdIn

object HackerrankHelloWorldN extends App {
 def f(n: Int) = for (a <- 1 to n) {println("Hello World")}

var n = StdIn.readInt()
  f(n)
}