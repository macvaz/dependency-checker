package es.macvaz.spring.kotlin.dep_checker.model

import java.time.LocalDateTime
import javax.persistence.*

import es.macvaz.spring.kotlin.dep_checker.toSlug

@Entity
class Article(
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null
)

@Entity
class User(
	var login: String,
	var firstname: String,
	var lastname: String,
	var description: String? = null,
	@Id @GeneratedValue var id: Long? = null
)
enum class ProcessStatus {
    OK, RUNNING, KO
}


@Entity
class IngestedFile(
    var camIngestor: String,
    var fileKey: String,
    var partition1: String,
    var partition2: String? = null,
    var duration: Int,
    var user: String,
    var startedAt: LocalDateTime,
    var endedAt: LocalDateTime,
    @Enumerated(EnumType.STRING) @Column(length = 8)
    var status: ProcessStatus,
    @Id @GeneratedValue var id: Long? = null
)

@Entity
class ProcessChecklist(
    var fileKey: String,
    var camIngestor: String,
    @ManyToOne var process: Process,
    @Id @GeneratedValue var id: Long? = null
)

enum class Periodicity {
    DAILY, WEEKLY, MONTHLY, YEARLY
}

@Entity
class Process(
    var name: String,
    var description: String,
    @Enumerated(EnumType.STRING) @Column(length = 8)
    var periodicity: Periodicity,
    @Id @GeneratedValue var id: Long? = null
)