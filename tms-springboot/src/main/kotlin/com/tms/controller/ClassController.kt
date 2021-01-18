package com.tms.controller

import com.tms.`do`.ClassDO
import com.tms.`do`.ClassPeopleDO
import com.tms.`do`.MvPeopleDo
import com.tms.`do`.RenameDo
import com.tms.entity.CClass
import com.tms.service.ClassService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlin.reflect.jvm.internal.impl.name.ClassId

/**
 * 模块2.1 分班 2020.3.31
 * 测试 完成 2020.3.31
 */

/**
 * 存在问题待解决：学生分班可能会重复，即一个学生多个班。
 */
@RestController
@RequestMapping("/class")
@CrossOrigin(origins = ["*"])
class ClassController @Autowired constructor(val classService: ClassService) {

    @PostMapping("/addClassAndPeople")
    fun addClassAndPeople(@RequestBody classDO: ClassDO): Int {
        val (cClass, coaches, students) = classDO;
        classService.addClass(cClass)
        val classId: Int = cClass.id!!
        classService.addStudents(classId, students)
        classService.addCoaches(classId, coaches)
        return 1
    }

    @PostMapping("/addClass")
    fun addClass(@RequestBody cClass: CClass) = classService.addClass(cClass)

    @DeleteMapping("/rmClass/{id}")
    fun rmClass(@PathVariable("id") classId: Int) = classService.rmClass(classId)

    @PostMapping("/rename")
    fun renameClass(@RequestBody renameDo: RenameDo) = classService.renameClass(renameDo.classId, renameDo.newName)

    @PostMapping("/addStudents")
    fun addStudents(@RequestBody peopleDO: ClassPeopleDO) = classService.addStudents(peopleDO.classId, peopleDO.people)

    @DeleteMapping("/rmStudents")
    fun rmStudents(@RequestBody peopleDO: ClassPeopleDO) = classService.rmStudents(peopleDO.classId, peopleDO.people)

    @PostMapping("/mvStudents")
    fun mvStudents(@RequestBody mvPeopleDo: MvPeopleDo) = classService.mvStudents(mvPeopleDo.oldClassId, mvPeopleDo.newClassId, mvPeopleDo.people)

    @PostMapping("/addCoaches")
    fun addCoaches(@RequestBody peopleDO: ClassPeopleDO) = classService.addCoaches(peopleDO.classId, peopleDO.people)

    @DeleteMapping("/rmCoaches")
    fun rmCoaches(@RequestBody peopleDO: ClassPeopleDO) = classService.rmCoaches(peopleDO.classId, peopleDO.people)

    @PostMapping("/mvCoaches")
    fun mvCoaches(@RequestBody mvPeopleDo: MvPeopleDo) = classService.mvCoaches(mvPeopleDo.oldClassId, mvPeopleDo.newClassId, mvPeopleDo.people)
}