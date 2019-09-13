package com.dl.cron

import org.scalatest.{FlatSpec, Matchers}

class DayOfMonthExpandedCronFieldTest extends FlatSpec with Matchers {

  behavior of classOf[DayOfMonthExpandedCronField].getSimpleName

  private val allValues = List(
    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
    "31")


  it should "display cron field name" in {
    new DayOfMonthExpandedCronField("").cronFieldName shouldBe "day of month"
  }

  it should "convert * to all values" in {
    new DayOfMonthExpandedCronField("*").expandedValue() shouldBe allValues.mkString(" ")
  }

  it should "convert separate values (with ,) to separate values" in {
    new DayOfMonthExpandedCronField("1,6,13,19").expandedValue() shouldBe "1 6 13 19"
  }

  it should "not convert separate values (with ,) to separate values with unknown values" in {
    an[IllegalArgumentException] should be thrownBy new DayOfMonthExpandedCronField("73, 2").expandedValue()
  }

  it should "not convert separate values (with ,) to separate values when having last comma" in {
    an[IllegalArgumentException] should be thrownBy new DayOfMonthExpandedCronField("10,").expandedValue()
  }

  it should "convert range (-) to a range of values" in {
    new DayOfMonthExpandedCronField("9-12").expandedValue() shouldBe "9 10 11 12"
  }

  it should "convert range with a step" in {
    new DayOfMonthExpandedCronField("1-14/6").expandedValue() shouldBe "1 7 13"
  }

  it should "convert all values with a step" in {
    new DayOfMonthExpandedCronField("*/6").expandedValue() shouldBe "1 7 13 19 25 31"
  }

  it should "return all acceptable values for field" in {
    new DayOfMonthExpandedCronField("").allValues shouldBe allValues
  }
}

