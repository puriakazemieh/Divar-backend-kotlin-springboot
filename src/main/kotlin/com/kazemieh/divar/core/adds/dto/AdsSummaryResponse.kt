package com.kazemieh.divar.core.adds.dto

import com.kazemieh.divar.core.adds.entity.Ads
import com.kazemieh.divar.core.image.dto.ImageResponse
import com.kazemieh.divar.core.image.dto.toResponse
import com.kazemieh.divar.core.location.dto.NeighborhoodResponse
import com.kazemieh.divar.core.location.dto.toResponse
import java.time.Instant

data class AdsSummaryResponse(
    val id: Long? = null,
    val title: String,
    val price: String,
    val neighborhood: NeighborhoodResponse,
    val previewImage: ImageResponse?,
    val createdAt: Instant? = null,

    )

fun Ads.toAdsSummaryResponse(): AdsSummaryResponse = AdsSummaryResponse(
    id = id,
    title = title,
    price = price,
    neighborhood = neighborhood.toResponse(),
    previewImage = images.firstOrNull()?.toResponse(),
    createdAt = createdAt
)

