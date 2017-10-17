/**
  * description: 
  * link to test/main class:
  */

import org.scalatest.{FlatSpec, Matchers}

class DoubledMultiplierIdentityTest extends FlatSpec with Matchers {

  class DoubleMultiplierIdentityClass extends DoubledMultiplierIdentity

  val instance = new DoubleMultiplierIdentityClass

  "identity" should "return 2 * 1" in {
    instance.identity should equal(2)
  }
}