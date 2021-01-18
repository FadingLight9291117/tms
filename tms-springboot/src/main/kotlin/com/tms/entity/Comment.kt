package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId

data class Comment(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        @TableField("studentwork_id")
        var studentWorkId: Int,
        var coachId: Int,
        var content: String,
        var date: String
)