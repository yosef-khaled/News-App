package com.example.newsapp.features.splash_screen

import com.example.newsapp.repository_controllers.MySQLiteController
import com.example.newsapp.entities.News
import com.example.newsapp.entities.news_respons.NewsRespons
import com.example.newsapp.features.splash_screen.getway.Endpoint
import com.example.newsapp.features.splash_screen.getway.fetchAllNewsFromRepository
import io.reactivex.Single

fun fetchAllNews(
    mySQLiteController: MySQLiteController,
    endpoint: Endpoint,
    online: Boolean
): Single<List<News>> =
    fetchAllNewsFromRepository(
        mySQLiteController = mySQLiteController,
        endpoint = endpoint,
        online = online
    ).map {
        it.toNewsList()
    }

fun NewsRespons.toNewsList(): List<News> =
    ArrayList<News>().apply {
        this@toNewsList.articles?.forEach {
            this.add(
                News(
                    imgUrl = it.urlToImage ?: "No Image",
                    author = it.author ?: "No author",
                    content = it.content ?: "No content",
                    description = it.description ?: "No description",
                    newsUrl = it.url ?: "No news url",
                    publishedAt = it.publishedAt ?: "No published at",
                    title = it.title ?: "No title"
                )
            )
        }
    }