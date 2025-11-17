package com.kazemieh.divar.core.adds.repository

import com.kazemieh.divar.core.adds.entity.Ads
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AdsRepository : JpaRepository<Ads, Long> {

    fun findAllByCategoryId(categoryId: Long, pageable: Pageable): Page<Ads>

    @Query(
        """
            select c.name,c.id, count(a.id),a.title
            from adds a
            join a.category c
            where a.title like %:searchText% 
            group by c.name
        """
    )
    fun findCategoriesWithAdsCount(@Param("searchText") searchText: String): List<Array<Any>>

}