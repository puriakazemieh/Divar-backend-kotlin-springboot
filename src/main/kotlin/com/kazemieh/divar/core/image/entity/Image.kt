package com.kazemieh.divar.core.image.entity

import com.kazemieh.divar.core.adds.entity.Ads
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "image")
data class Image(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val path: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    val ads: Ads
)