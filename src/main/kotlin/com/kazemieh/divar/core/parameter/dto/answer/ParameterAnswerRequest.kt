package com.kazemieh.divar.core.parameter.dto.answer

import com.kazemieh.divar.core.adds.entity.Ads
import com.kazemieh.divar.core.parameter.entity.Parameter
import com.kazemieh.divar.core.parameter.entity.ParameterAnswer

data class ParameterAnswerRequest(
    val answer: String,
    val parameterId: Long,
)

fun ParameterAnswerRequest.toEntity(ads: Ads,parameter: Parameter): ParameterAnswer{
    return ParameterAnswer(
        answer = answer,
        ads = ads,
        parameter = parameter
    )
}