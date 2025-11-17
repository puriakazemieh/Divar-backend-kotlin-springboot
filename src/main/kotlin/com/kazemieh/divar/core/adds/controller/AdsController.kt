package com.kazemieh.divar.core.adds.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kazemieh.divar.core.adds.dto.AdsRequest
import com.kazemieh.divar.core.adds.dto.toResponse
import com.kazemieh.divar.core.adds.service.AdsService
import com.kazemieh.divar.utils.response.*
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

    @GetMapping("ads")
    fun getAds(
        @RequestParam(value = "page", required = false) page: Int? = 0,
        @RequestParam(value = "pageSize", required = false) pageSize: Int? = 20,
        @RequestParam(value = "categoryId", required = false) categoryId: Long? = null,
//        @RequestHeader("Authorization") token: String? = null
    ): ResponseEntity<*> {
//        if (token.isNullOrEmpty()) return ApiResponse.error(UnauthorizedError())
        if (categoryId == null) {
            val adsPage = service.findAll(page ?: 0, pageSize ?: 20)
            return ApiResponse.success(adsPage.toMyPage(adsPage.content.map { it?.toResponse() }))
        } else {
            val adsPage = service.findAll(page ?: 0, pageSize ?: 20, categoryId)
            return ApiResponse.success(adsPage.toMyPage(adsPage.content.map { it?.toResponse() }))
        }
    }

    @GetMapping("ads/detail")
    fun getAds(
        @RequestParam(value = "id") adsId: Long? = null,
//        @RequestHeader("Authorization") token: String? = null
    ): ResponseEntity<*> {
//        if (token.isNullOrEmpty()) return ApiResponse.error(UnauthorizedError())
        if (adsId == null) return ApiResponse.error(BadRequestError(message = "آیدی آگهی ضروری است"))
        return service.findById(adsId)?.let {
            ApiResponse.success(it.toResponse())
        } ?: ApiResponse.error(GoneError())
    }

    @GetMapping("ads/categories_of_ads")
    fun getCategoriesOfAds(
        @RequestParam(value = "searchText") searchText: String? = null,
    ): ResponseEntity<*> {
        if (searchText.isNullOrEmpty()) return ApiResponse.error(BadRequestError(message = "متن جست و جو الزامی است"))
        return service.findCategoriesWithAdsCount(searchText).let {
            ApiResponse.success(it)
        }
    }


}