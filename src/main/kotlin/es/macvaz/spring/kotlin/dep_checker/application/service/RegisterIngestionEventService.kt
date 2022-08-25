package es.macvaz.spring.kotlin.dep_checker.application.service

import org.springframework.stereotype.Service

import es.macvaz.spring.kotlin.dep_checker.application.port.`in`.RegisterIngestionEventCommand
import es.macvaz.spring.kotlin.dep_checker.application.port.`in`.RegisterIngestionEventUseCase
import es.macvaz.spring.kotlin.dep_checker.application.port.out.IngestedFileRepositoryPort
import es.macvaz.spring.kotlin.dep_checker.application.common.IngestedFileSerializer
import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile

@Service
class RegisterIngestionEventService (private val repositoryPort: IngestedFileRepositoryPort): RegisterIngestionEventUseCase {
    override fun registerIngestion(message: RegisterIngestionEventCommand): IngestedFile? {
        val ingestedFile = IngestedFileSerializer.fromMap(message)
        repositoryPort.save(ingestedFile)
        return ingestedFile
    }
}