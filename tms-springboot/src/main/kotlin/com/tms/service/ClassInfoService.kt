package com.tms.service

import com.tms.entity.CClass
import com.tms.entity.Coach
import com.tms.entity.Course
import com.tms.entity.Student

/**
 * 模块4.3 课堂信息管理模块
 */
interface ClassInfoService {
    fun classCoaches(classId: Int): List<Coach>
    fun classStudents(classId: Int): List<Student>
    fun allClasses(): List<CClass>
    fun getClassInfo(classId: Int): CClass
    fun courseOfClass(classId: Int): List<Course>
}