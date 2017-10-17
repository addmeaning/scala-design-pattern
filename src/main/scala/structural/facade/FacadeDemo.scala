package structural.facade

import java.util.Base64

class DataReader extends DataDownloader with DataDecoder with Deserializer {
  def readPerson: Person = {
    (download _ andThen decode andThen parse)("name")
  }

}

trait DataDownloader {
  def download(url: String): Array[Byte] = url.getBytes
}

trait DataDecoder {
  def decode(data: Array[Byte]): String = new String(Base64.getDecoder.decode(data))
}

trait Deserializer {
  def parse(data: String): Person = Person(data)
}

case class Person(name: String)