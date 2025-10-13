package com.kazemieh.divar.core.adds.repository

import com.kazemieh.divar.core.adds.entity.Ads
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AdsRepository : JpaRepository<Ads, Long> {

    fun findAllByCategoryId(categoryId: Long, pageable: Pageable): Page<Ads>

}