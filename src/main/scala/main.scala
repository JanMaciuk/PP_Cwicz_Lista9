@main
def main(): Unit = {
  println("Hello world!")
  var p1 = new Pojazd("Ford", "Focus")
  var p2 = new Pojazd("Ford", "Focus", 2010)
  var p3 = new Pojazd("Ford", "Focus", 2010, "DW 12345")

  var t31 = new Time3(1, 5)
  println(t31.getHour)
  println(t31.getMinute)
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

