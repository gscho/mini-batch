package org.minibatch

import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.jr.ob.JSON
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val parser = JSON.std.streamingFactory.createParser(InputStreamReader(System.`in`, "UTF-8"))
    var token = parser.nextToken()
    if (token == null) {
        System.exit(0)
    }
    var fields = HashMap<String, MutableList<String>>()
    var start = if (token == JsonToken.START_ARRAY) JsonToken.START_ARRAY else JsonToken.START_OBJECT
    var end = if (start == JsonToken.START_ARRAY) JsonToken.END_ARRAY else JsonToken.END_OBJECT
    while (token != null) {
        if (token == start) {
            fields.clear()
        }
        if (token == JsonToken.FIELD_NAME) {
            var field = parser.getCurrentName()
            token = parser.nextToken()
            var value = parser.getValueAsString()
            if (fields.containsKey(field)) {
                fields.get(field)?.add(field)
            } else {
                Optional.ofNullable(value).ifPresent { v-> fields.put(field, mutableListOf(v)) }
            }
        }
        if (token == end) {
            //TODO: doWork(fields)
        }
        token = parser.nextToken()
    }
    fields.entries.forEach { elem -> println(elem.key + " " + elem.value) }

}

