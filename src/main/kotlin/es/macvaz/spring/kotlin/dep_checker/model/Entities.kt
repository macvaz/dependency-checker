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

@Entity
class IngestedFile(
    var camIngestor: String,
    var fileKey: String,
    var partition1: String,
    var partition2: String? = null,
    var duration: Int,
    var startedAt: LocalDateTime,
    var endedAt: LocalDateTime,
    @Id @GeneratedValue var id: Long? = null
)

@Entity
class ProcessDependencyChecklist(
    var fileKey: String,
    var camIngestor: String,
    @ManyToOne var process: Process,
    @Id @GeneratedValue var id: Long? = null
)

@Entity
class Process(
    var name: String,
    @Id @GeneratedValue var id: Long? = null
)