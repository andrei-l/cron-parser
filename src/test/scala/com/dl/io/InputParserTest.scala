package com.dl.io

import org.scalatest.{FlatSpec, Matchers}

class InputParserTest extends FlatSpec with Matchers {
  behavior of classOf[InputParser].getSimpleName

  it should "fail on more that one argument" in {
    an[IllegalArgumentException] should be thrownBy new InputParser(Array("1", "2"))
  }

  it should "fail on a single argument which doesn't have valid input" in {
    an[IllegalArgumentException] should be thrownBy new InputParser(Array("1"))
  }

  it should "successfully process a single argument with a valid input" in {
    val inputParser = new InputParser(Array("""*/15 0 1,15 * 1-5 /usr/bin/find"""))

    inputParser.command shouldBe "/usr/bin/find"
  }
}
