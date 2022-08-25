package es.macvaz.spring.kotlin.dep_checker.application.port.`in`

import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile

typealias RegisterIngestionEventCommand = Map<String, String>

interface RegisterIngestionEventUseCase {
    fun registerIngestionEvent(message: RegisterIngestionEventCommand): IngestedFile?
}