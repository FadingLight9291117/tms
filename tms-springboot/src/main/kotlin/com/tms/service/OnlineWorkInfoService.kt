package com.tms.service

import com.tms.entity.Comment
import com.tms.entity.Onlinework
import com.tms.entity.Studentwork

/**
 * 模块4.4 线上作业信息
 */
interface OnlineWorkInfoService {
    fun all(): List<Onlinework>
    fun courseWork(courseId: Int): List<Onlinework>
    fun onlineWorkById(onlineWorkId: Int): Onlinework
    fun allStudentWorks(onlineWorkId: Int): List<Studentwork>
    fun comments(studentworkId: Int): List<Comment>
}