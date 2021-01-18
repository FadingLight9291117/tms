package com.tms.controller

import com.tms.service.ClassInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/classInfo")
@CrossOrigin("*")
class ClassInfoController @Autowired constructor(val classInfoService: ClassInfoService) {
    @GetMapping("/all")
    fun allClassInfo() = classInfoService.allClasses().sortedByDescending { it.date }

    @GetMapping("/coachesByClassId/{id}")
    fun classCoaches(@PathVariable("id") id: Int) = classInfoService.classCoaches(id)

    @GetMapping("/studentsByClassId/{id}")
    fun classStudents(@PathVariable("id") id: Int) = classInfoService.classStudents(id)

    @GetMapping("/coursesByClassId/{id}")
    fun classCourse(@PathVariable("id") id: Int) = classInfoService.courseOfClass(id)

    @GetMapping("/classId/{id}")
    fun getClassInfo(@PathVariable("id") id: Int) = classInfoService.getClassInfo(id)
}