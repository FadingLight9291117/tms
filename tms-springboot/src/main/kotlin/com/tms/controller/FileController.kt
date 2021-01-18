package com.tms.controller

import com.tms.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("file")
class FileController @Autowired constructor(val fileService: FileService) {
    @GetMapping("/videos")
    fun videos() = fileService.getAllVideoURL()
}