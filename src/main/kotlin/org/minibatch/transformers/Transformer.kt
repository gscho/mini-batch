package org.minibatch.transformers

interface Transformer{
    fun transform(e : Any): String
}