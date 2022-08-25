package es.macvaz.spring.kotlin.dep_checker.adapter.out.persistence

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile
import es.macvaz.spring.kotlin.dep_checker.domain.ProcessChecklist
import es.macvaz.spring.kotlin.dep_checker.domain.Process

interface IngestedFileRepository : CrudRepository<IngestedFile, Long> {
    fun findAllByCamIngestorOrderByFileKeyDesc(cam: String): Iterable<IngestedFile>
    fun findAllByOrderByFileKeyDesc(): Iterable<IngestedFile>
}

interface ProcessRepository : CrudRepository<Process, Long> {
    fun findByName(name: String): Process?
}

interface ChecklistRepository: CrudRepository<ProcessChecklist, Long> {}

