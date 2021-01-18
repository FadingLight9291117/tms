package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.tms.entity.CS
import com.tms.entity.Parent
import com.tms.entity.SP
import com.tms.entity.Student
import com.tms.mapper.CSMapper
import com.tms.mapper.ParentMapper
import com.tms.mapper.SPMapper
import com.tms.mapper.StudentMapper
import com.tms.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl @Autowired constructor(
        val studentMapper: StudentMapper,
        val parentMapper: ParentMapper,
        val spMapper: SPMapper,
        val csMapper: CSMapper) : StudentService {
    override fun all(): List<Student> {
        return studentMapper.selectList(QueryWrapper<Student>().eq("affirm", 1))
    }

    override fun byClassId(classId: Int): List<Student> {
        val css = csMapper.selectList(QueryWrapper<CS>().eq("class_id", classId))
        val ids = css
                .asSequence()
                .map { it.studentId }
                .toList()


        return if (ids.isEmpty()) emptyList() else studentMapper.selectBatchIds(ids)
    }

    override fun byStudentId(studentId: Int): Student {
        return studentMapper.selectById(studentId)
    }

    override fun allCandidates(): List<Student> {
        return studentMapper.selectList(QueryWrapper<Student>().eq("affirm", 0))
    }

    override fun updateStudent(student: Student): Int {
        return studentMapper.updateById(student)
    }

    @Transactional
    override fun rmStudent(id: Int): Int {
        csMapper.delete(QueryWrapper<CS>().eq("student_id", id))
        spMapper.delete(QueryWrapper<SP>().eq("student_id", id))
        return studentMapper.deleteById(id)
    }

    override fun allParent(): List<Parent> {
        return parentMapper.selectList(null)
    }

    override fun parentById(parentId: Int): Parent {
        return parentMapper.selectById(parentId)
    }

    override fun parentByChildId(childId: Int): Parent {
        val parentId = spMapper.selectOne(QueryWrapper<SP>().eq("student_id", childId)).parentId
        return parentById(parentId)
    }

    @Transactional
    override fun rmParent(id: Int): Int {
        spMapper.delete(QueryWrapper<SP>().eq("parent_id", id))
        return parentMapper.deleteById(id)
    }
}