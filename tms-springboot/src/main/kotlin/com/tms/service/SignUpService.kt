package com.tms.service

import com.tms.`do`.AffirmDO
import com.tms.`do`.StudentSignUpDO
import com.tms.entity.Admin
import com.tms.entity.Coach

/**
 * 模块1.1 用户注册报名
 * */
interface SignUpService {
    fun addStudent(studentSignUpDo: StudentSignUpDO): Int
    fun addCoach(coach: Coach): Int
    fun addAdmin(admin: Admin): Int
    fun affirm(affirmDO: AffirmDO): Int
}