package com.dl.cron

import java.text.DateFormatSymbols

private[cron] class MonthExpandedCronField(month: String)
  extends ExpandedCronField
    with CronFieldAdditionalConverterSupport {
  override def expandedValue(): String = convert(month)

  override val cronFieldName: String = "month"

  override val allValues: Seq[String] = (1 to 12).map(_.toString)

  override val allAdditionalValues: Seq[String] =  new DateFormatSymbols().getMonths.map(_.take(3))
}
