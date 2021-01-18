package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.tms.`do`.LoginDO
import com.tms.constant.User
import com.tms.entity.Admin
import com.tms.entity.Coach
import com.tms.entity.Student
import com.tms.mapper.AdminMapper
import com.tms.mapper.CoachMapper
import com.tms.mapper.StudentMapper
import com.tms.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl @Autowired constructor(val studentMapper: StudentMapper, val coachMapper: CoachMapper, val adminMapper: AdminMapper) : LoginService {

    fun queryStudent(phone: Long, password: String): Student? = studentMapper.selectOne(QueryWrapper<Student>().allEq(mapOf("phone" to phone, "password" to password, "affirm" to 1)))

    fun queryCoach(phone: Long, password: String): Coach? = coachMapper.selectOne(QueryWrapper<Coach>().allEq(mapOf("phone" to phone, "password" to password, "affirm" to 1)))

    fun queryAdmin(phone: Long, password: String): Admin? = adminMapper.selectOne(QueryWrapper<Admin>().allEq(mapOf("phone" to phone, "password" to password)))

    override fun login(loginDO: LoginDO): Any? = when (User.valueOf(loginDO.type.toUpperCase())) {
        User.ADMIN -> queryAdmin(loginDO.phone, loginDO.password)
        User.COACH -> queryCoach(loginDO.phone, loginDO.password)
        User.STUDENT -> queryStudent(loginDO.phone, loginDO.password)
    }

    override fun auth(loginDO: LoginDO): Boolean = this.login(loginDO) != null

}