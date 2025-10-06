package com.kazemieh.divar.core.parameter.service

import com.kazemieh.divar.core.parameter.entity.ParameterAnswer
import com.kazemieh.divar.core.parameter.repository.ParameterAnswerRepository
import org.springframework.stereotype.Service

@Service
class ParameterAnswerService(
    val repository: ParameterAnswerRepository
) {


    fun save(entity: ParameterAnswer): ParameterAnswer {
        return repository.save(entity)
    }

    fun findAll(): List<ParameterAnswer> {
        return repository.findAll()
    }

    fun count(): Long {
        return repository.count()
    }

    fun saveAll(values: List<ParameterAnswer>) {
        repository.saveAll(values)
    }
}