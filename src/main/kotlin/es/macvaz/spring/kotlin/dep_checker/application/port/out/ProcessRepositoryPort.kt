package es.macvaz.spring.kotlin.dep_checker.application.port.out

import es.macvaz.spring.kotlin.dep_checker.domain.Process

interface ProcessRepositoryPort {
    fun save(process: Process): Process
}