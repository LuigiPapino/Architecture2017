package com.spranga.architecture2017.ui

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.spranga.architecture2017.R
import com.spranga.architecture2017.data.model.Post
import com.spranga.architecture2017.presenter.MainPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import javax.inject.Inject

open class MainActivity : AppCompatActivity() {


  @Inject
  lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    AndroidInjection.inject(this)

    setSupportActionBar(toolbar)
    fab.setOnClickListener { presenter.click() }
    presenter.init(this)


  }

  fun showError(message: String?) {
    AlertDialog.Builder(this).setTitle("Error").setMessage(message).show()
  }

  fun showPosts(posts: List<Post>) {
    AlertDialog.Builder(this).setTitle("Success")
        .setMessage(String.format("%d posts downloaded", posts.size))
        .show()
  }
}
