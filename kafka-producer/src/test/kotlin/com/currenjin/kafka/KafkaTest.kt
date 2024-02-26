package com.currenjin.kafka

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = [
    "listeners=PLAINTEXT://localhost:9092",
    "port=9092",
])
annotation class KafkaTest
