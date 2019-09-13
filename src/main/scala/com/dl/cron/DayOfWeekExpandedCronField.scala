package com.dl.cron

import java.text.DateFormatSymbols

private[cron] class DayOfWeekExpandedCronField(dayOfWeek: String)
  extends ExpandedCronField
    with CronFieldAdditionalConverterSupport {
  override def expandedValue(): String = convert(dayOfWeek)


  override val cronFieldName: String = "day of week"

  override val allValues: Seq[String] = (1 to 7).map(_.toString)

  override val allAdditionalValues: Seq[String] =  new DateFormatSymbols().getWeekdays.map(_.take(3))
}
