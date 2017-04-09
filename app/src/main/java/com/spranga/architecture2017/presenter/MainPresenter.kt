package com.spranga.architecture2017.presenter

import com.spranga.architecture2017.data.model.Post
import com.spranga.architecture2017.data.network.ApiService
import com.spranga.architecture2017.ui.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

/**
 * Created by nietzsche on 02/04/17.
 */

class MainPresenter @Inject constructor(internal var apiService: ApiService) {

  internal var view: MainActivity? = null

  fun init(view: MainActivity) {
    this.view = view
  }

  fun click() {
    apiService.posts
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ this.doSomething(it) },
            { this.onError(it) })


  }

  private fun onError(throwable: Throwable) {
    view?.showError(throwable.message)
  }

  private fun doSomething(posts: List<Post>) {
    view?.showPosts(posts)
  }

  fun imageClick() {

  }

  fun javaClick() {
    Schedulers.io().scheduleDirect(
        {
          val url = URL("https://www.linkedin.com/company-beta/10647841/?pathWildcard=10647841")
          val urlConnection = url.openConnection() as HttpURLConnection
          try {
            val `in` = BufferedInputStream(urlConnection.inputStream)
            //readStream(`in`)
          } finally {
            urlConnection.disconnect()
          }
        })
  }
}
