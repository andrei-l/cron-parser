package com.dl.cron

import org.scalatest.{FlatSpec, FunSuite, Matchers}

class CronTransformationTest extends FlatSpec with Matchers {
  behavior of classOf[CronTransformation].getSimpleName

  it should "present arguments as sorted expanded cron fields" in {
    val expandedCronFields = new CronTransformation(Array("1", "2", "3", "4", "5")).asExpandedCronFields

    expandedCronFields.map(_.getClass) should contain theSameElementsInOrderAs Seq(
      classOf[MinuteExpandedCronField], classOf[HourExpandedCronField], classOf[DayOfMonthExpandedCronField],
      classOf[MonthExpandedCronField], classOf[DayOfWeekExpandedCronField]
    )
  }

}
