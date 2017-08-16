package org.minibatch

import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.io.InputStreamReader
import java.lang.Thread.sleep
import java.util.Optional
import java.util.stream.Stream
import java.util.concurrent.LinkedBlockingQueue
//import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
    val queue = LinkedBlockingQueue<String>()
    val mapper = ObjectMapper()
    val parser = mapper.getFactory().createParser(InputStreamReader(System.`in`, "UTF-8"))
    var token = parser.nextToken()
    if (token == JsonToken.START_ARRAY) token = parser.nextToken()
    var str = arrayOf("user", "statuses_count")
    //val exec = Executors.newFixedThreadPool(2)
    launch(CommonPool){
        while (token == JsonToken.START_OBJECT) {
            var node: TreeNode = mapper.readTree(parser)
            println("here")
            delay(3000)
            queue.add(node.toString())
            token = parser.nextToken()
        }
        queue.add("POISON")
    }
    Stream.generate { Optional.ofNullable(queue.poll()) }.filter { e -> e.isPresent }.map { e -> e.get() }.forEach { e -> if(e == "POISON") System.exit(0) else println(e) }

}

