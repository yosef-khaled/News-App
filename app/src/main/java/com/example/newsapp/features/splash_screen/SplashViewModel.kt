package com.example.newsapp.features.splash_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.repository_controllers.MySQLiteController
import com.example.newsapp.entities.News
import com.example.newsapp.features.splash_screen.getway.Endpoint
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SplashViewModel(
    private val getAllNewsUseCase: (MySQLiteController, Endpoint, Boolean) -> Single<List<News>> = ::fetchAllNews
) : ViewModel() {
    val screenStates by lazy { MutableLiveData<SplashScreenStates>() }
    private var disposable: Disposable? = null

    fun getAllNews(
        endpoint: Endpoint,
        mySQLiteController: MySQLiteController,
        online: Boolean,
        subscribeOnSchedulers: Scheduler = Schedulers.io(),
        observeOnSchedulers: Scheduler = AndroidSchedulers.mainThread()
    ) {
        disposable = getAllNewsUseCase(mySQLiteController, endpoint, online)
            .subscribeOn(subscribeOnSchedulers)
            .observeOn(observeOnSchedulers)
            .doOnSubscribe { screenStates.postValue(Loading) }
            .subscribe(
                { screenStates.postValue(Success(it)) },
                { screenStates.postValue(Error(it.message ?: "")) })

    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}