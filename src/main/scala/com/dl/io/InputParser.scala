package com.dl.io

import com.dl.cron.CronTransformation

class InputParser(args: Array[String]) {
  require(
    args.length == 1,
    """expected a single program argument like "*/15 0 1,15 * 1-5 /usr/bin/find" """
  )

  private val cronArgs = args.head.split(" ")

  require(
    cronArgs.length == 6,
    "expected standard cron format with five time fields (minute, hour, day of month, month, and day of week) plus a command."
  )

  def cronTransformation: CronTransformation = new CronTransformation(cronArgs)

  def command: String = cronArgs(5)
}
