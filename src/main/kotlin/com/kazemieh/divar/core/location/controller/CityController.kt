package com.kazemieh.divar.core.location.controller

import com.kazemieh.divar.core.location.dto.toResponse
import com.kazemieh.divar.core.location.service.CityService
import com.kazemieh.divar.utils.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class CityController(
    val service: CityService
) {

    @GetMapping("city")
    fun getCity(
        @RequestParam("includeNeighborhood") includeNeighborhood: Boolean? = true,
    ): ResponseEntity<*> {
        return ApiResponse.success(service.findAll().map { it.toResponse(includeNeighborhood == true) })
    }

}