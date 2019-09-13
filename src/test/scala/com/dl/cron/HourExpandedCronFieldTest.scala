package com.dl.cron

import org.scalatest.{FlatSpec, Matchers}

class HourExpandedCronFieldTest extends FlatSpec with Matchers {

  behavior of classOf[HourExpandedCronField].getSimpleName

  private val allValues = List(
    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
    "21", "22", "23")


  it should "display cron field name" in {
    new HourExpandedCronField("").cronFieldName shouldBe "hour"
  }

  it should "convert * to all values" in {
    new HourExpandedCronField("*").expandedValue() shouldBe allValues.mkString(" ")
  }

  it should "convert separate values (with ,) to separate values" in {
    new HourExpandedCronField("1,6,13,19").expandedValue() shouldBe "1 6 13 19"
  }

  it should "not convert separate values (with ,) to separate values with unknown values" in {
    an[IllegalArgumentException] should be thrownBy new HourExpandedCronField("73, 2").expandedValue()
  }

  it should "not convert separate values (with ,) to separate values when having last comma" in {
    an[IllegalArgumentException] should be thrownBy new HourExpandedCronField("10,").expandedValue()
  }

  it should "convert range (-) to a range of values" in {
    new HourExpandedCronField("9-12").expandedValue() shouldBe "9 10 11 12"
  }

  it should "convert range with a step" in {
    new HourExpandedCronField("0-12/3").expandedValue() shouldBe "0 3 6 9 12"
  }

  it should "return all acceptable values for field" in {
    new HourExpandedCronField("").allValues shouldBe allValues
  }
}

