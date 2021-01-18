package com.tms.service.serviceImpl

import com.tms.entity.Comment
import com.tms.entity.Onlinework
import com.tms.entity.Studentwork
import com.tms.mapper.CommentMapper
import com.tms.mapper.OnlineWorkMapper
import com.tms.mapper.StudentWorkMapper
import com.tms.service.OnlineworkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OnlineworkServiceImpl @Autowired constructor(
        val onlineWorkMapper: OnlineWorkMapper,
        val studentWorkMapper: StudentWorkMapper,
        val commentMapper: CommentMapper) : OnlineworkService {

    override fun publish(onlinework: Onlinework): Int {
        return onlineWorkMapper.insert(onlinework)
    }

    override fun submitWork(studentwork: Studentwork): Int {
        return studentWorkMapper.insert(studentwork)
    }

    override fun comment(comment: Comment): Int {
        return commentMapper.insert(comment)
    }

}