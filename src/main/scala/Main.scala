import cats.implicits._
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

@main def hello: Unit =
  println("Hello world!")
  println(msg)
  performCombine()
  performJson()

def msg = "I was compiled by Scala 3. :)"

def performCombine(): Unit =
  val xs: Seq[Int] = Seq(1, 2, 3, 4, 5)
  val ys: Seq[Int] = Seq(6, 7, 8, 9, 10)
  println((xs |+| ys).mkString(", "))

case class JsonObject(foo: Int, bar: Double, buzz: String)
def performJson(): Unit =
  val jsonString = """{
"foo": 42,
"bar": 3.14,
"buzz": "piyo"
}"""
  val parsed: Either[io.circe.Error, JsonObject] =
    decode[JsonObject](jsonString)
  println(parsed)
