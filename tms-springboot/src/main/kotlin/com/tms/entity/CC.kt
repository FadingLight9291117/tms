package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("cc")
data class CC(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var classId: Int,
        var coachId: Int
)
