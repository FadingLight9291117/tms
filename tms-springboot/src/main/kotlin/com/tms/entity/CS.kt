package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("cs")
data class CS(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var classId: Int,
        var studentId: Int
)
