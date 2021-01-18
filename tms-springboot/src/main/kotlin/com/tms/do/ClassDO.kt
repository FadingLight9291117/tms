package com.tms.`do`

import com.tms.entity.CClass

data class ClassDO(
        val cClass: CClass,
        val coaches: List<Int>,
        val students: List<Int>
)