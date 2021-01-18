package com.tms.controller

import com.tms.service.OnlineWorkInfoService
import com.tms.service.OnlineworkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/onlineWorkInfo")
@CrossOrigin("*")
class OnlineWorkInfoController @Autowired constructor(val onlineWorkInfoService: OnlineWorkInfoService) {
    @GetMapping("/all")
    fun all() = onlineWorkInfoService.all().sortedByDescending { it.date }

    @GetMapping("/courseWork/{id}")
    fun courseWork(@PathVariable id: Int) = onlineWorkInfoService.courseWork(id)

    @GetMapping("/onlineWorkId/{id}")
    fun onlineWorkById(@PathVariable("id") id: Int) = onlineWorkInfoService.onlineWorkById(id)

    @GetMapping("/allStudentWorks/{id}")
    fun allStudentWorks(@PathVariable("id") id: Int) = onlineWorkInfoService.allStudentWorks(id).sortedByDescending { it.date }

    @GetMapping("/comments/{id}")
    fun comments(@PathVariable("id") courseId: Int) = onlineWorkInfoService.comments(courseId)
}