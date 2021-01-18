package com.tms.controller

import com.tms.service.FileService
import com.tms.service.serviceImpl.FileServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/upload")
class UploadController @Autowired constructor(val fileService: FileService) {

    @PostMapping("/img")
    fun img(@RequestParam img: MultipartFile): ResponseEntity<Map<String, String>?> {
        val suffixes = arrayOf("jpg", "jpeg", "png")
        val fileSuffix = img.originalFilename?.split(".")?.last() ?: ""
        return if (fileSuffix in suffixes) {
            val url = fileService.saveImg("${System.nanoTime()}.${fileSuffix}", img)
            val result = mapOf("url" to url)
            ResponseEntity<Map<String, String>?>(result, HttpStatus.OK)
        } else {
            ResponseEntity<Map<String, String>?>(null, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/video")
    fun video(@RequestParam video: MultipartFile): ResponseEntity<Map<String, String>?> {
        val suffixes = arrayOf("mp4", "flv")
        val fileSuffix = video.originalFilename?.split(".")?.last() ?: ""
        return if (fileSuffix in suffixes) {
            val url = fileService.saveVideo("${System.nanoTime()}.${fileSuffix}", video)
            val result = mapOf("url" to url)
            ResponseEntity<Map<String, String>?>(result, HttpStatus.OK)
        } else {
            ResponseEntity<Map<String, String>?>(null, HttpStatus.BAD_REQUEST)
        }
    }
}