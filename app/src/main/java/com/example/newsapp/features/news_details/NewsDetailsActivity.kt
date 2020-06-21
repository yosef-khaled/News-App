package com.example.newsapp.features.news_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.behaviours.NEWS
import com.example.newsapp.entities.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        setupActionBar()
        getDataFromExtra()
    }
    private fun setupActionBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.news_details)
    }
    private fun getDataFromExtra(){
        val news = intent.getSerializableExtra(NEWS) as News
        bindScreenData(news)
    }
    private fun bindScreenData(news: News){
        with(news) {
            Picasso.get().load(imgUrl).into(news_details_image)
            title_details_text.text = title
            news_author_name_Details_text.text = author
            date_details_text.text = publishedAt
            news_content_details_text.text = content
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}