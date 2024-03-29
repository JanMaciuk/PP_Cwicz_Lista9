import scala.collection.mutable
import scala.collection.mutable.Seq
@main
def main(): Unit = {
  println("Hello world!")
  val p1 = new Pojazd("Ford", "Focus")
  val p2 = new Pojazd("Ford", "Focus", 2010)
  val p3 = new Pojazd("Ford", "Focus", 2010, "DW 12345")

  val t31 = new Time3(1, 5)
  println(t31.getHour)
  println(t31.getMinute)

  try {
    metoda1()
  } catch {
    case e: Exception =>
      println("Wyjatek: " + e.getMessage)
      e.printStackTrace()
  }
  /*
  at main$package$.metoda1(main.scala:78)
  at main$package$.main(main.scala:13)
  main$package$ to nazwa pakietu, w którym znajduje się metoda main
  main to nazwa pliku, w którym znajduje się metoda main
  .metoda1 to nazwa metody w której wywołano wyjątek
  13 to numer linii, w której wywołano metodę metoda1

  */

}

class Time1(private var hour: Int):
  if (hour < 0) hour = 0

  def getHour: Int = hour

  def setHour(hour: Int): Unit =
    if (hour < 0) this.hour = 0 else this.hour = hour

end Time1

class Time2(private var hour: Int, private var minute: Int):
  if ((hour < 0) || (minute < 0)) throw new IllegalArgumentException("hour and minute must be positive")
  if ((hour > 23) || (minute > 59)) throw new IllegalArgumentException("hour or minute too large")
  def getHour: Int = hour
  def getMinute: Int = minute
  def setHour(hour: Int): Unit =
    if ((hour < 0) || (hour > 23)) throw new IllegalArgumentException("hour must be between 0 and 23")
    else this.hour = hour
  def setMinute(minute: Int): Unit =
    if ((minute < 0) || (minute > 59)) throw new IllegalArgumentException("minute must be between 0 and 59")
    else this.minute = minute

  def before(other: Time2): Boolean =
    if (hour < other.hour) true
    else if (hour > other.hour) false
    else if (minute < other.minute) true
    else false

end Time2

class Time3(private var hour:Int, private var minute: Int):
  if ((hour < 0) || (minute < 0)) throw new IllegalArgumentException("hour and minute must be positive")
  if ((hour > 23) || (minute > 59)) throw new IllegalArgumentException("hour or minute too large")
  private var minutesSinceMidnight: Int = hour * 60 + minute

  def getHour: Int = minutesSinceMidnight / 60
  def getMinute: Int = minutesSinceMidnight % 60
  def setHour(hour: Int): Unit =
    if ((hour < 0) || (hour > 23)) throw new IllegalArgumentException("hour must be between 0 and 23")
    else
      minutesSinceMidnight = hour * 60 + getMinute

  def setMinute(minute: Int): Unit =
    if ((minute < 0) || (minute > 59)) throw new IllegalArgumentException("minute must be between 0 and 59")
    else
      minutesSinceMidnight = getHour * 60 + minute

    def before(other: Time3): Boolean = minutesSinceMidnight < other.minutesSinceMidnight

end Time3


//Parametry rokProdukcji i rejestracja są opcjonalne bo mają wartości domyślne, nie trzeba ich podawać przy tworzeniu obiektu.
class Pojazd(val producent: String, val model: String, val rokProdukcji: Int = -1, var rejestracja: String = "")

@throws[Exception]
def metoda1(): Unit = {
  metoda2()
}
@throws[Exception]
def metoda2(): Unit = {
  metoda3()
}
@throws[Exception]
def metoda3(): Unit = {
  throw new Exception("Wyjatek zgloszony w metoda3")
}

//Lista 10:

// Class Klasa[+T](var x: T) x jest podtypem T lub T, np: do Klasa[Any] możemy przypisać Klasa[Int]
// Class Klasa[-T](var x: T) T jest podtypem x lub x, np: do Klasa[Int] możemy przypisać Klasa[Any]
/*Zadanie 1:
  class GenericCellMut[+T] (var x: T)
  Covariant type T occurs in contravariant position in type T of value x
  Powyższy kod nie może się skompilować, w takiej klasie możemy zmienić wartość x, co nie jest dopuszczalne, ze względu na poniższy przykład:
  val anyCell: GenericCellMut[Any] = new GenericCellMut[Int](1)
  anyCell.x = "abc"
  val i: Int = anyCell.x
  W tym przykładzie moglibyśmy przypisać do zmiennej wartość innego typu.
  Wersja kontrwariantna GenericCellMut[-T] (var x: T) podobnie nie może się skompilować, np:
  val anyCell: GenericCellMut[Int] = new GenericCellMut[Any]("abc")
  val i: Int = anyCell.x
  Ponownie dochodzimy do niezgodnego typu.
*/

//Zadanie 4:
def copy[T](dest: mutable.Seq[T], src: mutable.Seq[T]): Unit = {
  var i = 0
  while (i < src.size) {
    dest.update(i, src(i))
    i += 1
  }
}

