package com.currenjin.kafka.support

import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.RecordMetadata

class SimpleProduceCallback: Callback {
    companion object {
        fun of(): SimpleProduceCallback {
            return SimpleProduceCallback()
        }
    }

    override fun onCompletion(metadata: RecordMetadata?, exception: Exception) {
        if (metadata != null) {
            println("✅ 메시지 발행 성공.\n" +
                    "topic : ${metadata.topic()},\n" +
                    "partition : ${metadata.partition()},\n" +
                    "offset : ${metadata.offset()},\n" +
                    "timestamp : ${metadata.timestamp()}")
        } else {
            println("❌ 메시지 발행 실패.\n" +
                    exception)
        }
    }

}
