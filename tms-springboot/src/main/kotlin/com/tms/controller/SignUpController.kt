package com.tms.controller

import com.tms.`do`.AffirmDO
import com.tms.`do`.StudentSignUpDO
import com.tms.entity.Admin
import com.tms.entity.Coach
import com.tms.service.SignUpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 模块1.1 报名系统-用户报名 2020.3.29
 * 测试 部分完成 2020.3.29
 */
@RestController
@RequestMapping("/signUp")
@CrossOrigin(origins = ["*"])
class SignUpController @Autowired constructor(val signUpService: SignUpService) {
    @PostMapping("/student")
    fun student(@RequestBody data: StudentSignUpDO) = signUpService.addStudent(studentSignUpDo = data)

    @PostMapping("/coach")
    fun coach(@RequestBody coach: Coach) = signUpService.addCoach(coach = coach)

    @PostMapping("/admin")
    fun admin(@RequestBody admin: Admin) = signUpService.addAdmin(admin = admin)

    @PostMapping("/affirm")
    fun affirm(@RequestBody affirmDO: AffirmDO) = signUpService.affirm(affirmDO = affirmDO)
}