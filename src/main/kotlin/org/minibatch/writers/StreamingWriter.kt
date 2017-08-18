package org.minibatch.writers


interface StreamingWriter {
    fun close()
    fun write(xml: String)
}