package com.currenjin.kafka.kafkatemplate

import com.currenjin.kafka.support.KafkaTest
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import java.time.Duration

@KafkaTest
class KafkaCommitTest {
    private val sampleTopic = "sample-topic"
    private val exampleTopic = "example-topic"
    private val messageValue = "hello world"
    private val sampleMessage: ProducerRecord<String, String> = ProducerRecord(sampleTopic, messageValue)
    private val exampleMessage: ProducerRecord<String, String> = ProducerRecord(exampleTopic, messageValue)

    @Autowired
    private lateinit var template: KafkaTemplate<String, String>

    @Autowired
    private lateinit var factory: DefaultKafkaConsumerFactory<String, String>

    private lateinit var sut: KafkaConsumer<String, String>

    @BeforeEach
    fun setUp() {
        sut = factory.createConsumer("group-id", "client-id") as KafkaConsumer<String, String>
    }

    @Test
    fun name() {
        sut.subscribe(listOf(sampleTopic, exampleTopic))

        template.send(sampleMessage)
        template.send(exampleMessage)

        Thread.sleep(5000)

        val actual: ConsumerRecords<String, String> = sut.poll(Duration.ofSeconds(10))

        assertConsumeCount(1, sampleTopic, actual)
        assertConsumeCount(1, exampleTopic, actual)
    }

    private fun assertConsumeCount(count: Int, topic: String, actual: ConsumerRecords<String, String>) {
        assertEquals(count, actual.records(topic).count())
    }
}