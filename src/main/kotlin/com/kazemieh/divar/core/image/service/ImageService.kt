package com.kazemieh.divar.core.image.service

import com.kazemieh.divar.core.adds.entity.Ads
import com.kazemieh.divar.core.image.entity.Image
import com.kazemieh.divar.core.image.repostory.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.io.path.pathString

@Service
class ImageService(
    val repository: ImageRepository
) {

    private val uploadDir = "uploads/"

    init {
        val dir = File(uploadDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }


    fun save(file: MultipartFile, ads: Ads): Image {
        val fileName = "${System.currentTimeMillis()}_${file.originalFilename}.jpg"
        val filePath = Paths.get("$uploadDir$fileName")
        Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)
        val image = Image(path = filePath.pathString, ads = ads)
        return repository.save(image)
    }

    fun saveAll(files: List<MultipartFile>, ads: Ads): List<Image> {
        val images = mutableListOf<Image>()
        files.forEach { file ->
            val fileName = "${System.currentTimeMillis()}_${file.originalFilename}.jpg"
            val filePath = Paths.get("$uploadDir$fileName")
            Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)
            images.add(Image(path = filePath.pathString, ads = ads))
        }
        return repository.saveAll(images)
    }


}