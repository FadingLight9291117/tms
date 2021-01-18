package com.tms.service.serviceImpl

import com.tms.entity.News
import com.tms.mapper.NewsMapper
import com.tms.service.NewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NewsServiceImpl @Autowired constructor(val newsMapper: NewsMapper) : NewsService {
    override fun all(): List<News> = newsMapper.selectList(null)
    override fun add(news: News) = newsMapper.insert(news)
    override fun remove(id: Int) = newsMapper.deleteById(id)
    override fun removeAll() = newsMapper.delete(null)
}