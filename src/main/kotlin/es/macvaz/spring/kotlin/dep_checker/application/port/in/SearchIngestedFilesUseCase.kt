package es.macvaz.spring.kotlin.dep_checker.application.port.`in`

import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile

interface SearchIngestedFilesUseCase {
    fun findById(id: Long): IngestedFile?
    fun findAll(): Iterable<IngestedFile>
}