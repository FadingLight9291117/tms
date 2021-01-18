package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.tms.entity.*
import com.tms.mapper.*
import com.tms.service.ClassInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClassInfoServiceImpl @Autowired constructor(
        val ccMapper: CCMapper,
        val csMapper: CSMapper,
        val cClassMapper: CClassMapper,
        val courseMapper: CourseMapper,
        val coachMapper: CoachMapper,
        val studentMapper: StudentMapper) : ClassInfoService {
    override fun classCoaches(classId: Int): List<Coach> {
        val coachIds = ccMapper.selectList(QueryWrapper<CC>().eq("class_id", classId))
                .map { it.coachId }
                .toList()
        return coachMapper.selectBatchIds(coachIds)
    }

    override fun classStudents(classId: Int): List<Student> {
        val studentIds = csMapper.selectList(QueryWrapper<CS>().eq("class_id", classId))
                .map { it.studentId }
                .toList()
        return studentMapper.selectBatchIds(studentIds)
    }

    override fun allClasses(): List<CClass> {
        return cClassMapper.selectList(null)
    }

    override fun getClassInfo(classId: Int): CClass {
        return cClassMapper.selectById(classId)
    }

    override fun courseOfClass(classId: Int): List<Course> {
        return courseMapper.selectList(QueryWrapper<Course>().eq("class_id", classId))
    }

}