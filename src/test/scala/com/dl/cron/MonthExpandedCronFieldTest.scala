package com.dl.cron

import org.scalatest.{FlatSpec, Matchers}

class MonthExpandedCronFieldTest extends FlatSpec with Matchers {

  behavior of classOf[MonthExpandedCronField].getSimpleName

  private val allValues = List(
    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "11", "12")


  it should "display cron field name" in {
    new MonthExpandedCronField("").cronFieldName shouldBe "month"
  }

  it should "convert * to all values" in {
    new MonthExpandedCronField("*").expandedValue() shouldBe allValues.mkString(" ")
  }

  it should "convert separate values (with ,) to separate values" in {
    new MonthExpandedCronField("1,6,9").expandedValue() shouldBe "1 6 9"
  }

  it should "not convert separate values (with ,) to separate values with unknown values" in {
    an[IllegalArgumentException] should be thrownBy new MonthExpandedCronField("73, 2").expandedValue()
  }

  it should "not convert separate values (with ,) to separate values when having last comma" in {
    an[IllegalArgumentException] should be thrownBy new MonthExpandedCronField("10,").expandedValue()
  }

  it should "convert range (-) to a range of values" in {
    new MonthExpandedCronField("9-12").expandedValue() shouldBe "9 10 11 12"
  }

  it should "convert range (-) to a range of values with names" in {
    new MonthExpandedCronField("Jun-Dec").expandedValue() shouldBe "Jun Jul Aug Sep Oct Nov Dec"
  }

  it should "convert range with a step" in {
    new MonthExpandedCronField("1-9/2").expandedValue() shouldBe "1 3 5 7 9"
  }

  it should "convert all values with a step" in {
    new MonthExpandedCronField("*/6").expandedValue() shouldBe "1 7"
  }

  it should "convert all values with a step and names" in {
    new MonthExpandedCronField("Mar-Sep/3").expandedValue() shouldBe "Mar Jun Sep"
  }

  it should "return all acceptable values for field" in {
    new MonthExpandedCronField("").allValues shouldBe allValues
  }
}

