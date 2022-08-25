package es.macvaz.spring.kotlin.dep_checker.adapter.out.persistence

import org.springframework.stereotype.Component

import es.macvaz.spring.kotlin.dep_checker.application.port.out.IngestedFileRepositoryPort
import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile

@Component
class IngestedFilePersistenceAdapter(private val repository: IngestedFileRepository): IngestedFileRepositoryPort {

    override fun save(ingestion: IngestedFile) {
        repository.save(ingestion)
    }

    override fun findById(id: Long): IngestedFile? {
        return repository.findById(id).orElse(null)
    }

    override fun findAll(): Iterable<IngestedFile> {
        return repository.findAll()
    }
}