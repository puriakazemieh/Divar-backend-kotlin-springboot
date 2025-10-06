package com.kazemieh.divar.core.image.dto

import com.kazemieh.divar.core.image.entity.Image

data class ImageResponse(val path: String)

fun Image.toResponse(): ImageResponse {
    return ImageResponse(path)
}