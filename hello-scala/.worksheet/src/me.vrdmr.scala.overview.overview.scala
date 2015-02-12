package me.vrdmr.scala.overview

object overview {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(94); 
  println("Welcome to the Scala worksheet");$skip(59); 

  // Type would be inferred. ; optional
  var number = -1;System.out.println("""number  : Int = """ + $show(number ));$skip(43); 
  // List
  val list = List(1, 2, 3, 4, 5);System.out.println("""list  : List[Int] = """ + $show(list ));$skip(51); 

  // Maps
  val map = Map(1 -> "one", 2 -> "two");System.out.println("""map  : scala.collection.immutable.Map[Int,String] = """ + $show(map ));$skip(65); 

  // {} is optional for one liners
  def square(x: Int) = x * x;System.out.println("""square: (x: Int)Int""");$skip(64); val res$0 = 

  // higher order function
  list.filter(_ > 2).map(_ * 3).sum;System.out.println("""res0: Int = """ + $show(res$0));$skip(52); 

  // Casting is easy
  val strAsNum = "1000".toInt;System.out.println("""strAsNum  : Int = """ + $show(strAsNum ));$skip(67); 

  // printing a number. Convenient string ops
  println(strAsNum);$skip(67); 

  // convinient number obs
  number = number.abs; println(number);$skip(56); 

  // really easy ranges
  val range = 1 until 100 by 2;System.out.println("""range  : scala.collection.immutable.Range = """ + $show(range ));$skip(106); 
  // Wow. Really easy tuples
  val tuple = ("Tuples", 1, true, "wonderful and surprisingly useful", list);System.out.println("""tuple  : (String, Int, Boolean, String, List[Int]) = """ + $show(tuple ));$skip(58); 

  // convinient collection ops
  val tsil = list.reverse;System.out.println("""tsil  : List[Int] = """ + $show(tsil ));$skip(37); 

  for ((k, v) <- map) println(k, v);$skip(89); 

  // Nested loops made easy
  val result = for (i <- 0 to 2; j <- 0 to 2) println(i, j);System.out.println("""result  : Unit = """ + $show(result ));$skip(54); 

  val functionLiteral = (n: Int) => math.sqrt(n) * n;System.out.println("""functionLiteral  : Int => Double = """ + $show(functionLiteral ));$skip(76); 

  // String literals
  val longLiterals = """
   a long "String" :)
   """;System.out.println("""longLiterals  : String = """ + $show(longLiterals ));$skip(95); 

  // default value of param
  def lotsOfParam(num: Int = 10, str: String = "N/A") = str * num;System.out.println("""lotsOfParam: (num: Int, str: String)String""");$skip(28); val res$1 = 

  lotsOfParam(str = "wat");System.out.println("""res1: String = """ + $show(res$1));$skip(73); 

  // partial functions -- WOW!
  def addNumbers(x: Int)(y: Int) = x + y;System.out.println("""addNumbers: (x: Int)(y: Int)Int""");$skip(28); 
  def add2 = addNumbers(2)_;System.out.println("""add2: => Int => Int""");$skip(10); val res$2 = 
  add2(3)

  // easy interfaces / mixins
  trait Namable { val name: String; def greet: String = s"Hi $name!" }

  // laxy evaluation
  trait Randomable { lazy val rand: Int = (math.random * 100).toInt }

  // easy class definition, support for mixins using traits.
  case class Person(name: String, favoriteLanguage: String) extends Namable with Randomable;System.out.println("""res2: Int = """ + $show(res$2));$skip(414); 

  val person: Namable with Randomable = Person("Sanket", "Java");System.out.println("""person  : me.vrdmr.scala.overview.overview.Namable with me.vrdmr.scala.overview.overview.Randomable = """ + $show(person ));$skip(205); 

  //Pattern Matching
  person match {
    case p @ Person(n, fl) => println(s"${p.greet} p.s. we like $fl too!" +
      s" Random number: ${p.rand}. (Still ${p.rand})")
    case _ => println("hm...")
  };$skip(77); 

  val condition = if (person.name == "Sanket") "Hi Sanket!" else "Superman"

  /* Making an implicit class, which holds some functions,
  and can be used with the varibles where the datatype
  matches the datatype of the variables it would be used on*/
  implicit class SuperInt(val i: Int) {
    def square = i * i
    def sqrt = math.sqrt(i)
  };System.out.println("""condition  : String = """ + $show(condition ));$skip(307); 

  println(7.square, 9.sqrt.toInt)

  /* Making an implicit class, which holds some functions,
  and can be used with the varibles where the datatype
  matches the datatype of the variables it would be used on*/
  implicit class SuperString(val i: String) {
    def lengthMod2 = i.length() % 2
    def presentIn = i.contains("Hello")
  };$skip(378); 

  println("Sanket".presentIn, "HelloWorld".presentIn, "Varad".lengthMod2);$skip(104); 

  def quack(quackable: { def quack: String }) = {
    "What does the duck say? " + quackable.quack
  }

  class RealDuck { def quack = "Quack!" }
  class ImposterDuck { def quack = "Qwaaack!" };System.out.println("""quack: (quackable: AnyRef{def quack: String})String""");$skip(114); val res$3 = 

  quack(new RealDuck);System.out.println("""res3: String = """ + $show(res$3));$skip(26); val res$4 = 
  quack(new ImposterDuck)

  import scala.language.dynamics
  class Useless extends Dynamic {
    def applyDynamic(name: String)(args: Any*) {
      println(s"Sorry, I wish I could $name...")
      if (!args.isEmpty) {
        println(s"Here, you can have your ${args.mkString(", ")} back.")
      }
    }
  };System.out.println("""res4: String = """ + $show(res$4));$skip(312); 
  val useless = new Useless;System.out.println("""useless  : me.vrdmr.scala.overview.overview.Useless = """ + $show(useless ));$skip(43); 
  useless.reticulate("splines", "marbles")}
}
