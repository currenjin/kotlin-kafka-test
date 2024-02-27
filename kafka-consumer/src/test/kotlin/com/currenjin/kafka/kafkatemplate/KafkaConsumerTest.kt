package com.currenjin.kafka.kafkatemplate

import com.currenjin.kafka.support.KafkaTest
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import java.util.*

@KafkaTest
class KafkaConsumerTest {
    private val sampleTopic = "sample-topic"
    private val exampleTopic = "example-topic"
    private val messageValue = "hello world"
    private val sampleTopicMessage: ProducerRecord<String, String> = ProducerRecord(sampleTopic, messageValue)
    private val exampleTopicMessage: ProducerRecord<String, String> = ProducerRecord(exampleTopic, messageValue)

    @Autowired
    private lateinit var sut: KafkaTemplate<String, String>

    @Autowired
    private lateinit var consumerFactory: ConsumerFactory<String, String>

    @BeforeEach
    fun setUp() {
        sut.setConsumerFactory(consumerFactory)
    }

    @Test
    fun consume() {
        sut.send(sampleTopicMessage)

        val actual = sut.receive(sampleTopic, 0, 0)

        assertEquals(messageValue, actual!!.value())
    }

    @Test
    fun multi_consume() {
        sut.send(sampleTopicMessage)
        sut.send(exampleTopicMessage)

        val sampleActual = sut.receive(sampleTopic, 0, 0)
        val exampleActual = sut.receive(exampleTopic, 0, 0)

        assertEquals(sampleTopic, sampleActual!!.topic())
        assertEquals(messageValue, sampleActual.value())
        assertEquals(exampleTopic, exampleActual!!.topic())
        assertEquals(messageValue, exampleActual.value())
    }
}
