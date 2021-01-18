package com.tms.controller

import com.tms.`do`.LoginDO
import com.tms.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 登录
 */
@RestController
@RequestMapping("/login")
@CrossOrigin("*")
class LoginController @Autowired constructor(val loginService: LoginService) {
    @PostMapping
    fun login(@RequestBody loginDO: LoginDO) = loginService.login(loginDO)

    @PostMapping("/auth")
    fun auth(@RequestBody loginDO: LoginDO?) = if (loginDO != null) loginService.auth(loginDO) else false
}