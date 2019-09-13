package com.dl.cron

private[cron] class MinuteExpandedCronField(minute: String)
  extends ExpandedCronField
    with CronFieldConverterSupport {
  override def expandedValue(): String = convert(minute)

  override val cronFieldName: String = "minute"

  override val allValues: Seq[String] = (0 to 59).map(_.toString)
}
