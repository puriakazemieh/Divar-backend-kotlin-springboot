package com.kazemieh.divar

import com.kazemieh.divar.core.category.service.CategoryService
import com.kazemieh.divar.core.location.service.CityService
import com.kazemieh.divar.core.location.service.NeighborhoodService
import com.kazemieh.divar.core.location.service.ProvinceService
import com.kazemieh.divar.core.parameter.service.ParameterService
import com.kazemieh.divar.utils.provider.CategoryDataProvider
import com.kazemieh.divar.utils.provider.LocationDataProvider
import com.kazemieh.divar.utils.provider.ParameterDataProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class DivarApplication

fun main(args: Array<String>) {
    val context = runApplication<DivarApplication>(*args)
    initLocation(context)
    initCategory(context)
    initParameters(context)
}

fun initLocation(context: ConfigurableApplicationContext) {
    val tiple = LocationDataProvider.getData()

    val provinceService = context.getBean(ProvinceService::class.java)
    val cityService = context.getBean(CityService::class.java)
    val neighborhoodService = context.getBean(NeighborhoodService::class.java)

    if (neighborhoodService.count() < 1) {
        provinceService.saveAll(tiple.first)
        cityService.saveAll(tiple.second)
        neighborhoodService.saveAll(tiple.third)
    }

}


fun initCategory(context: ConfigurableApplicationContext) {
    val service = context.getBean(CategoryService::class.java)
//    service.repository.deleteAll()
    if (service.count() < 1) {
        service.saveAll(CategoryDataProvider.getData())
    }
}

fun initParameters(context: ConfigurableApplicationContext) {
    val service = context.getBean(ParameterService::class.java)
    val categories = context.getBean(CategoryService::class.java).findAll()
    if (service.count() < 1)
        service.saveAll(ParameterDataProvider.getParameters(categories))
}