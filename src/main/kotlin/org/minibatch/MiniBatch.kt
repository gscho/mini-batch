package org.minibatch

import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Optional
import java.util.stream.Stream
import java.util.concurrent.LinkedBlockingQueue
import org.json.XML
import org.json.JSONObject







fun main(args: Array<String>) {
    val queue = LinkedBlockingQueue<String>()
    val mapper = ObjectMapper()
    val parser = mapper.getFactory().createParser(InputStreamReader(System.`in`, "UTF-8"))
    var token = parser.nextToken()
    if (token == JsonToken.START_ARRAY) token = parser.nextToken()
    var str = arrayOf("user", "statuses_count")
    launch(CommonPool){
        while (token == JsonToken.START_OBJECT) {
            var node: TreeNode = mapper.readTree(parser)
//            println("HERE")
//            delay(3000)
            queue.add(node.toString())
            token = parser.nextToken()
        }
        queue.add("POISON")
    }
    val writer: BufferedWriter = Files.newBufferedWriter(Paths.get("./output.xml"))
    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>")
    Stream.generate { Optional.ofNullable(queue.poll()) }
            .filter { e -> e.isPresent }
            .map { e -> e.get() }
            .forEach { e -> if(e == "POISON") close(writer) else write(writer, e) }

}

fun close(writer: BufferedWriter){
    writer.write("\n</root>")
    writer.close()
    System.exit(0)
}

fun write(writer: BufferedWriter, json: String) {
    val json = JSONObject(json)
    val xml = XML.toString(json)
    writer.write(xml)
}

