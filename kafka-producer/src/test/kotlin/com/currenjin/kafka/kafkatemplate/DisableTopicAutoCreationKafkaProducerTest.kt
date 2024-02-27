package com.currenjin.kafka.kafkatemplate

import com.currenjin.kafka.support.DisableTopicAutoCreationKafkaTest
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate

@DisableTopicAutoCreationKafkaTest
class DisableTopicAutoCreationKafkaProducerTest {
    private val sampleTopic = "sample-topic"

    @Autowired
    private lateinit var sut: KafkaTemplate<String, String>

    @Test
    @Timeout(2)
    fun timeout() {
        val message = ProducerRecord<String, String>(sampleTopic, "hello")

        sut.send(message)
    }
}