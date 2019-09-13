package com.dl.cron

import org.scalatest.{FlatSpec, Matchers}

class DayOfWeekExpandedCronFieldTest extends FlatSpec with Matchers {

  behavior of classOf[DayOfWeekExpandedCronFieldTest].getSimpleName

  private val allValues = List(
    "1", "2", "3", "4", "5", "6", "7")


  it should "display cron field name" in {
    new DayOfWeekExpandedCronField("").cronFieldName shouldBe "day of week"
  }

  it should "convert * to all values" in {
    new DayOfWeekExpandedCronField("*").expandedValue() shouldBe allValues.mkString(" ")
  }

  it should "convert separate values (with ,) to separate values" in {
    new DayOfWeekExpandedCronField("1,6,7").expandedValue() shouldBe "1 6 7"
  }

  it should "not convert separate values (with ,) to separate values with unknown values" in {
    an[IllegalArgumentException] should be thrownBy new DayOfWeekExpandedCronField("73, 2").expandedValue()
  }

  it should "not convert separate values (with ,) to separate values when having last comma" in {
    an[IllegalArgumentException] should be thrownBy new DayOfWeekExpandedCronField("10,").expandedValue()
  }

  it should "convert range (-) to a range of values" in {
    new DayOfWeekExpandedCronField("3-6").expandedValue() shouldBe "3 4 5 6"
  }

  it should "convert range with a step" in {
    new DayOfWeekExpandedCronField("1-6/2").expandedValue() shouldBe "1 3 5"
  }

  it should "convert all values with a step" in {
    new DayOfWeekExpandedCronField("*/6").expandedValue() shouldBe "1 7"
  }

  it should "convert all values with a step and names" in {
    new DayOfWeekExpandedCronField("Mon-Sat/2").expandedValue() shouldBe "Mon Wed Fri"
  }

  it should "return all acceptable values for field" in {
    new DayOfWeekExpandedCronField("").allValues shouldBe allValues
  }
}


