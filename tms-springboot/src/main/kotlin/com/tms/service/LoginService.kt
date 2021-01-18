package com.tms.service

import com.tms.`do`.LoginDO

interface LoginService {
    fun login(loginDO: LoginDO): Any?
    fun auth(loginDO: LoginDO): Boolean
}