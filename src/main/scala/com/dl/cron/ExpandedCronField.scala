package com.dl.cron

trait ExpandedCronField {
  def expandedValue(): String

  def cronFieldName(): String
}
