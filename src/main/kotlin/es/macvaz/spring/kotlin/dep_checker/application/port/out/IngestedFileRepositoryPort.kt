package es.macvaz.spring.kotlin.dep_checker.application.port.out

import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile

interface IngestedFileRepositoryPort {
    fun save(ingestion: IngestedFile): IngestedFile
    fun findById(id: Long): IngestedFile?
    fun findAll(): Iterable<IngestedFile>
}