package com.tms.service.serviceImpl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.tms.entity.CC
import com.tms.entity.Coach
import com.tms.mapper.CCMapper
import com.tms.mapper.CoachMapper
import com.tms.service.CoachService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoachServiceImpl @Autowired constructor(val coachMapper: CoachMapper, val ccMapper: CCMapper) : CoachService {
    override fun all(): List<Coach> {
        return coachMapper.selectList(QueryWrapper<Coach>().eq("affirm", 1))
    }

    override fun getById(id: Int): Coach {
        return coachMapper.selectById(id)
    }

    override fun getByClassId(id: Int): List<Coach> {
        val coachIds = ccMapper.selectList(QueryWrapper<CC>().eq("class_id", id))
                .asSequence()
                .map { it.coachId }
                .toList()
        return if (coachIds.isEmpty()) emptyList() else coachMapper.selectBatchIds(coachIds)
    }

    override fun update(coach: Coach): Int {
        return coachMapper.updateById(coach)
    }

    override fun remove(id: Int): Int {
        return coachMapper.deleteById(id)
    }

    override fun candidates(): List<Coach> {
        return coachMapper.selectList(QueryWrapper<Coach>().eq("affirm", 0))
    }
}