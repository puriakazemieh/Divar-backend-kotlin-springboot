package com.kazemieh.divar.core.adds.dto

import com.kazemieh.divar.core.parameter.dto.answer.ParameterAnswerRequest


data class GetAdsRequest(
    val categoryId: Long?,
    val neighborhoodId: Long?,
    val cityId: Long?,
    val price: String?,
    val parameters: List<ParameterAnswerRequest>?,
    val searchText: String = "",
    val page: Int
)
