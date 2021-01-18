package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId

data class Parent(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var name: String,
        var phone: Long,
        var relation: String?
)