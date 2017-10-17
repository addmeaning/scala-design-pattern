package structural.decorator.scalalike

import java.io.{BufferedInputStream, BufferedReader, ByteArrayOutputStream, InputStreamReader}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import scala.collection.JavaConverters._

trait InputReader {
  def readLines(): Stream[String]
}

trait CompressingInputReaderTrait extends InputReader {
  abstract override def readLines(): Stream[String] = super.readLines().map {
    line =>
      val text = line.getBytes(Charset.forName("UTF-8"))
      val output = new ByteArrayOutputStream()
      val compressor = new GZIPOutputStream(output)
      try {
        compressor.write(text, 0, text.length)
        val outputByteArray = output.toByteArray
        new String(outputByteArray, Charset.forName("UTF-8"))
      } finally {
        compressor.close()
        output.close()
      }
  }
}
trait CapitalizedInputReaderTrait extends InputReader {
  abstract override def readLines(): Stream[String] = super.readLines().map(_.toUpperCase)
}


class AdvancedInputReader(reader: BufferedReader) extends InputReader {
  override def readLines(): Stream[String] = reader.lines().iterator().asScala.toStream
}


trait Base64EncoderInputReaderTrait extends InputReader {
  abstract override def readLines(): Stream[String] = super.readLines().map(line => Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8"))))
}

object StackableTraitsExample {
  def main(args: Array[String]): Unit = {
    val stream = new BufferedReader(
      new InputStreamReader(
        new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))
      )
    )
    try {
      val reader = new AdvancedInputReader(stream) with CapitalizedInputReaderTrait with Base64EncoderInputReaderTrait with CompressingInputReaderTrait
      reader.readLines().foreach(println)
    } finally {
      stream.close()
    }
  }
}
