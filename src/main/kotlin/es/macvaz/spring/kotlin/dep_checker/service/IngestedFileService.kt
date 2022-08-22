package es.macvaz.spring.kotlin.dep_checker.service

import org.springframework.stereotype.Component

import es.macvaz.spring.kotlin.dep_checker.model.IngestedFileRepository

@Component
class IngestedFileService(private val repository: IngestedFileRepository) {

    fun findAll() = repository.findAll()
    fun findById(id: Long) = repository.findById(id)
}