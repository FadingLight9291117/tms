package com.tms.controller

import com.tms.entity.News
import com.tms.service.NewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/news")
@CrossOrigin("*")
class NewsController @Autowired constructor(val newsService: NewsService) {
    @GetMapping("/all")
    fun all() = newsService.all().sortedByDescending { it.date }.toList()

    @PostMapping("/add")
    fun add(@RequestBody news: News) = newsService.add(news)

    @DeleteMapping("/rm/{id}")
    fun rm(@PathVariable("id") id: Int) = newsService.remove(id)

    @DeleteMapping("/rmAll")
    fun rmAll() = newsService.removeAll()
}