package es.macvaz.spring.kotlin.dep_checker.adapter.out.persistence

import org.springframework.stereotype.Component

import es.macvaz.spring.kotlin.dep_checker.application.port.out.ProcessRepositoryPort
import es.macvaz.spring.kotlin.dep_checker.domain.Process

@Component
class ProcessPersistenceAdapter(private val repository: ProcessRepository): ProcessRepositoryPort {

    override fun save(process: Process) : Process{
        return repository.save(process)
    }
}