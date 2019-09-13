package com.dl.cron

import scala.language.implicitConversions

private[cron] trait CronFieldAdditionalConverterSupport extends CronFieldConverterSupport {
  self =>

  def allAdditionalValues(): Seq[String]

  override def convert(field: String): String = {
    try {
      super.convert(field)
    } catch {
      case _: IllegalArgumentException =>
        new CronFieldConverterSupport {
          override def allValues(): Seq[String] = self.allAdditionalValues()
        }.convert(field)
    }
  }
}
