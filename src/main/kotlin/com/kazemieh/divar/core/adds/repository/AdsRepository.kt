package com.kazemieh.divar.core.adds.repository

import com.kazemieh.divar.core.adds.entity.Ads
import org.springframework.data.jpa.repository.JpaRepository

interface AdsRepository : JpaRepository<Ads, Long> {


}