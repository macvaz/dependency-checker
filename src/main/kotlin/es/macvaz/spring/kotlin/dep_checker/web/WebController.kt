package es.macvaz.spring.kotlin.dep_checker.web

import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

import es.macvaz.spring.kotlin.dep_checker.model.*
import es.macvaz.spring.kotlin.dep_checker.format

/**
 * Main controller for the Server-Side HTML user interface. Relies on  mustache templates for the HTML rendering
 */
@Controller
class WebController(
	private val repository: ArticleRepository) {

	@GetMapping("/")
	fun home(model: Model): String {
		model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
		return "home_page"
	}

	@GetMapping("/article/{slug}")
	fun article(@PathVariable slug: String, model: Model): String {
		val article = repository
				.findBySlug(slug)
				?.render()
				?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")
		model["article"] = article
		return "process_page"
	}

	fun Article.render() = RenderedArticle(
			slug,
			title,
			headline,
			content,
			author,
			addedAt.format()
	)

	data class RenderedArticle(
		val slug: String,
		val title: String,
		val headline: String,
		val content: String,
		val author: User,
		val addedAt: String)
}
