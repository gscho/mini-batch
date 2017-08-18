package org.minibatch.queue

import java.util.concurrent.LinkedBlockingQueue

object JobQueue {
    val queue = LinkedBlockingQueue<Any>()
}