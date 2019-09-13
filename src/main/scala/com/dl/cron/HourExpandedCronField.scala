package com.dl.cron

private[cron] class HourExpandedCronField(hour: String)
  extends ExpandedCronField
    with CronFieldConverterSupport {
  override def expandedValue(): String = convert(hour)

  override val cronFieldName: String = "hour"

  override val allValues: Seq[String] = (0 to 23).map(_.toString)
}
