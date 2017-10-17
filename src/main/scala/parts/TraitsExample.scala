
trait Timer {
 def getTime() : String = System.currentTimeMillis().toString
 def setTime(time: String)
}
trait Watcher{
  self : Timer =>

  def elapsed(time: String) = getTime() + time
}
