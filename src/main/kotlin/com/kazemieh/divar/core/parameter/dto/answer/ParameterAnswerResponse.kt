package com.kazemieh.divar.core.parameter.dto.answer

import com.kazemieh.divar.core.parameter.dto.ParameterResponse
import com.kazemieh.divar.core.parameter.dto.toResponse
import com.kazemieh.divar.core.parameter.entity.ParameterAnswer

data class ParameterAnswerResponse(
    val answer: String,
    val parameterResponse: ParameterResponse,
)

fun ParameterAnswer.toResponse(): ParameterAnswerResponse {
    return ParameterAnswerResponse(
        answer = answer,
        parameterResponse = parameter.toResponse()
    )
}