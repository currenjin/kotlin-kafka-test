package com.currenjin.kafka

import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate

@KafkaTest
class KafkaProducerTest {
    private val sampleTopic = "sample-topic"

    @Autowired
    private lateinit var sut: KafkaTemplate<String, String>

    @Test
    fun sample() {
        val message = ProducerRecord<String, String>(sampleTopic, "hello")

        val actual = sut.send(message)

        assertEquals(sampleTopic, actual.get().recordMetadata.topic())
    }
}