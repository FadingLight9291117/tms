package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId

data class News(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var title: String,
        var content: String,
        var date: String
)