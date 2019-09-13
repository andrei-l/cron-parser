package com.dl.io

import com.dl.cron.ExpandedCronField

class OutputTransformer(command: String, expandedCronFields: List[ExpandedCronField]) {
  private final val OutputFormat = "%-14s %s"

  override def toString: String = {
    val expandedCronFieldsAsString = expandedCronFields
      .map(ecf => OutputFormat.format(ecf.cronFieldName(), ecf.expandedValue()))
      .mkString(System.lineSeparator())
    expandedCronFieldsAsString + System.lineSeparator() + OutputFormat.format("command", command)
  }
}
