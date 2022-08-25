package es.macvaz.spring.kotlin.dep_checker.adapter.`in`.api

import java.time.LocalDateTime

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import es.macvaz.spring.kotlin.dep_checker.domain.*
import es.macvaz.spring.kotlin.dep_checker.adapter.out.persistence.IngestedFileRepository
import es.macvaz.spring.kotlin.dep_checker.application.service.IngestedFileService


@WebMvcTest
class IngestedFileApiIntegrationTests(@Autowired val mockMvc: MockMvc) {

	@MockkBean
	lateinit var repository: IngestedFileRepository

	@MockkBean
	lateinit var service: IngestedFileService

	@Test
	fun `List ingested files`() {
		val b1p1 = IngestedFile(
			camIngestor = "FCR",
			fileKey = "b1p1",
			partition1 = "202002",
			duration = 351,
			user = "qpepe",
			status = ProcessStatus.RUNNING,
			startedAt = LocalDateTime.now(),
			endedAt = LocalDateTime.now()
		)

		every {	service.findAll() } returns listOf(b1p1)
		mockMvc.perform(get("/api/ingestedFile/").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("\$.[0].fileKey").value(b1p1.fileKey))
		}
	}
