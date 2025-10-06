package com.kazemieh.divar.core.category.service

import com.kazemieh.divar.core.category.entity.Category
import com.kazemieh.divar.core.category.repository.CategoryRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryService(
    val repository: CategoryRepository
) {

    fun findAll(): List<Category> {
        return repository.findAll().filter { it.parent == null }
    }

    fun findById(id: Long): Category? {
        return repository.findById(id).getOrNull()
    }

    fun save(entity: Category): Category {
        return repository.save(entity)
    }

    fun saveAll(entity: List<Category>): MutableList<Category> {
        return repository.saveAll(entity)
    }

    fun count(): Long {
        return repository.count()
    }

    fun getReferenceById(id: Long): Category? {
        return try {
            repository.getReferenceById(id)
        } catch (e: Exception) {
            null
        }
    }

}