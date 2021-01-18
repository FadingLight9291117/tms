package com.tms.service

import com.tms.entity.Parent
import com.tms.entity.Student

/**
 * 模块4.1 学生信息管理模块 2020.4.3
 */
interface StudentService {
    fun all(): List<Student>
    fun byClassId(classId: Int): List<Student>
    fun byStudentId(studentId: Int): Student
    fun allCandidates(): List<Student>
    fun updateStudent(student: Student): Int
    fun rmStudent(id: Int): Int
    fun allParent(): List<Parent>
    fun parentById(parentId: Int): Parent
    fun parentByChildId(childId: Int): Parent
    fun rmParent(id: Int): Int
}