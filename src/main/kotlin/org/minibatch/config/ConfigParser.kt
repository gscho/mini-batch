package org.minibatch.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.nio.file.Files
import java.nio.file.Paths

class ConfigParser(path: String = "./config.yml") {
    private val path = Paths.get(path)
    fun load(): Config {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())

        return Files.newBufferedReader(this.path).use {
            mapper.readValue(it, Config::class.java)
        }
    }
}

data class General(val type: String? = null )

data class Reader(val type: String)

data class Writer(val type: String, val path: String)

data class Config(val general: General , val reader: Reader, val writer: Writer)