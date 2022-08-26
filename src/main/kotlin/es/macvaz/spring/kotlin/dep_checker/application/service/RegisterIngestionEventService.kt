package es.macvaz.spring.kotlin.dep_checker.application.service

import org.springframework.stereotype.Service

import es.macvaz.spring.kotlin.dep_checker.application.port.`in`.RegisterIngestionEventCommand
import es.macvaz.spring.kotlin.dep_checker.application.port.`in`.RegisterIngestionEventUseCase
import es.macvaz.spring.kotlin.dep_checker.application.port.out.IngestedFileRepositoryPort
import es.macvaz.spring.kotlin.dep_checker.application.service.util.IngestedFileSerializer
import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile

@Service
class RegisterIngestionEventService (private val repositoryPort: IngestedFileRepositoryPort): RegisterIngestionEventUseCase {
    /**
     * Implements port.in user case interfaces recieving as argumentos classes that implements port.out interfaces
     * port.out interfaces are implemented in adapters but we don't depend on adapter but in port.out interfaces
     */
    override fun registerIngestionEvent(message: RegisterIngestionEventCommand): IngestedFile? {
        val ingestedFile = IngestedFileSerializer.fromMap(message)
        repositoryPort.save(ingestedFile)
        return ingestedFile
    }
}