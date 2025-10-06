package com.kazemieh.divar.core.location.service

import com.kazemieh.divar.core.location.entity.Neighborhood
import com.kazemieh.divar.core.location.repository.NeighborhoodRepository
import org.springframework.stereotype.Service

@Service
class NeighborhoodService(
    val repository: NeighborhoodRepository
) {

    fun findAll(): List<Neighborhood> {
        return repository.findAll()
    }

    fun save(entity: Neighborhood): Neighborhood {
        return repository.save(entity)
    }

    fun saveAll(entity: List<Neighborhood>): MutableList<Neighborhood> {
        return repository.saveAll(entity)
    }

    fun count(): Long {
        return repository.count()
    }

    fun getReferenceById(id: Long): Neighborhood? {
        return try {
            repository.getReferenceById(id)
        } catch (e: Exception) {
            null
        }
    }

}