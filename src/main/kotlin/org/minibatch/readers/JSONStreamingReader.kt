package org.minibatch.readers

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.experimental.delay
import java.io.InputStreamReader
import java.util.*

class JSONStreamingReader constructor(val queue: Queue<Any>) : StreamingReader {


    override fun read(){
        val mapper = ObjectMapper()
        val parser = mapper.factory.createParser(InputStreamReader(System.`in`, "UTF-8"))
        var token = parser.nextToken()
        if (token == JsonToken.START_ARRAY) token = parser.nextToken()
        launch(CommonPool){
            while (token == JsonToken.START_OBJECT) {
                var node: TreeNode = mapper.readTree(parser)
                queue.add(node.toString())
                token = parser.nextToken()
            }
            queue.add("POISON")
        }
    }


}