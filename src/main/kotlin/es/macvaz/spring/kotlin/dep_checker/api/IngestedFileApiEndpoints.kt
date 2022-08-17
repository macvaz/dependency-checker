package es.macvaz.spring.kotlin.dep_checker.api

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import es.macvaz.spring.kotlin.dep_checker.model.*

@RestController
@RequestMapping("/api/ingestedFile")
class IngestedFileController(private val repository: IngestedFileRepository) {

	@GetMapping("/")
	fun findAll() = repository.findAll()

	@GetMapping("/{id}")
	fun findOne(@PathVariable id: Long) = repository
		.findById(id).orElse(null)
		?: throw ResponseStatusException(NOT_FOUND, "This ingestion does not exist")

}
