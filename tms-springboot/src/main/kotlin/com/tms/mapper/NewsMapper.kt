package com.tms.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.tms.entity.News
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Component
@Mapper
interface NewsMapper : BaseMapper<News>