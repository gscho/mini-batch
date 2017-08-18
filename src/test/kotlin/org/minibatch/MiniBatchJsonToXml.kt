package org.minibatch

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import org.junit.Rule
import org.junit.contrib.java.lang.system.ExpectedSystemExit
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals


class MiniBatchJsonToXmlTest {

    @Rule @JvmField
    val exit: ExpectedSystemExit = ExpectedSystemExit.none()

    @Before
    fun before(){
        val data = "{\"test\":\"123\"}"
        System.setIn(ByteArrayInputStream(data.toByteArray()))
    }

    @Test
    fun test(){
        exit.expectSystemExit()
        MiniBatch().main(arrayOf("./src/test/kotlin/res/jsonToXml/config.yml"))

    }

    @After
    fun after(){
        val result = Files.readAllLines(Paths.get("./src/test/kotlin/res/jsonToXml/output.xml"))
        assertEquals(3,result.size)
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", result?.get(0))
        assertEquals("<root><test>123</test>", result?.get(1))
        assertEquals("</root>", result?.get(2))
        Files.delete(Paths.get("./src/test/kotlin/res/jsonToXml/output.xml"))
    }
}
