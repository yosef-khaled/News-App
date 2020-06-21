package com.example.newsapp.features.show_news_list_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.behaviours.shareAction
import com.example.newsapp.entities.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_list.view.*

class NewsListAdapter(
    private val newsList: List<News>,
    private val onNewsClick: (News) -> Unit
) : RecyclerView.Adapter<NewsListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewsListViewHolder =
        viewGroup.context
            .let { LayoutInflater.from(it) }
            .inflate(R.layout.item_news_list, viewGroup, false)
            .let { NewsListViewHolder(it) }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val item = newsList[position]
        with(holder) {
            Picasso.get().load(item.imgUrl).into(newsImage)
            newsTitle.text = item.title
            date.text = item.publishedAt
            shareButton.shareAction(
                item.newsUrl,
                shareButton.context.getString(R.string.share_news)
            )
            newsCard.setOnClickListener { onNewsClick(item) }
        }
    }

}

class NewsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val newsImage: ImageView = view.news_image
    val newsTitle: TextView = view.news_title_text
    val date: TextView = view.date_text
    val facebookShareButton: ImageButton = view.facebook_share_button
    val shareButton: ImageButton = view.share_button
    val newsCard: CardView = view.news_card
}