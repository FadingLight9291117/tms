package com.tms.controller

import com.tms.entity.Coach
import com.tms.service.CoachService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coach")
@CrossOrigin("*")
class CoachController @Autowired constructor(val coachService: CoachService) {
    @GetMapping("/all")
    fun all() = coachService.all().sortedByDescending { it.date }

    @GetMapping("/allExceptClass/{id}")
    fun allExceptClass(@PathVariable("id") classId: Int) = coachService.all().subtract(coachService.getByClassId(classId))

    @GetMapping("/coachId/{id}")
    fun getById(@PathVariable("id") id: Int) = coachService.getById(id)

    @GetMapping("/classId/{id}")
    fun getByClassId(@PathVariable("id") id: Int) = coachService.getByClassId(id)

    @PostMapping("/update")
    fun update(@RequestBody coach: Coach) = coachService.update(coach)

    @DeleteMapping("/rm/{id}")
    fun remove(@PathVariable("id") id: Int) = coachService.remove(id)

    @GetMapping("/candidates")
    fun candidates() = coachService.candidates()
}