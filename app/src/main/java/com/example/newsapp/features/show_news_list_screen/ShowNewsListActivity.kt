package com.example.newsapp.features.show_news_list_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.behaviours.NEWS
import com.example.newsapp.behaviours.NEWS_LIST
import com.example.newsapp.entities.News
import com.example.newsapp.features.news_details.NewsDetailsActivity
import com.example.newsapp.entities.ListOfNewsInObject
import kotlinx.android.synthetic.main.activity_show_news_list.*

class ShowNewsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_news_list)
        getExtraData()
    }

    private fun getExtraData() {
        val data = intent.getSerializableExtra(NEWS_LIST) as ListOfNewsInObject?
        data?.let { setupNewsRecycler(it.listNews) }
    }

    private fun setupNewsRecycler(newsList: List<News>) {
        with(news_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsListAdapter(newsList, ::navigateToNewsDetails)
        }
    }

    private fun navigateToNewsDetails(news: News) {
        startActivity(Intent(this, NewsDetailsActivity::class.java).putExtra(NEWS, news))
    }
}