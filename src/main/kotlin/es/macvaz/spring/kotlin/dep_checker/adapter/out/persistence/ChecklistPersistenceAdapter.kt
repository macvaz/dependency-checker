package es.macvaz.spring.kotlin.dep_checker.adapter.out.persistence

import org.springframework.stereotype.Component

import es.macvaz.spring.kotlin.dep_checker.application.port.out.ChecklistRepositoryPort
import es.macvaz.spring.kotlin.dep_checker.domain.ProcessChecklist

@Component
class ChecklistPersistenceAdapter(private val repository: ChecklistRepository): ChecklistRepositoryPort {

    override fun save(checklist: ProcessChecklist): ProcessChecklist {
        return repository.save(checklist)
    }
}