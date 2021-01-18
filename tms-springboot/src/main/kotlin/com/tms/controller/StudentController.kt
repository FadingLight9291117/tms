package com.tms.controller

import com.tms.entity.Student
import com.tms.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
class StudentController @Autowired constructor(val studentService: StudentService) {
    @GetMapping("/all")
    fun all() = studentService.all().sortedByDescending { it.date }

    @GetMapping("/allExceptClass/{id}")
    fun allExceptClass(@PathVariable("id") classId: Int) = studentService.all().subtract(studentService.byClassId(classId))

    @GetMapping("/classId/{id}")
    fun byClassId(@PathVariable("id") id: Int) = studentService.byClassId(id)

    @GetMapping("/studentId/{id}")
    fun byStudentId(@PathVariable("id") id: Int) = studentService.byStudentId(id)

    @GetMapping("/candidates")
    fun allCandidate() = studentService.allCandidates()

    @PostMapping("/updateStudent")
    fun updateStudent(@RequestBody student: Student) = studentService.updateStudent(student)

    @DeleteMapping("/rmStudent/{id}")
    fun rmStudent(@PathVariable("id") id: Int) = studentService.rmStudent(id)

    @GetMapping("/parents")
    fun allParent() = studentService.allParent()

    @GetMapping("/parentId/{id}")
    fun parentById(@PathVariable("id") id: Int) = studentService.parentById(id)

    @GetMapping("/childId/{id}")
    fun parentByChildId(@PathVariable("id") id: Int) = studentService.parentByChildId(id)

    @DeleteMapping("/rmParent/{id}")
    fun rmParent(@PathVariable("id") id: Int) = studentService.rmParent(id)
}