package com.example.newsapp.features.splash_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.behaviours.NEWS_LIST
import com.example.newsapp.behaviours.getViewModel
import com.example.newsapp.behaviours.isNetworkAvailable
import com.example.newsapp.behaviours.observe
import com.example.newsapp.domain.RxRetrofitClient
import com.example.newsapp.entities.ListOfNewsInObject
import com.example.newsapp.entities.News
import com.example.newsapp.features.show_news_list_screen.ShowNewsListActivity
import com.example.newsapp.features.splash_screen.getway.Endpoint
import com.example.newsapp.repository_controllers.MySQLiteController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class SplashActivity : AppCompatActivity() {
    private val viewModel by getViewModel { SplashViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observe(viewModel.screenStates, ::onScreenDataChange)
    }

    private fun onScreenDataChange(screenStates: SplashScreenStates) {
        when (screenStates) {
            is Loading -> loader.visibility = View.VISIBLE
            is Success -> {
                loader.visibility = View.GONE
                navigateTONewsListActivity(screenStates.newsList)
            }
            is Error -> {
                loader.visibility = View.GONE
                showErrorSnackbar(errorMasseg = screenStates.error)
            }
        }
    }

    private fun navigateTONewsListActivity(listNews: List<News>) {
        startActivity(
            Intent(this, ShowNewsListActivity::class.java).putExtra(
                NEWS_LIST,
                ListOfNewsInObject(listNews)
            )
        )
        finish()
    }

    private fun showErrorSnackbar(errorMasseg: String) {
        Snackbar.make(main_view, errorMasseg, Snackbar.LENGTH_LONG).show()
    }

    override fun onStart() {
        viewModel.getAllNews(
            endpoint = RxRetrofitClient.clientInstant.create(Endpoint::class.java),
            online = isNetworkAvailable(),
            mySQLiteController = MySQLiteController(
                applicationContext
            )
        )
        super.onStart()
    }
}