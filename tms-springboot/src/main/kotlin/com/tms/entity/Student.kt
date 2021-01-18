package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId

data class Student(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var phone: Long,
        var password: String,
        var name: String,
        var sex: String,
        var birthday: String?,
        var picture: String?,
        var level: String,
        var dan: String?,
        var date: String,
        var affirm: Int = 0
)