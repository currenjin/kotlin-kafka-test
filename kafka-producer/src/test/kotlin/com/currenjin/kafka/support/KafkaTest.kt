package com.currenjin.kafka.support

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SpringBootTest
@EmbeddedKafka(partitions = 1)
annotation class KafkaTest
