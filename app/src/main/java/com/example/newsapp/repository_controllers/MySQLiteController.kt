package com.example.newsapp.repository_controllers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.newsapp.behaviours.*
import com.example.newsapp.entities.news_respons.Article
import com.example.newsapp.entities.news_respons.NewsRespons
import com.example.newsapp.entities.news_respons.Source

class MySQLiteController(context: Context?) :
    SQLiteOpenHelper(context, DATA_BASE_NAME, null, 1) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val createTable =
            "create table news(id integer primary key,status text,totalResults integer,author text,title text,description text,url text,urlToImage text,publishedAt text,content text,sourceId text,sourceName text)"
        sqLiteDatabase.execSQL(createTable)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        val deleteQuery = "DROP TABLE IF EXISTS news"
        sqLiteDatabase.execSQL(deleteQuery)
        onCreate(sqLiteDatabase)
    }

    fun addAllNews(news: NewsRespons) {
        val db = this.writableDatabase
        dropTable()
        with(ContentValues()) {
            news.articles?.forEach {
                put(STATUS, news.status)
                put(TOTAL_RESULTS, news.totalResults)
                put(AUTHOR, it.author)
                put(TITLE, it.title)
                put(DESCRIPTION, it.description)
                put(URL, it.url)
                put(URL_TO_IMAGE, it.urlToImage)
                put(PUBLISHED_AT, it.publishedAt)
                put(CONTENT, it.content)
                put(SOURCE_ID, it.source?.id)
                put(SOURCE_NAME, it.source?.name)
                db.insert(TABLE_NAME, null, this)
            }
        }
    }

    val allNews: NewsRespons
        get() {
            val newsRespons = NewsRespons(null, null, null)
            val newsList: ArrayList<Article> = arrayListOf()
            val selectQuery = "select * from news"
            val db = this.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    with(cursor) {
                        val author = getString(cursor.getColumnIndex(AUTHOR))
                        val title = getString(cursor.getColumnIndex(TITLE))
                        val description = getString(cursor.getColumnIndex(DESCRIPTION))
                        val url = getString(cursor.getColumnIndex(URL))
                        val urlToImage = getString(cursor.getColumnIndex(URL_TO_IMAGE))
                        val publishedAt = getString(cursor.getColumnIndex(PUBLISHED_AT))
                        val content = getString(cursor.getColumnIndex(CONTENT))
                        val sourceId = getString(cursor.getColumnIndex(SOURCE_ID))
                        val sourceName = getString(cursor.getColumnIndex(SOURCE_NAME))
                        val status = getString(cursor.getColumnIndex(STATUS))
                        val totalResults = getInt(cursor.getColumnIndex(TOTAL_RESULTS))
                        val news = Article(
                            author = author,
                            title = title,
                            publishedAt = publishedAt,
                            description = description,
                            content = content,
                            source = Source(id = sourceId, name = sourceName),
                            url = url,
                            urlToImage = urlToImage
                        )
                        newsRespons.status = status
                        newsRespons.totalResults = totalResults
                        newsList.add(news)
                    }
                } while (cursor.moveToNext())
            }
            newsRespons.articles = newsList
            return newsRespons
        }

    private fun dropTable() {
        val db = this.writableDatabase
        onUpgrade(db, 1, 1)
    }
}