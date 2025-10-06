package com.kazemieh.divar.core.adds.dto

import com.kazemieh.divar.core.adds.entity.Ads
import com.kazemieh.divar.core.category.dto.CategoryResponse
import com.kazemieh.divar.core.category.dto.toResponse
import com.kazemieh.divar.core.image.dto.ImageResponse
import com.kazemieh.divar.core.image.dto.toResponse
import com.kazemieh.divar.core.location.dto.NeighborhoodResponse
import com.kazemieh.divar.core.location.dto.toResponse
import com.kazemieh.divar.core.parameter.dto.answer.ParameterAnswerResponse
import com.kazemieh.divar.core.parameter.dto.answer.toResponse
import com.kazemieh.divar.core.user.dto.UserResponse
import com.kazemieh.divar.core.user.dto.toResponse

data class AdsResponse(
    val id: Long? = null,
    val title: String,
    val description: String,
    val price: String,
    val neighborhood: NeighborhoodResponse,
    val user: UserResponse,
    val category: CategoryResponse,
    val images: List<ImageResponse>,
    val answer: List<ParameterAnswerResponse>
)

fun Ads.toResponse(): AdsResponse = AdsResponse(
    id = id,
    title = title,
    description = description,
    price = price,
    neighborhood = neighborhood.toResponse(),
    user = user.toResponse(""),
    category = category.toResponse(false),
    images = images.map { it.toResponse() },
    answer = answer.map { it.toResponse() }
)

