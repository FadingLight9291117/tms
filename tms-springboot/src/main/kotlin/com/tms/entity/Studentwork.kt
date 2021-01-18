package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId

data class Studentwork(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var studentId: Int,
        @TableField("onlinework_id")
        var onlineWorkId: Int,
        var video: String,
        var date: String
)