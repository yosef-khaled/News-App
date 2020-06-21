package com.example.newsapp.behaviours

import android.content.Intent
import android.widget.ImageView

fun ImageView.shareAction(shareUrl: String,shareTitle:String) {
    this.setOnClickListener {
        this.context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT, shareUrl),
                shareTitle)
        )
    }
}