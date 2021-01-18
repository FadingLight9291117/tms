package com.tms.service

import com.tms.entity.Course

/**
 * 模块2.2 排课
 */
interface CourseService {
    fun all(): List<Course>
    fun addCourse(course: Course): Int
    fun rmCourse(id: Int): Int
    fun disableCourse(id: Int?): Int
    fun enableCourse(id: Int): Int
}