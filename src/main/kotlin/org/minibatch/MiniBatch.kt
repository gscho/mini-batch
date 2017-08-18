package org.minibatch

import org.minibatch.config.ConfigParser
import org.minibatch.queue.JobQueue
import org.minibatch.factory.ReaderFactory
import org.minibatch.factory.TransformerFactory
import org.minibatch.factory.WriterFactory
import java.util.stream.Stream
import java.util.*


class MiniBatch {
    fun main(args: Array<String> = arrayOf("./config.yml")) {

        val config = ConfigParser(args[0]).load()
        val reader = ReaderFactory().getReader(config.reader)!!
        val transformer = TransformerFactory().getTransformer(config.reader, config.writer)!!
        val writer = WriterFactory().getWriter(config.writer)!!
        reader.read()
        Stream.generate { Optional.ofNullable(JobQueue.queue.poll()) }
                .filter { e -> e.isPresent }
                .map { e -> transformer.transform(e.get()) }
                .forEach { e -> if (e == "POISON") writer.close() else writer.write(e) }

    }

}