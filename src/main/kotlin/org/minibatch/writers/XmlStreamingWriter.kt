package org.minibatch.writers

import org.json.JSONObject
import org.json.XML
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Paths

class XmlStreamingWriter : StreamingWriter {

    private val writer: BufferedWriter = Files.newBufferedWriter(Paths.get("./output.xml"))

    init {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>")
    }

    override fun close(){
        writer.write("\n</root>")
        writer.close()
        System.exit(0)
    }


    override fun write(json: JSONObject) {
        val xml = XML.toString(json)
        writer.write(xml)
    }

}