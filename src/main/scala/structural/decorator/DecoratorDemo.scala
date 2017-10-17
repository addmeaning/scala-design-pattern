package structural.decorator

import java.io.{BufferedReader, ByteArrayOutputStream}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import scala.collection.JavaConverters._

trait InputReader {
  def readLines(): Stream[String]
}

class AdvancedInputReader(reader: BufferedReader) extends InputReader {
  override def readLines(): Stream[String] = reader.lines().iterator().asScala.toStream
}

abstract class InputReaderDecorator(inputReader: InputReader) extends InputReader {
  override def readLines(): Stream[String] = inputReader.readLines()
}
class CapitalizedInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {
  override def readLines(): Stream[String] = super.readLines().map(_.toUpperCase)
}

class CompressingInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {
  override def readLines(): Stream[String] = super.readLines().map {
    line =>
      val text = line.getBytes(Charset.forName("UTF-8"))
      println(s"Length before compression: ${text.length.toString}")
      val output = new ByteArrayOutputStream()
      val compressor = new GZIPOutputStream(output)
      try {
        compressor.write(text, 0, text.length)
        val outputByteArray = output.toByteArray
        println(s"Length after compression: ${outputByteArray.length.toString}")
        new String(outputByteArray, Charset.forName("UTF-8"))
      } finally {
        compressor.close()
        output.close()
      }
  }
}


class Base64EncoderInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {
  override def readLines(): Stream[String] = super.readLines().map(line => Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8"))))
}