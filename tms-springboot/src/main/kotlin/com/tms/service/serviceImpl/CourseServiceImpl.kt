package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.tms.entity.Course
import com.tms.mapper.CourseMapper
import com.tms.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl @Autowired constructor(val courseMapper: CourseMapper) : CourseService {
    override fun all(): List<Course> = courseMapper.selectList(null)
    override fun addCourse(course: Course) = courseMapper.insert(course)

    override fun rmCourse(id: Int) = courseMapper.deleteById(id)

    override fun disableCourse(id: Int?): Int {
        if (id != null) {
            val course = courseMapper.selectOne(QueryWrapper<Course>().eq("id", id))
            course.enable = 0
            return courseMapper.updateById(course)
        } else {
            val courses = courseMapper.selectList(QueryWrapper<Course>().eq("enable", 1))
            courses.forEach {
                it.enable = 0
                courseMapper.updateById(it)
            }
        }
        return 1
    }

    override fun enableCourse(id: Int): Int {
        val course = courseMapper.selectOne(QueryWrapper<Course>().eq("id", id))
        course.enable = 1;
        return courseMapper.updateById(course)
    }

}