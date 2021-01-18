package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("sp")
data class SP(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var studentId: Int,
        var parentId: Int
)