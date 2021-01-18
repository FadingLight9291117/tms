package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("admin")
data class Admin(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var phone: Long?,
        var password: String,
        var name: String,
        var picture: String?
)
