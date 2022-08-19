package es.macvaz.spring.kotlin.dep_checker.serializations

import java.time.LocalDateTime

import kotlinx.serialization.json.JsonElement

import es.macvaz.spring.kotlin.dep_checker.model.IngestedFile
import es.macvaz.spring.kotlin.dep_checker.model.ProcessStatus
import es.macvaz.spring.kotlin.dep_checker.model.getValue

data class RenderedIngestedFile(
    val camIngestor: String,
    val fileKey: String,
    val partition1: String,
    val partition2: String?,
    val duration: Int,
    val user: String,
    val status: String,
    val startedAt: String,
    val endedAt: String,
    var id: Long
)

object IngestedFileSerializer {
    fun fromMap(map: Map<String, JsonElement>) : IngestedFile {
        val data = IngestedFile(
            camIngestor = getValue(map, "camIngestor"),
            fileKey = getValue(map, "fileKey"),
            partition1 = getValue(map, "partition1"),
            partition2 = getValue(map, "partition2"),
            duration = getValue(map, "duration").toInt(),
            user = getValue(map, "user"),
            startedAt = LocalDateTime.now(),
            endedAt = LocalDateTime.now(),
            status = ProcessStatus.valueOf(getValue(map, "status"))
        )
        return data
    }
}