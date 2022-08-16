package es.macvaz.spring.kotlin.dep_checker.web

import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

import es.macvaz.spring.kotlin.dep_checker.model.*
import es.macvaz.spring.kotlin.dep_checker.format
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

/**
 * Main controller for the Server-Side HTML user interface. Relies on  mustache templates for the HTML rendering
 */
@Controller
class CheckListWebEndpoints(private val checklistRepo: ChecklistRepository) {

	/*
	@GetMapping("/")
	fun homePage(model: Model): String {
		model["ingestedFiles"] = checklistRepo.findAll().map { it.render() }
		return "home_page"
	}

	@GetMapping("/checklist/{id}")
	fun processPage(@PathVariable id: Long, model: Model): String {
		val process = fileRepo
			.findById(id)
			.map { it.render() }
			?: throw ResponseStatusException(NOT_FOUND, "This process does not exist")
		model["process"] = process
		return "process_page"
	}

	fun Process.render() = RenderedProcess(
		name,
		description,
		periodicity.name
	)

	data class RenderedProcess(
		val name: String,
		val description: String,
		var periodicity: String
	)
	 */
}
