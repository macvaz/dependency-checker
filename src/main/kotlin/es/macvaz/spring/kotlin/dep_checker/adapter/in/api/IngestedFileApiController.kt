package es.macvaz.spring.kotlin.dep_checker.adapter.`in`.api

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import es.macvaz.spring.kotlin.dep_checker.application.port.`in`.SearchIngestedFilesUseCase

@RestController
@RequestMapping("/api/ingestedFile")
class IngestedFileController(private val useCase: SearchIngestedFilesUseCase) {

	@GetMapping("/")
	fun findAll() = useCase.findAll()

	@GetMapping("/{id}")
	fun findOne(@PathVariable id: Long) = useCase
		.findById(id)
		?: throw ResponseStatusException(NOT_FOUND, "This ingestion does not exist")

}
