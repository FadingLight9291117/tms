package com.tms.service

import com.tms.entity.CClass

/**
 * 模块2.1 分班
 */
interface ClassService {
    fun addClass(cClass: CClass): Int
    fun rmClass(classId: Int): Int
    fun renameClass(classId: Int, newName: String): Int
    fun addStudents(classId: Int, students: List<Int>): Int
    fun rmStudents(classId: Int, students: List<Int>): Int
    fun mvStudents(oldClassId: Int, newClassId: Int, students: List<Int>): Int
    fun addCoaches(classId: Int, coaches: List<Int>): Int
    fun rmCoaches(classId: Int, coaches: List<Int>): Int
    fun mvCoaches(oldClassId: Int, newClassId: Int, coaches: List<Int>): Int
}