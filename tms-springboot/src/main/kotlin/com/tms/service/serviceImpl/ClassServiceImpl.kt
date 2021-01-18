package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.tms.entity.CC
import com.tms.entity.CClass
import com.tms.entity.CS
import com.tms.mapper.*
import com.tms.service.ClassService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClassServiceImpl @Autowired constructor(val cClassMapper: CClassMapper,
                                              val csMapper: CSMapper,
                                              val ccMapper: CCMapper) : ClassService {
    // 添加班级
    override fun addClass(cClass: CClass) = cClassMapper.insert(cClass)

    @Transactional
    override fun rmClass(classId: Int): Int {
        cClassMapper.delete(QueryWrapper<CClass>().eq("Id", classId))
        csMapper.delete(QueryWrapper<CS>().eq("class_id", classId))
        ccMapper.delete(QueryWrapper<CC>().eq("class_id", classId))
        return 1
    }

    override fun renameClass(classId: Int, newName: String): Int {
        val cClass = cClassMapper.selectOne(QueryWrapper<CClass>().eq("id", classId))
        cClass.name = newName
        return cClassMapper.updateById(cClass)
    }

    @Transactional
    override fun addStudents(classId: Int, students: List<Int>): Int {
        students.forEach {
            csMapper.insert(CS(null, classId, it))
        }
        // 增加班级人数
        val cClass = cClassMapper.selectOne(QueryWrapper<CClass>().eq("id", classId))
        cClass.num += students.size
        cClassMapper.updateById(cClass)
        return 1
    }

    @Transactional
    override fun rmStudents(classId: Int, students: List<Int>): Int {
        students.forEach {
            csMapper.delete(QueryWrapper<CS>().allEq(mapOf("class_id" to classId, "student_id" to it)))
        }
        // 减少班级人数
        val cClass = cClassMapper.selectOne(QueryWrapper<CClass>().eq("id", classId))
        cClass.num -= students.size
        cClassMapper.updateById(cClass)
        return 1
    }

    @Transactional
    override fun mvStudents(oldClassId: Int, newClassId: Int, students: List<Int>): Int {
        students.forEach {
            val cClass = csMapper.selectOne(QueryWrapper<CS>().allEq(mapOf("class_id" to oldClassId, "student_id" to it)))
            cClass.classId = newClassId
            csMapper.updateById(cClass)
        }
        // 改变班级人数
        val oldClass = cClassMapper.selectById(oldClassId)
        val newClass = cClassMapper.selectById(newClassId)
        oldClass.coachNum -= students.size
        newClass.coachNum += students.size
        return 1
    }

    @Transactional
    override fun addCoaches(classId: Int, coaches: List<Int>): Int {
        coaches.forEach {
            ccMapper.insert(CC(null, classId, it))
        }
        // 增加教练人数
        val cClass = cClassMapper.selectById(classId)
        cClass.coachNum += coaches.size
        cClassMapper.updateById(cClass)
        return 1
    }

    @Transactional
    override fun rmCoaches(classId: Int, coaches: List<Int>): Int {
        coaches.forEach {
            ccMapper.delete(QueryWrapper<CC>().allEq(mapOf("class_id" to classId, "coach_id" to it)))
        }
        // 减少教练人数
        val cClass = cClassMapper.selectById(classId)
        cClass.coachNum += coaches.size
        cClassMapper.updateById(cClass)
        return 1
    }

    @Transactional
    override fun mvCoaches(oldClassId: Int, newClassId: Int, coaches: List<Int>): Int {
        coaches.forEach {
            val cClass = ccMapper.selectOne(QueryWrapper<CC>().allEq(mapOf("class_id" to oldClassId, "coach_id" to it)))
            cClass.classId = newClassId
            ccMapper.updateById(cClass)
        }
        // 改变教练人数
        val oldClass = cClassMapper.selectById(oldClassId)
        val newClass = cClassMapper.selectById(newClassId)
        oldClass.coachNum -= coaches.size
        newClass.coachNum += coaches.size
        return 1
    }

}