package com.tms.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import org.springframework.data.relational.core.mapping.Table

@Table("coach")
data class Coach(
        @TableId(type = IdType.AUTO)
        var id: Int?,
        var phone: Long,
        var password: String,
        var name: String,
        var sex: String,
        var birthday: String,
        var picture: String?,
        var dan: String,
        var intro: String,
        var date: String,
        var affirm: Int
)