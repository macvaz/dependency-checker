package es.macvaz.spring.kotlin.dep_checker.adapter.`in`.events

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

import es.macvaz.spring.kotlin.dep_checker.application.port.`in`.RegisterIngestionEventUseCase

@Component
class IngestionKafkaConsumer (val useCase: RegisterIngestionEventUseCase) {

    @KafkaListener(topics = ["\${kafka.topics.ingestion.name}"], groupId = "\${kafka.topics.ingestion.group}")
    fun listenGroup(consumerRecord: ConsumerRecord<String, String>) {
        val messageMap = ConsumerRecordParser.parse(consumerRecord)
        val eventType = ConsumerRecordParser.decodeEventType(messageMap)

        if (eventType == EventTypes.Ingestion)
            useCase.registerIngestionEvent(messageMap!!)
    }
}