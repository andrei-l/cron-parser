package com.dl.cron

class CronTransformation(args: Array[String]) {
  private val CronFieldsNumber = 5

  require(
    args.length >= CronFieldsNumber,
    s"expected $CronFieldsNumber cron fields"
  )

  def asExpandedCronFields: List[ExpandedCronField] = List(
    new MinuteExpandedCronField(args(0)),
    new HourExpandedCronField(args(1)),
    new DayOfMonthExpandedCronField(args(2)),
    new MonthExpandedCronField(args(3)),
    new DayOfWeekExpandedCronField(args(4))
  )
}
