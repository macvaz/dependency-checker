package es.macvaz.spring.kotlin.dep_checker.repository

import org.springframework.data.repository.CrudRepository

import es.macvaz.spring.kotlin.dep_checker.model.IngestedFile
import es.macvaz.spring.kotlin.dep_checker.model.ProcessChecklist
import es.macvaz.spring.kotlin.dep_checker.model.Process
import org.springframework.stereotype.Repository

@Repository
interface IngestedFileRepository : CrudRepository<IngestedFile, Long> {
    fun findAllByCamIngestorOrderByFileKeyDesc(cam: String): Iterable<IngestedFile>
    fun findAllByOrderByFileKeyDesc(): Iterable<IngestedFile>
}

interface ProcessRepository : CrudRepository<Process, Long> {
    fun findByName(name: String): Process?
}

interface ChecklistRepository: CrudRepository<ProcessChecklist, Long> {}

