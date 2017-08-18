package org.minibatch.factory

import org.minibatch.config.Reader
import org.minibatch.queue.JobQueue
import org.minibatch.readers.JSONStreamingReader
import org.minibatch.readers.StreamingReader

class ReaderFactory {
    fun getReader(reader: Reader): StreamingReader? {
        when (reader.type?.toLowerCase()) {
            "json" -> return JSONStreamingReader(JobQueue.queue)
            else                          -> return null
        }
    }
}