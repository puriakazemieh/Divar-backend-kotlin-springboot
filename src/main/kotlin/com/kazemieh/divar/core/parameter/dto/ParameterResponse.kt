package com.kazemieh.divar.core.parameter.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.kazemieh.divar.core.category.dto.CategoryResponse
import com.kazemieh.divar.core.category.dto.toResponse
import com.kazemieh.divar.core.parameter.entity.DataType
import com.kazemieh.divar.core.parameter.entity.Parameter

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ParameterResponse(
    val id: Long,
    val name: String,
    val dataType: DataType,
    val acceptedOptions: List<String>? = null,
    val category: CategoryResponse? = null,
)

fun Parameter.toResponse(includeCategory: Boolean? = false): ParameterResponse {
    return ParameterResponse(
        id = id,
        name = name,
        dataType = dataType,
        acceptedOptions = acceptedOptions?.split(","),
        category = if (includeCategory == true) category.toResponse() else null
    )
}