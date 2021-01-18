package com.tms.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.tms.entity.Student
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Component
@Mapper
interface StudentMapper : BaseMapper<Student>