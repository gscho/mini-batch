package org.minibatch.factory

import org.minibatch.config.Reader
import org.minibatch.config.Writer
import org.minibatch.transformers.JSONXmlTransformer
import org.minibatch.transformers.Transformer

class TransformerFactory{
    fun getTransformer(reader: Reader, writer: Writer): Transformer? {
        when (reader.type.toLowerCase() + writer.type.toLowerCase()) {
            "jsonxml" -> return JSONXmlTransformer()
            else                          -> return null
        }
    }
}