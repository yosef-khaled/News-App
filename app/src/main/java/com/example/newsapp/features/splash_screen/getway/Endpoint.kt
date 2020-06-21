package com.example.newsapp.features.splash_screen.getway

import com.example.newsapp.entities.news_respons.NewsRespons
import io.reactivex.Single
import retrofit2.http.GET

interface Endpoint {

    @GET("top-headlines?country=us&category=business&apiKey=15287687b89b46549338cacf67122149")
    fun getAllNews(): Single<NewsRespons>

}