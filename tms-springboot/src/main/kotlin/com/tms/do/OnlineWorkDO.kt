package com.tms.`do`

data class OnlineWorkDO(
        val video: String,
        val courses: Iterable<Int>,
        val date: String
)