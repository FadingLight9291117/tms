package com.tms.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.tms.entity.Onlinework
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Component
@Mapper
interface OnlineWorkMapper : BaseMapper<Onlinework>