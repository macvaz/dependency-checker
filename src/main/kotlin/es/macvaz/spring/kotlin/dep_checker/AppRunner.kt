package es.macvaz.spring.kotlin.dep_checker

import java.time.LocalDateTime

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import es.macvaz.spring.kotlin.dep_checker.model.*


@Configuration
class AppRunner {

    @Bean
    fun databaseInitializer(
		userRepository: UserRepository,
		articleRepository: ArticleRepository,
		processRepository: ProcessRepository,
		checklistRepository: ChecklistRepository,
		ingestedFileRepository: IngestedFileRepository) = ApplicationRunner {

        val smaldini = userRepository.save(User("smaldini", "Stéphane", "Maldini"))
        articleRepository.save(
			Article(
				title = "Reactor Bismuth is out",
				headline = "Lorem ipsum",
				content = "dolor sit amet",
				author = smaldini)
		)
        articleRepository.save(
			Article(
				title = "Reactor Aluminium has landed",
				headline = "Lorem ipsum",
				content = "dolor sit amet",
				author = smaldini)
		)

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
