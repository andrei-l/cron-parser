package com.dl.cron

private[cron] class DayOfMonthExpandedCronField(dayOfMonth: String)
  extends ExpandedCronField
    with CronFieldConverterSupport {
  override def expandedValue(): String = convert(dayOfMonth)

  override val cronFieldName: String = "day of month"

  override val allValues: Seq[String] = (1 to 31).map(_.toString)
}
