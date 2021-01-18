package com.tms.service

import com.tms.entity.Comment
import com.tms.entity.Onlinework
import com.tms.entity.Studentwork

/**
 * 模块3.1 线上作业
 */
interface OnlineworkService {
    fun publish(onlinework: Onlinework): Int
    fun submitWork(studentwork: Studentwork): Int
    fun comment(comment: Comment): Int
}