package com.spranga.architecture2017.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.spranga.architecture2017.R
import com.spranga.architecture2017.data.model.Post
import com.spranga.architecture2017.presenter.MainPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.login_button
import kotlinx.android.synthetic.main.content_main.main_button_image
import kotlinx.android.synthetic.main.content_main.main_button_java
import kotlinx.android.synthetic.main.content_main.main_button_okhttp
import kotlinx.android.synthetic.main.content_main.main_image
import javax.inject.Inject


open class MainActivity : AppCompatActivity() {


  @Inject
  lateinit var presenter: MainPresenter
  var callbackManager: CallbackManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    AndroidInjection.inject(this)

    setSupportActionBar(toolbar)
    main_button_okhttp.setOnClickListener { presenter.click() }
    main_button_java.setOnClickListener({ presenter.javaClick() })
    main_button_image.setOnClickListener(
        { main_image.setImageURI("https://i.ytimg.com/vi/v9oxyswY8fs/maxresdefault.jpg") })
    presenter.init(this)
    callbackManager = CallbackManager.Factory.create()
    login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
      override fun onCancel() {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onSuccess(p0: LoginResult?) {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onError(p0: FacebookException?) {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
      }

    })

  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)
    callbackManager?.onActivityResult(requestCode, resultCode, data)
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
