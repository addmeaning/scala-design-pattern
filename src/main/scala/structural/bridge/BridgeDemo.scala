package structural.bridge

import java.security.MessageDigest


trait PasswordConverter {
  def convert(password: String): String
}

class SaltedPasswordConverter(val salt: String) extends PasswordConverter {
  self: Hasher =>
  override def convert(password: String): String = hash(s"$salt:$password")
}

class SimplePasswordConverter extends PasswordConverter {
  self: Hasher =>
  override def convert(password: String): String = hash(password)
}

trait Hasher {
  def hash(data: String): String

  protected def getDigest(algorithm: String, data: String): MessageDigest = {
    val crypt = MessageDigest.getInstance(algorithm)
    crypt.reset()
    crypt.update(data.getBytes)
    crypt
  }
}


class Sha256Hasher extends Hasher {
  override def hash(data: String): String = new String(getDigest("SHA-256", data).digest())
}

class Md5Hasher extends Hasher {
  override def hash(data: String): String = new String(getDigest("MD5", data).digest())
}

class Sha1Hasher extends Hasher {
  override def hash(data: String): String = new String(getDigest("SHA-1", data).digest())
}
