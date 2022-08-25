package es.macvaz.spring.kotlin.dep_checker.adapter.`in`.web

import es.macvaz.spring.kotlin.dep_checker.adapter.`in`.api.RenderedIngestedFile
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile
import es.macvaz.spring.kotlin.dep_checker.format
import es.macvaz.spring.kotlin.dep_checker.application.service.IngestedFileService


/**
 * Main controller for the Server-Side HTML user interface. Relies on  mustache templates for the HTML rendering
 */
@Controller
class IngestedFileWebEndpoints(private val service: IngestedFileService) {

	@GetMapping("/")
	fun homePage(model: Model): String {
		model["ingestedFiles"] = service.findAll().map { it.render() }
		return "home_page"
	}

	@GetMapping("/ingestedFile/{id}")
	fun processPage(@PathVariable id: Long, model: Model): String {
		val ingestion = service
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
