package com.kazemieh.divar.core.parameter.entity

import com.kazemieh.divar.core.adds.entity.Ads
import jakarta.persistence.*

@Entity(name = "Parameter_answer")
data class ParameterAnswer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val answer: String,

    @ManyToOne()
    @JoinColumn(name = "ads_id")
    val ads: Ads,

    @ManyToOne()
    @JoinColumn(name = "parameter_id", nullable = true)
    val parameter: Parameter,

    )

