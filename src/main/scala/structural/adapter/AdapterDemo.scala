package structural.adapter
import structural.adapter.Helpers._


trait Log {
  def info(message: String)

  def debug(message: String)

  def warning(message: String)

  def error(message: String)
}

class Logger {
  def log(message: String, severity: String): Unit = println(s"${severity.toUpperCase}: $message")
}



class FinalLogger extends Logger

object AdapterImplicitExample {

  def main(args: Array[String]): Unit = {

    val logger = new FinalLogger
    logger.info("This is an info message.")
    logger.debug("Debug something here.")
    logger.error("Show an error message.")
    logger.warning("About to finish.")
    logger.info("Bye!")
  }
}