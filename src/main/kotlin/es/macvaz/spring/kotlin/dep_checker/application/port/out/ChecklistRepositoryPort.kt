package es.macvaz.spring.kotlin.dep_checker.application.port.out

import es.macvaz.spring.kotlin.dep_checker.domain.ProcessChecklist

interface ChecklistRepositoryPort {
    fun save(process: ProcessChecklist): ProcessChecklist
}