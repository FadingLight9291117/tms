package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId

data class Onlinework(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var courseId: Int,
        var video: String,
        var date: String
)

