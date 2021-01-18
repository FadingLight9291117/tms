package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("class")
data class CClass(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var name: String,
        var num: Int = 0,
        var coachNum: Int = 0,
        var info: String?,
        var date: String
)
