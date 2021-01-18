package com.tms.service

import com.tms.entity.News

interface NewsService {
    fun all(): List<News>
    fun add(news: News): Int
    fun remove(id: Int): Int
    fun removeAll(): Int
}