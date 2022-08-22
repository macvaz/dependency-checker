package es.macvaz.spring.kotlin.dep_checker.service

import org.springframework.stereotype.Service

import es.macvaz.spring.kotlin.dep_checker.model.IngestedFileRepository

@Service
class IngestedFileService(val repository: IngestedFileRepository) {

    fun findAll() = repository.findAll()
    fun findById(id: Long) = repository.findById(id)
}