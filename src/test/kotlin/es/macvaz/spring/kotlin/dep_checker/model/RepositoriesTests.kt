package es.macvaz.spring.kotlin.dep_checker.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

import es.macvaz.spring.kotlin.dep_checker.model.*
import java.time.LocalDateTime

@DataJpaTest
class RepositoriesTests @Autowired constructor(
	val entityManager: TestEntityManager,
	val ingestedFileRepo: IngestedFileRepository
) {

	@Test
	fun `When findById then return IngestedFile`() {
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
		entityManager.persist(b1p1)
		entityManager.flush()
		val found = ingestedFileRepo.findByIdOrNull(b1p1.id!!)
		assertThat(found).isEqualTo(b1p1)
	}
}
