package com.kazemieh.divar.core.parameter.service

import com.kazemieh.divar.core.category.entity.Category
import com.kazemieh.divar.core.category.service.CategoryService
import com.kazemieh.divar.core.parameter.entity.Parameter
import com.kazemieh.divar.core.parameter.repository.ParameterRepository
import org.springframework.stereotype.Service

@Service
class ParameterService(
    val repository: ParameterRepository,
    val categoryService: CategoryService
) {


    fun save(entity: Parameter): Parameter {
        return repository.save(entity)
    }

    fun findAll(): List<Parameter> {
        return repository.findAll()
    }

    fun findByCategory(categoryId: Long): List<Parameter>? {
        val parameters = repository.findAllByCategoryId(categoryId)
        if (parameters.isEmpty()) {
            var category = categoryService.findById(categoryId) ?: return null
            while (category.parent != null) {
                category = category.parent!!
                repository.findAllByCategoryId(category.id).takeIf { it.isNotEmpty() }?.let {
                    return it
                }
            }
        } else {
            return parameters
        }


        val list: MutableList<Long> = mutableListOf()
        list.add(categoryId)

        println(list.map { it })
        return repository.findAllByCategoryIdIn(list)

    }

    fun count(): Long {
        return repository.count()
    }

    fun saveAll(values: List<Parameter>) {
        repository.saveAll(values)
    }


    fun getReferenceById(id: Long): Parameter? {
        return try {
            repository.getReferenceById(id)
        } catch (e: Exception) {
            null
        }
    }
}