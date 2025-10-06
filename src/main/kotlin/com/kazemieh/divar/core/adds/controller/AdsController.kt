package com.kazemieh.divar.core.adds.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kazemieh.divar.core.adds.dto.AdsRequest
import com.kazemieh.divar.core.adds.service.AdsService
import com.kazemieh.divar.utils.response.ApiResponse
import com.kazemieh.divar.utils.response.BadRequestError
import com.kazemieh.divar.utils.response.UnauthorizedError
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1/")
class AdsController(
    val service: AdsService,
    private val jacksonObjectMapper: ObjectMapper
) {

    @PostMapping("ads")
    fun create(
        @RequestPart("images") images: List<MultipartFile>? = null,
        @RequestPart("ads") adsRequestString: String? = null,
        @RequestHeader("Authorization") token: String? = null,
    ): ResponseEntity<*> {
        if (token.isNullOrEmpty()) return ApiResponse.error(UnauthorizedError())
        if (adsRequestString.isNullOrEmpty()) return ApiResponse.error(BadRequestError(message = "اطلاعات آگهی ارسال نشده است"))
        val adsRequest = jacksonObjectMapper.readValue<AdsRequest>(adsRequestString)
        val ads = service.save(adsRequest, token, images)
        return ApiResponse.success(ads)
    }

}