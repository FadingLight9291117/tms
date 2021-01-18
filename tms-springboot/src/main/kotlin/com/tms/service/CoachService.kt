package com.tms.service

import com.tms.entity.Coach

/**
 * 模块4.2 教练信息管理
 */
interface CoachService {
    fun all(): List<Coach>
    fun getById(id: Int): Coach
    fun getByClassId(id: Int): List<Coach>
    fun update(coach: Coach): Int
    fun remove(id: Int): Int
    fun candidates(): List<Coach>
}