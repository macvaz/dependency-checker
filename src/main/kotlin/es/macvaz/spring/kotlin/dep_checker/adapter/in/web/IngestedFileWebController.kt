package es.macvaz.spring.kotlin.dep_checker.adapter.`in`.web

import java.time.format.DateTimeFormatterBuilder
import java.util.*

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

import es.macvaz.spring.kotlin.dep_checker.domain.IngestedFile
import es.macvaz.spring.kotlin.dep_checker.application.service.SearchIngestedFilesService
import es.macvaz.spring.kotlin.dep_checker.application.service.util.RenderedIngestedFile


/**
 * Main controller for the Server-Side HTML user interface. Relies on  mustache templates for the HTML rendering
 */
@Controller
class IngestedFileWebController(private val service: SearchIngestedFilesService) {

	@GetMapping("/")
	fun homePage(model: Model): String {
		model["ingestedFiles"] = service.findAll().map { it.render() }
		return "home_page"
	}

	@GetMapping("/ingestedFile/{id}")
	fun processPage(@PathVariable id: Long, model: Model): String {
		val ingestion = service
			.findById(id)
			?.render()
			?: throw ResponseStatusException(NOT_FOUND, "This ingestedFile does not exist")
		model["ingestedFile"] = ingestion
		return "ingested_file_page"
	}

	private val englishDateFormatter = DateTimeFormatterBuilder()
		.appendPattern("yyyy-MM-dd")
		.toFormatter(Locale.ENGLISH)

	fun IngestedFile.render() = RenderedIngestedFile(
		camIngestor,
		fileKey,
		partition1,
		partition2,
		duration,
		user,
		status.name,
		startedAt.format(englishDateFormatter),
		endedAt.format(englishDateFormatter),
		id!!
	)


}
