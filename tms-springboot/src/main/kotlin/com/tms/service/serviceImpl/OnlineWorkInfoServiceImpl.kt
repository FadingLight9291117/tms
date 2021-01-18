package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.tms.entity.Comment
import com.tms.entity.Onlinework
import com.tms.entity.Studentwork
import com.tms.mapper.CommentMapper
import com.tms.mapper.OnlineWorkMapper
import com.tms.mapper.StudentWorkMapper
import com.tms.service.OnlineWorkInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OnlineWorkInfoServiceImpl @Autowired constructor(
        val onlineWorkMapper: OnlineWorkMapper,
        val studentWorkMapper: StudentWorkMapper,
        val commentMapper: CommentMapper) : OnlineWorkInfoService {
    override fun all(): List<Onlinework> {
        return onlineWorkMapper.selectList(null)
    }

    override fun courseWork(courseId: Int): List<Onlinework> {
        return onlineWorkMapper.selectList(QueryWrapper<Onlinework>().eq("course_id", courseId))
    }

    override fun onlineWorkById(onlineWorkId: Int): Onlinework {
        return onlineWorkMapper.selectById(onlineWorkId)
    }

    override fun allStudentWorks(onlineWorkId: Int): List<Studentwork> {
        return studentWorkMapper.selectList(QueryWrapper<Studentwork>().eq("onlinework_id", onlineWorkId))
    }

    override fun comments(studentworkId: Int): List<Comment> {
        return commentMapper.selectList(QueryWrapper<Comment>().eq("studentwork_id", studentworkId))
    }
}