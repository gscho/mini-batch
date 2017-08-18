package org.minibatch.writers

import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Paths
import org.minibatch.config.Writer
import java.text.SimpleDateFormat
import java.util.*

class XmlStreamingWriter(config: Writer) : StreamingWriter {

    private val writer: BufferedWriter =
            Files.newBufferedWriter(
                    Paths.get(
                            Optional.ofNullable(config.path)
                            .orElse("./output" +
                                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                            .format(Date()) + ".xml")))

    init {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>")
    }

    override fun close() {
        writer.write("\n</root>")
        writer.close()
        System.exit(0)
    }


    override fun write(xml: String) {
        writer.write(xml)
    }

}