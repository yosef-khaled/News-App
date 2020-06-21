package com.example.newsapp.features.splash_screen.getway

import com.example.newsapp.repository_controllers.MySQLiteController
import com.example.newsapp.entities.news_respons.NewsRespons
import io.reactivex.Single

fun fetchAllNewsFromRepository(
    mySQLiteController: MySQLiteController,
    endpoint: Endpoint,
    online: Boolean
): Single<NewsRespons> {
    return if (online) {
        fetchAllNewsFromApi(endpoint).doOnSuccess { mySQLiteController.addAllNews(it) }
    } else {
        fetchAllNewsFromSQLite(mySQLiteController)
    }
}

private fun fetchAllNewsFromApi(endpoint: Endpoint): Single<NewsRespons> = endpoint.getAllNews()

private fun fetchAllNewsFromSQLite(mySQLiteController: MySQLiteController): Single<NewsRespons> =
    Single.just(mySQLiteController.allNews)
