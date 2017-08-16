package org.minibatch

import org.json.JSONObject
import org.minibatch.readers.JSONStreamingReader
import java.util.Optional
import java.util.stream.Stream
import java.util.concurrent.LinkedBlockingQueue
import org.minibatch.writers.XmlStreamingWriter


fun main(args: Array<String>) {
    val queue = LinkedBlockingQueue<String>()
    val reader = JSONStreamingReader(queue)
    reader.read()
    val writer = XmlStreamingWriter()
    Stream.generate { Optional.ofNullable(queue.poll()) }
            .filter { e -> e.isPresent }
            .map { e -> e.get() }
            .forEach { e -> if(e == "POISON") writer.close() else writer.write(JSONObject(e)) }

}

