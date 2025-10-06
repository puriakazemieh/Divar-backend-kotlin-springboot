package com.kazemieh.divar.core.adds.dto

import com.kazemieh.divar.core.adds.entity.Ads
import com.kazemieh.divar.core.category.entity.Category
import com.kazemieh.divar.core.location.entity.Neighborhood
import com.kazemieh.divar.core.parameter.dto.answer.ParameterAnswerRequest
import com.kazemieh.divar.core.user.entity.User

data class AdsRequest(
    val id: Long? = null,
    val title: String,
    val description: String,
    val price: String,
    val neighborhoodId: Long,
    val categoryId: Long,
    val answer: List<ParameterAnswerRequest>
)

fun AdsRequest.toEntity(neighborhood: Neighborhood, category: Category, user: User): Ads {
    return Ads(
        title = title,
        description = description,
        price = price,
        neighborhood = neighborhood,
        user = user,
        category = category
    )
}
