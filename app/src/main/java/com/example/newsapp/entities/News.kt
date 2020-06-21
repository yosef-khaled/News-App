package com.example.newsapp.entities

import java.io.Serializable

class News(
    val imgUrl: String = "",
    val newsUrl: String = "",
    val title: String = "",
    val publishedAt: String = "",
    val content: String = "",
    val author: String = "",
    val description: String = ""
) : Serializable