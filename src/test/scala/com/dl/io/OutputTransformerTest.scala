package com.dl.io

import com.dl.cron.ExpandedCronField
import org.mockito.Mockito
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, FunSuite, Matchers}

class OutputTransformerTest extends FlatSpec with Matchers with MockitoSugar {
  behavior of classOf[OutputTransformer].getSimpleName

  val expandedCronFieldMock1 = mock[ExpandedCronField]
  val expandedCronFieldMock2 = mock[ExpandedCronField]

  it should "format output properly" in {
    Mockito.when(expandedCronFieldMock1.expandedValue()).thenReturn("value1")
    Mockito.when(expandedCronFieldMock1.cronFieldName()).thenReturn("field1")
    Mockito.when(expandedCronFieldMock2.expandedValue()).thenReturn("value2")
    Mockito.when(expandedCronFieldMock2.cronFieldName()).thenReturn("field2")
    val output = new OutputTransformer("some command", List(expandedCronFieldMock1, expandedCronFieldMock2))

    output.toString shouldBe
      """field1         value1
        |field2         value2
        |command        some command""".stripMargin
  }

}
