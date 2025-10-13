package com.kazemieh.divar.utils.response

import org.springframework.data.domain.Page


data class MyPage<T>(
    val content: T,
    val totalPages: Int,
    val totalElements: Long,
    val isFirst: Boolean,
    val isLast: Boolean,
)

fun <T> Page<T>.toMyPage(content: List<*> = this.content): MyPage<*> {
    return MyPage(
        content = content,
        totalPages = totalPages,
        totalElements = totalElements,
        isFirst = isFirst,
        isLast = isLast
    )
}