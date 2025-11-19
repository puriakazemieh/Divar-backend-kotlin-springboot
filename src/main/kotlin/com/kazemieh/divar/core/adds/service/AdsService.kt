package com.kazemieh.divar.core.adds.service

import com.kazemieh.divar.core.adds.dto.AdsRequest
import com.kazemieh.divar.core.adds.dto.CategoriesOfAds
import com.kazemieh.divar.core.adds.dto.toEntity
import com.kazemieh.divar.core.adds.dto.toResponse
import com.kazemieh.divar.core.adds.entity.Ads
import com.kazemieh.divar.core.adds.repository.AdsRepository
import com.kazemieh.divar.core.category.service.CategoryService
import com.kazemieh.divar.core.image.service.ImageService
import com.kazemieh.divar.core.location.service.NeighborhoodService
import com.kazemieh.divar.core.parameter.dto.answer.ParameterAnswerRequest
import com.kazemieh.divar.core.parameter.dto.answer.toEntity
import com.kazemieh.divar.core.parameter.service.ParameterAnswerService
import com.kazemieh.divar.core.parameter.service.ParameterService
import com.kazemieh.divar.core.user.service.UserService
import com.kazemieh.divar.utils.response.ApiResponse
import com.kazemieh.divar.utils.response.BadRequestError
import com.kazemieh.divar.utils.response.UnauthorizedError
import com.kazemieh.divar.utils.security.JwtService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import kotlin.collections.get

@Service
class AdsService(
    val repository: AdsRepository,
    val jwtService: JwtService,
    val userService: UserService,
    val neighborhoodService: NeighborhoodService,
    val categoryService: CategoryService,
    val parameterService: ParameterService,
    val parameterAnswerService: ParameterAnswerService,
    val imageService: ImageService
) {


    fun save(adsRequest: AdsRequest, token: String, images: List<MultipartFile>?): ResponseEntity<*> {
        val user = jwtService.extractMobile(token)?.let { userService.findByMobile(it) }
            ?: return ApiResponse.error(UnauthorizedError(message = "توکن صحیح نیست"))

        val neighborhood = neighborhoodService.getReferenceById(adsRequest.neighborhoodId)
            ?: return ApiResponse.error(BadRequestError(message = " محله یافت نشد"))

        val category = categoryService.getReferenceById(adsRequest.categoryId)
            ?: return ApiResponse.error(BadRequestError(message = " دسته بندی یافت نشد"))

        val ads = repository.save(adsRequest.toEntity(neighborhood, category, user))

        val answer = adsRequest.answer.map {
            it.toEntity(
                ads = ads,
                parameter = parameterService.getReferenceById(it.parameterId)
                    ?: return ApiResponse.error(BadRequestError(message = "پارامتر یافت نشد "))
            )
        }
        parameterAnswerService.saveAll(answer)

        images?.let { imageService.saveAll(it, ads) }

        return ApiResponse.success(ads.toResponse())
    }

    fun findAll(page: Int, pageSize: Int = 20): Page<Ads> {
        val pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        return repository.findAll(pageable)
    }

    fun findAll(page: Int, pageSize: Int = 20, categoryId: Long): Page<Ads> {
        val pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))
        return repository.findAllByCategoryId(categoryId, pageable)
    }

    fun saveAll(values: List<Ads>) {
        repository.saveAll(values)
    }

    fun findById(id: Long): Ads? {
        return repository.findByIdOrNull(id)
    }

    fun findCategoriesWithAdsCount(searchText: String, cityId: Long): List<CategoriesOfAds> {
        return repository.findCategoriesWithAdsCount(searchText, cityId = cityId).map {
            CategoriesOfAds(
                categoryName = it[0] as String,
                categoryId = it[1] as Long,
                adsCount = it[2] as Long,
                adsTitle = it[3] as String
            )
        }
    }

    fun findAdsByFilter(
        title: String?,
        cityId: Long?,
        categoryId: Long?,
        neighborhoodId: Long?,
        page: Int,
        parameterAnswerRequests: List<ParameterAnswerRequest>?,
        price: String?,
        pageSize: Int = 20,
    ): Page<Ads> {
        val pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))

        // 10000 - 250000
        val minPrice = price?.split(" - ")?.firstOrNull()
        val maxPrice = price?.split(" - ")?.lastOrNull()

        return repository.findAdsByFilter(
            title = title,
            cityId = cityId,
            categoryId = categoryId,
            minPrice = if (minPrice.isNullOrEmpty()) null else minPrice.toDouble(),
            maxPrice = if (maxPrice.isNullOrEmpty()) null else maxPrice.toDouble(),
            parametersIds = parameterAnswerRequests?.map { it.parameterId },
            answers = parameterAnswerRequests?.map { it.answer },
            neighborhoodId = neighborhoodId,
            pageable = pageable
        )
    }


}