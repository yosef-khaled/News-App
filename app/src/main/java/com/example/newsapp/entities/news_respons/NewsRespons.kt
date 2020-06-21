package com.example.newsapp.entities.news_respons

data class NewsRespons(
    var articles: List<Article>?,
    var status: String?,
    var totalResults: Int?
)