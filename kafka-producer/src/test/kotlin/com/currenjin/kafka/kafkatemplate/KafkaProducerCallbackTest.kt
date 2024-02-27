package com.currenjin.kafka.kafkatemplate

import com.currenjin.kafka.support.KafkaTest
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate

@KafkaTest
class KafkaProducerCallbackTest {
    private val sampleTopic = "sample-topic"

    @Autowired
    private lateinit var sut: KafkaTemplate<String, String>

    // TODO: SimpleProduceCallback 사용
    @Test
    fun name() {
        val message = ProducerRecord<String, String>(sampleTopic, "hello")
        sut.send(message)
    }
}