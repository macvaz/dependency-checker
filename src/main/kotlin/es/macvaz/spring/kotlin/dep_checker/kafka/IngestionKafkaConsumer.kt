package es.macvaz.spring.kotlin.dep_checker.kafka

import kotlinx.serialization.json.*

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

import es.macvaz.spring.kotlin.dep_checker.model.IngestedFileRepository
import es.macvaz.spring.kotlin.dep_checker.serializations.IngestedFileSerializer

@Component
class IngestionKafkaConsumer (val repository: IngestedFileRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${kafka.topics.ingestion.name}"], groupId = "\${kafka.topics.ingestion.group}")
    fun listenGroup(consumerRecord: ConsumerRecord<String, String>) {
        logger.info("Message received {}", consumerRecord)
        val jsonString = consumerRecord.value()

        val json = Json.parseToJsonElement(jsonString)
        val message = json.jsonObject.toMap()

        logger.info(message["event_type"]?.toString())

        when (message["event_type"]?.toString()) {
            "ingested_file" -> {
                repository.save(IngestedFileSerializer.fromMap(message))
            }
            else -> logger.info(jsonString)
        }
    }
}