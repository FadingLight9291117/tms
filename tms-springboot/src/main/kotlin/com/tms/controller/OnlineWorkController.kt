package com.tms.controller

import com.tms.`do`.OnlineWorkDO
import com.tms.entity.Comment
import com.tms.entity.Onlinework
import com.tms.entity.Studentwork
import com.tms.service.OnlineworkService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 模块3.1 线上作业
 */
@RestController
@RequestMapping("/onlineWork")
@CrossOrigin("*")
class OnlineWorkController @Autowired constructor(val onlineWorkService: OnlineworkService) {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/publish")
    fun publish(@RequestBody onlineWorkDO: OnlineWorkDO) = try {
        onlineWorkDO.courses
                .asSequence()
                .map { Onlinework(null, it, onlineWorkDO.video, onlineWorkDO.date) }
                .forEach { onlineWorkService.publish(it) }
        true
    } catch (error: Error) {
        logger.error(error.message)
        false
    }

    @PostMapping("/submit")
    fun submit(@RequestBody studentWork: Studentwork) = onlineWorkService.submitWork(studentWork)

    @PostMapping("/comment")
    fun comment(@RequestBody comment: Comment) = onlineWorkService.comment(comment)
}