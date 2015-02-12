package me.vrdmr.scala.overview

object overview {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // Type would be inferred. ; optional
  var number = -1                                 //> number  : Int = -1
  // List
  val list = List(1, 2, 3, 4, 5)                  //> list  : List[Int] = List(1, 2, 3, 4, 5)

  // Maps
  val map = Map(1 -> "one", 2 -> "two")           //> map  : scala.collection.immutable.Map[Int,String] = Map(1 -> one, 2 -> two)

  // {} is optional for one liners
  def square(x: Int) = x * x                      //> square: (x: Int)Int

  // higher order function
  list.filter(_ > 2).map(_ * 3).sum               //> res0: Int = 36

  // Casting is easy
  val strAsNum = "1000".toInt                     //> strAsNum  : Int = 1000

  // printing a number. Convenient string ops
  println(strAsNum)                               //> 1000

  // convinient number obs
  number = number.abs; println(number)            //> 1

  // really easy ranges
  val range = 1 until 100 by 2                    //> range  : scala.collection.immutable.Range = Range(1, 3, 5, 7, 9, 11, 13, 15,
                                                  //|  17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53,
                                                  //|  55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91,
                                                  //|  93, 95, 97, 99)
  // Wow. Really easy tuples
  val tuple = ("Tuples", 1, true, "wonderful and surprisingly useful", list)
                                                  //> tuple  : (String, Int, Boolean, String, List[Int]) = (Tuples,1,true,wonderfu
                                                  //| l and surprisingly useful,List(1, 2, 3, 4, 5))

  // convinient collection ops
  val tsil = list.reverse                         //> tsil  : List[Int] = List(5, 4, 3, 2, 1)

  for ((k, v) <- map) println(k, v)               //> (1,one)
                                                  //| (2,two)

  // Nested loops made easy
  val result = for (i <- 0 to 2; j <- 0 to 2) println(i, j)
                                                  //> (0,0)
                                                  //| (0,1)
                                                  //| (0,2)
                                                  //| (1,0)
                                                  //| (1,1)
                                                  //| (1,2)
                                                  //| (2,0)
                                                  //| (2,1)
                                                  //| (2,2)
                                                  //| result  : Unit = ()

  val functionLiteral = (n: Int) => math.sqrt(n) * n
                                                  //> functionLiteral  : Int => Double = <function1>

  // String literals
  val longLiterals = """
   a long "String" :)
   """                                            //> longLiterals  : String = "
                                                  //|    a long "String" :)
                                                  //|    "

  // default value of param
  def lotsOfParam(num: Int = 10, str: String = "N/A") = str * num
                                                  //> lotsOfParam: (num: Int, str: String)String

  lotsOfParam(str = "wat")                        //> res1: String = watwatwatwatwatwatwatwatwatwat

  // partial functions -- WOW!
  def addNumbers(x: Int)(y: Int) = x + y          //> addNumbers: (x: Int)(y: Int)Int
  def add2 = addNumbers(2)_                       //> add2: => Int => Int
  add2(3)                                         //> res2: Int = 5

  // easy interfaces / mixins
  trait Namable { val name: String; def greet: String = s"Hi $name!" }

  // laxy evaluation
  trait Randomable { lazy val rand: Int = (math.random * 100).toInt }

  // easy class definition, support for mixins using traits.
  case class Person(name: String, favoriteLanguage: String) extends Namable with Randomable

  val person: Namable with Randomable = Person("Sanket", "Java")
                                                  //> person  : me.vrdmr.scala.overview.overview.Namable with me.vrdmr.scala.over
                                                  //| view.overview.Randomable = Person(Sanket,Java)

  //Pattern Matching
  person match {
    case p @ Person(n, fl) => println(s"${p.greet} p.s. we like $fl too!" +
      s" Random number: ${p.rand}. (Still ${p.rand})")
    case _ => println("hm...")
  }                                               //> Hi Sanket! p.s. we like Java too! Random number: 94. (Still 94)

  val condition = if (person.name == "Sanket") "Hi Sanket!" else "Superman"
                                                  //> condition  : String = Hi Sanket!

  /* Making an implicit class, which holds some functions,
  and can be used with the varibles where the datatype
  matches the datatype of the variables it would be used on*/
  implicit class SuperInt(val i: Int) {
    def square = i * i
    def sqrt = math.sqrt(i)
  }

  println(7.square, 9.sqrt.toInt)                 //> (49,3)

  /* Making an implicit class, which holds some functions,
  and can be used with the varibles where the datatype
  matches the datatype of the variables it would be used on*/
  implicit class SuperString(val i: String) {
    def lengthMod2 = i.length() % 2
    def presentIn = i.contains("Hello")
  }

  println("Sanket".presentIn, "HelloWorld".presentIn, "Varad".lengthMod2)
                                                  //> (false,true,1)

  def quack(quackable: { def quack: String }) = {
    "What does the duck say? " + quackable.quack
  }                                               //> quack: (quackable: AnyRef{def quack: String})String

  class RealDuck { def quack = "Quack!" }
  class ImposterDuck { def quack = "Qwaaack!" }

  quack(new RealDuck)                             //> res3: String = What does the duck say? Quack!
  quack(new ImposterDuck)                         //> res4: String = What does the duck say? Qwaaack!

  import scala.language.dynamics
  class Useless extends Dynamic {
    def applyDynamic(name: String)(args: Any*) {
      println(s"Sorry, I wish I could $name...")
      if (!args.isEmpty) {
        println(s"Here, you can have your ${args.mkString(", ")} back.")
      }
    }
  }
  val useless = new Useless                       //> useless  : me.vrdmr.scala.overview.overview.Useless = me.vrdmr.scala.overvi
                                                  //| ew.overview$$anonfun$main$1$Useless$1@10c13a99
  useless.reticulate("splines", "marbles")        //> Sorry, I wish I could reticulate...
                                                  //| Here, you can have your splines, marbles back.
}