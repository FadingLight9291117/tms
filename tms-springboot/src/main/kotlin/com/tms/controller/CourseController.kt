package com.tms.controller

import com.tms.`do`.CourseDO
import com.tms.`do`.CourseAndClassNameDO
import com.tms.entity.Course
import com.tms.service.ClassInfoService
import com.tms.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


/**
 * 模块2.2 排课
 */
@RestController
@RequestMapping("/course")
@CrossOrigin(origins = ["*"])
class CourseController @Autowired constructor(val courseService: CourseService, val classInfoService: ClassInfoService) {
    // 所有课程
    @GetMapping("/all")
    fun all() = courseService.all().filter { it.enable == 1 }

    // 所有课程id + 班级名
    @GetMapping("/allAndClassName")
    fun allCourseIdAndClassName(): List<CourseAndClassNameDO> {
        val allClasses = classInfoService.allClasses()
        return courseService.all()
                .asSequence()
                .filter { it.enable == 1 }
                .map { e ->
                    CourseAndClassNameDO(
                            id = e.id!!,
                            week = e.week,
                            time = e.time,
                            className = allClasses.find { it.id == e.classId }!!.name)
                }
                .toList()
    }

    // 添加课程
    @PostMapping("/add")
    fun add(@RequestBody courseDO: CourseDO): Int {
        courseService.addCourse(Course(
                id = null,
                classId = courseDO.classId,
                week = courseDO.week,
                time = courseDO.time,
                date = courseDO.date,
                enable = 1
        ))
        return 1
    }

    // 删除课程
    @DeleteMapping("/rm/{id}")
    fun rm(@PathVariable("id") id: Int): Int {
        courseService.rmCourse(id)
        return 1
    }

    // 清除所有课程数据
    @DeleteMapping("/clear")
    fun clear() = courseService.disableCourse(null)

    // 临时取消课程
    @PostMapping("/disable")
    fun disable(@RequestBody courses: List<Int>): Int {
        courses.forEach {
            courseService.disableCourse(it)
        }
        return 1
    }

    // 取消临时取消
    @PostMapping("/enable")
    fun enable(@RequestBody courses: List<Int>): Int {
        courses.forEach {
            courseService.enableCourse(it)
        }
        return 1
    }
}