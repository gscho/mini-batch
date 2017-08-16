package org.minibatch

import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStreamReader

fun main(args: Array<String>) {

    val mapper = ObjectMapper()
    val parser = mapper.getFactory().createParser(InputStreamReader(System.`in`, "UTF-8"))
    var token = parser.nextToken()
    if(token == JsonToken.START_ARRAY) token = parser.nextToken()
    var str = arrayOf("user", "statuses_count")
    while (token == JsonToken.START_OBJECT) {
        var node: TreeNode = mapper.readTree(parser)
        token = parser.nextToken()
        //TODO: doWork(node)
    }
}

