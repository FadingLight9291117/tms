package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId

data class Course(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var classId: Int,
        var week: String,
        var time: String,
        var date: String?,
        var enable: Int
)