package org.minibatch.writers

import org.json.JSONObject

interface StreamingWriter {
    fun close()
    fun write(json: JSONObject)
}