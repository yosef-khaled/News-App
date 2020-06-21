package com.example.newsapp.features.splash_screen

import com.example.newsapp.entities.News

sealed class SplashScreenStates
class Error(val error: String) : SplashScreenStates()
data class Success(val newsList :List<News>) : SplashScreenStates()
object Loading : SplashScreenStates()
