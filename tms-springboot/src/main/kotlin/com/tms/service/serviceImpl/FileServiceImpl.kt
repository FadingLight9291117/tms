package com.tms.service.serviceImpl

import com.tms.service.FileService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.websocket.server.ServerApplicationConfig

@Service
class FileServiceImpl : FileService {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    val videoPath = ResourceUtils.getURL("classpath:").path + "static/upload/video"
    val imgPath = ResourceUtils.getURL("classpath:").path + "static/upload/img"

    override fun saveVideo(name: String, file: MultipartFile): String {
        val path = File(videoPath, name)
        if (!path.exists()) path.mkdirs()
        file.transferTo(path)
        logger.info(path.absolutePath)
        return "http://localhost:80/upload/video/$name"
    }

    override fun saveImg(name: String, file: MultipartFile): String {
        val path = File(imgPath, name)
        if (!path.exists()) path.mkdirs()
        file.transferTo(path)
        logger.info(path.absolutePath)
        return "http://localhost:80/upload/img/$name"
    }

    override fun getAllVideoURL(): Iterable<String> =
            File(videoPath).list()
                    ?.map { "http://localhost:80/upload/video/$it" }
                    ?.toList()
                    ?: emptyList()

    override fun getAllImgURL(): Iterable<String> =
            File(imgPath).list()
                    ?.map { "http://localhost:80/upload/video/$it" }
                    ?.toList()
                    ?: emptyList()
}