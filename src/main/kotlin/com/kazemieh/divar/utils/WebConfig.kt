package com.kazemieh.divar.utils

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class WebConfig: WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:uploads")
        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
    }
}