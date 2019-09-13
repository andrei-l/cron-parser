package com.dl

import com.dl.io.{InputParser, OutputTransformer}

object CronParser extends App {
  val inputParser = new InputParser(args)
  val command = inputParser.command
  val cronTransformer = inputParser.cronTransformation

  val outputTransformer = new OutputTransformer(command, cronTransformer.asExpandedCronFields)
  println(outputTransformer.toString)
}
