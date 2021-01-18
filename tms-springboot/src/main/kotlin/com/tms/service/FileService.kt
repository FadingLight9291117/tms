package com.tms.service

import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun saveVideo(name: String, file: MultipartFile): String
    fun saveImg(name: String, file: MultipartFile): String
    fun getAllVideoURL(): Iterable<String>
    fun getAllImgURL(): Iterable<String>
}