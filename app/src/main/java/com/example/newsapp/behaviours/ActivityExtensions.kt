package com.example.newsapp.behaviours

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T> FragmentActivity.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let(observer) })
}

fun <T> Fragment.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let(observer) })
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo
        .isConnected
}



