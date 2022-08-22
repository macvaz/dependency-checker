package es.macvaz.spring.kotlin.dep_checker.web

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

import es.macvaz.spring.kotlin.dep_checker.model.*
import es.macvaz.spring.kotlin.dep_checker.format
import es.macvaz.spring.kotlin.dep_checker.serialization.RenderedIngestedFile


/**
 * Main controller for the Server-Side HTML user interface. Relies on  mustache templates for the HTML rendering
 */
@Controller
class IngestedFileWebEndpoints(
	private val fileRepo: IngestedFileRepository) {

	@GetMapping("/")
	fun homePage(model: Model): String {
		model["ingestedFiles"] = fileRepo.findAll().map { it.render() }
		return "home_page"
	}

	@GetMapping("/ingestedFile/{id}")
	fun processPage(@PathVariable id: Long, model: Model): String {
		val ingestion = fileRepo
			.findById(id).orElse(null)
			?.render()
			?: throw ResponseStatusException(NOT_FOUND, "This ingestedFile does not exist")
		model["ingestedFile"] = ingestion
		return "ingested_file_page"
	}

	fun IngestedFile.render() = RenderedIngestedFile(
		camIngestor,
		fileKey,
		partition1,
		partition2,
		duration,
		user,
		status.name,
		startedAt.format(),
		endedAt.format(),
		id!!
	)


}
