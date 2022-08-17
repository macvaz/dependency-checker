package es.macvaz.spring.kotlin.dep_checker.model

import java.time.LocalDateTime

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DbInitializer {

    @Bean
    fun databaseInitializer(
		processRepository: ProcessRepository,
		checklistRepository: ChecklistRepository,
		ingestedFileRepository: IngestedFileRepository) = ApplicationRunner {

		val process = processRepository.save(
			Process(
				name = "BC-Ope",
				description = "Managing files with operations data",
				periodicity = Periodicity.MONTHLY
			)
		)

		checklistRepository.save(
			ProcessChecklist(
				fileKey = "b1p1",
				camIngestor = "FCR",
				process = process
			)
		)

		ingestedFileRepository.save(
			IngestedFile(
				camIngestor = "FCR",
				fileKey = "b1p1",
				partition1 = "202002",
				duration = 351,
				user = "qpepe",
				status = ProcessStatus.RUNNING,
				startedAt = LocalDateTime.now(),
				endedAt = LocalDateTime.now()
			)
		)
    }
}
