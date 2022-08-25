package es.macvaz.spring.kotlin.dep_checker.application.service

import org.springframework.stereotype.Service

import es.macvaz.spring.kotlin.dep_checker.application.port.`in`.SearchIngestedFilesUseCase
import es.macvaz.spring.kotlin.dep_checker.application.port.out.IngestedFileRepositoryPort
import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile

@Service
class SearchIngestedFilesService(private val repository: IngestedFileRepositoryPort): SearchIngestedFilesUseCase {
    override fun findById(id: Long): IngestedFile? {
        return repository.findById(id)
    }
    override fun findAll(): Iterable<IngestedFile> {
        return repository.findAll()
    }
}