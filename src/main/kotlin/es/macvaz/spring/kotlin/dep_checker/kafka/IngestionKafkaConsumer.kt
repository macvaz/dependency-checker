package es.macvaz.spring.kotlin.dep_checker.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class IngestionKafkaConsumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${kafka.topics.ingestion.name}"], groupId = "\${kafka.topics.ingestion.group}")
    fun listenGroup(consumerRecord: ConsumerRecord<Any, Any>) {
        logger.info("Message received {}", consumerRecord)
    }
}