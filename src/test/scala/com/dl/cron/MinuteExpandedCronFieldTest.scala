package com.dl.cron

import org.scalatest.{FlatSpec, Matchers}

class MinuteExpandedCronFieldTest extends FlatSpec with Matchers {

  behavior of classOf[MinuteExpandedCronField].getSimpleName

  private val allValues = List(
    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
    "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
    "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
    "51", "52", "53", "54", "55", "56", "57", "58", "59")


  it should "display cron field name" in {
    new MinuteExpandedCronField("").cronFieldName shouldBe "minute"
  }

  it should "convert * to all values" in {
    new MinuteExpandedCronField("*").expandedValue() shouldBe allValues.mkString(" ")
  }

  it should "convert separate values (with ,) to separate values" in {
    new MinuteExpandedCronField("1,6,43,59").expandedValue() shouldBe "1 6 43 59"
  }

  it should "not convert separate values (with ,) to separate values with unknown values" in {
    an[IllegalArgumentException] should be thrownBy new MinuteExpandedCronField("73, 2").expandedValue()
  }

  it should "not convert separate values (with ,) to separate values when having last comma" in {
    an[IllegalArgumentException] should be thrownBy new MinuteExpandedCronField("10,").expandedValue()
  }

  it should "convert range (-) to a range of values" in {
    new MinuteExpandedCronField("9-12").expandedValue() shouldBe "9 10 11 12"
  }

  it should "convert range with a step" in {
    new MinuteExpandedCronField("0-12/3").expandedValue() shouldBe "0 3 6 9 12"
  }

  it should "return all acceptable values for field" in {
    new MinuteExpandedCronField("").allValues shouldBe allValues
  }
}
