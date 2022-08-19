package es.macvaz.spring.kotlin.dep_checker.model

import kotlinx.serialization.json.JsonElement
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

fun getValue(map: Map<String, JsonElement>, key: String) = map[key].toString()

interface IngestedFileRepository : CrudRepository<IngestedFile, Long> {
    fun findAllByCamIngestorOrderByFileKeyDesc(cam: String): Iterable<IngestedFile>
    fun findAllByOrderByFileKeyDesc(): Iterable<IngestedFile>
}

interface ProcessRepository : CrudRepository<Process, Long> {
    fun findByName(name: String): Process?
}

interface ChecklistRepository: CrudRepository<ProcessChecklist, Long> {}

