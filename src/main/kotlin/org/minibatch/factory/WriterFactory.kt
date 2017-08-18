package org.minibatch.factory

import org.minibatch.config.Writer
import org.minibatch.writers.StreamingWriter
import org.minibatch.writers.XmlStreamingWriter

class WriterFactory {
    fun getWriter(writer: Writer): StreamingWriter? {
        when (writer.type?.toLowerCase()) {
            "xml" -> return XmlStreamingWriter()
            else -> return null
        }
    }
}