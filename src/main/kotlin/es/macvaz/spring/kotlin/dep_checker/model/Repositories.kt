package es.macvaz.spring.kotlin.dep_checker.model

import org.springframework.data.repository.CrudRepository

interface IngestedFileRepository : CrudRepository<IngestedFile, Long> {
    fun findAllByCamIngestorOrderByFileKeyDesc(cam: String): Iterable<IngestedFile>
    fun findAllByOrderByFileKeyDesc(): Iterable<IngestedFile>
}

interface ProcessRepository : CrudRepository<Process, Long> {
    fun findByName(name: String): Process?
}

interface ChecklistRepository: CrudRepository<ProcessChecklist, Long> {}

