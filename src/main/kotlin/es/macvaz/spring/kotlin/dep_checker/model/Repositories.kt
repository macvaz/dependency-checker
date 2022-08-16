package es.macvaz.spring.kotlin.dep_checker.model

import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {
	fun findBySlug(slug: String): Article?
	fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : CrudRepository<User, Long> {
	fun findByLogin(login: String): User?
	fun findAllByOrderByFirstnameDesc(): Iterable<User>
}

interface IngestedFileRepository : CrudRepository<IngestedFile, Long> {}

interface ProcessRepository : CrudRepository<Process, Long> {}

interface ChecklistRepository: CrudRepository<ProcessChecklist, Long> {}

