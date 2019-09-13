package com.dl.cron

import scala.language.implicitConversions

private[cron] trait CronFieldConverterSupport {
  private val ExcludedGroup = "([^,-/*]+)"
  private val AnyValue = "*"
  private val ValueListSeparatorRegex = s"""($ExcludedGroup,)*$ExcludedGroup""".r
  private val RangeValuesSeparatorRegex = s"""$ExcludedGroup-$ExcludedGroup""".r
  private val StepValuesSeparatorRegex = s"(.*)/(\\d+)".r

  def convert(field: String): String = {
    if (field == AnyValue) {
      allValues()
    } else if (ValueListSeparatorRegex.pattern.matcher(field).matches()) {
      matchValueListValues(field)
    } else if (RangeValuesSeparatorRegex.pattern.matcher(field).matches()) {
      matchRangeValues(field)
    } else if (StepValuesSeparatorRegex.pattern.matcher(field).matches()) {
      matchStepValues(field)
    } else throwFailedToConvertFieldException(field)

  }

  private def matchRangeValues(field: String): Seq[String] = {
    field match {
      case RangeValuesSeparatorRegex(left, right) if allValues().contains(left) && allValues().contains(right) =>
        allValues().dropWhile(_ != left).reverse.dropWhile(_ != right).reverse
      case _ => throwFailedToConvertFieldException(field)
    }
  }

  private def matchValueListValues(field: String): Seq[String] = {
    val parsedValues = field.split(",")
    if (parsedValues.forall(allValues().contains)) parsedValues.seq
    else throwFailedToConvertFieldException(field)
  }

  private def matchStepValues(field: String): Seq[String] = {
    def skip(seq: Seq[String], n: Int) =
      seq.zipWithIndex.collect { case (element, index) if (index % n) == 0 => element }

    field match {
      case StepValuesSeparatorRegex("*", step) =>
        skip(allValues(), step.toInt)
      case StepValuesSeparatorRegex(range, step) =>
        val rangeValues = matchRangeValues(range)
        skip(rangeValues, step.toInt)
      case _ => throwFailedToConvertFieldException(field)
    }
  }

  private def throwFailedToConvertFieldException[T](field: String): T =
    throw new IllegalArgumentException(s"Failed to convert $field to valid cron value")

  private implicit def stringListToString(strings: Seq[String]): String = strings.mkString(" ")

  def allValues(): Seq[String]

}
