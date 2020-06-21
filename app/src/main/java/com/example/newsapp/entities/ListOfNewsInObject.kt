package com.example.newsapp.entities

import com.example.newsapp.entities.News
import java.io.Serializable

data class ListOfNewsInObject(val listNews: List<News>) : Serializable