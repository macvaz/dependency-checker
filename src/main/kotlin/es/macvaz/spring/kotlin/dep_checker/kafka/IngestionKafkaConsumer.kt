package es.macvaz.spring.kotlin.dep_checker.kafka

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

import es.macvaz.spring.kotlin.dep_checker.model.IngestedFileRepository
import es.macvaz.spring.kotlin.dep_checker.serializations.IngestedFileSerializer
import kotlinx.serialization.SerializationException

object EventTypes {
    const val Ingestion = "ingested_file"
}

@Component
class IngestionKafkaConsumer (val repository: IngestedFileRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${kafka.topics.ingestion.name}"], groupId = "\${kafka.topics.ingestion.group}")
    fun listenGroup(consumerRecord: ConsumerRecord<String, String>) {
        val messageText = consumerRecord.value()
        val messageMap = try {
            Json.decodeFromString<Map<String, String>>(messageText)
        } catch (e: SerializationException) {
            logger.error("Serialization error of message: $messageText")
            logger.error(consumerRecord.toString())
            return
        }

        when (val messageType = messageMap["event_type"]) {
            EventTypes.Ingestion -> repository.save(IngestedFileSerializer.fromMap(messageMap))
            else -> logger.error("Unexpected message type: $messageType")
        }

        logger.info(messageText)
    }
}