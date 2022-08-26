package es.macvaz.spring.kotlin.dep_checker.adapter.`in`.events

import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory

object EventTypes {
    const val Ingestion = "ingested_file"
}

object ConsumerRecordParser {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun parse(record: ConsumerRecord<String, String>): Map<String, String>? {
        val messageText = record.value()
        val messageMap = try { Json.decodeFromString<Map<String, String>>(messageText) }
        catch (e: SerializationException) {
            logger.error("Serialization error of message: $messageText")
            logger.error(record.toString())
            return null
        }
        return messageMap
    }

    fun decodeEventType(messageMap: Map<String, String>?): String? {
        when (val messageType = messageMap?.get("event_type")) {
            EventTypes.Ingestion -> return EventTypes.Ingestion
            else -> logger.error("Unexpected message type: $messageType")
        }
        return null
    }


}