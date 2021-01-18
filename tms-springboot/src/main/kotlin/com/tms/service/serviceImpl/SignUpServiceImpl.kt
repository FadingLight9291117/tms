package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper
import com.tms.`do`.AffirmDO
import com.tms.`do`.StudentSignUpDO
import com.tms.entity.Admin
import com.tms.entity.Coach
import com.tms.entity.SP
import com.tms.entity.Student
import com.tms.mapper.*
import com.tms.service.SignUpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpServiceImpl @Autowired constructor(
        val studentMapper: StudentMapper,
        val coachMapper: CoachMapper,
        val adminMapper: AdminMapper,
        val parentMapper: ParentMapper,
        val spMapper: SPMapper) : SignUpService {

    // 添加学生
    override fun addStudent(studentSignUpDo: StudentSignUpDO): Int {
        val (student, parent) = studentSignUpDo
        studentMapper.insert(student)
        if (parent != null) {
            parentMapper.insert(parent)
            val sp = when {
                student.id != null && parent.id != null -> SP(null, studentId = student.id!!, parentId = parent.id!!)
                else -> throw error("student.id 或 parent.id 为空")
            }
            spMapper.insert(sp)
        }
        return 1
    }

    // 添加教练
    override fun addCoach(coach: Coach) = coachMapper.insert(coach)

    // 添加管理员
    override fun addAdmin(admin: Admin) = adminMapper.insert(admin)

    // 确认候选人
    override fun affirm(affirmDO: AffirmDO): Int {
        val (students, coaches) = affirmDO
        students.forEach {
            val student = studentMapper.selectById(it)
            student.affirm = 1
            studentMapper.updateById(student)
        }
        coaches.forEach {
            val coach = coachMapper.selectById(it)
            coach.affirm = 1
            coachMapper.updateById(coach)
        }
        return 1
    }
}