package com.spranga.architecture2017

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build.VERSION
import android.os.Bundle
import android.support.multidex.MultiDex
import android.util.Log
import com.annimon.stream.Stream
import com.annimon.stream.function.Consumer
import com.bugsee.library.serverapi.data.event.Scope
import com.bugsee.library.util.LogWrapper
import com.facebook.drawee.backends.pipeline.Fresco
import com.spranga.architecture2017.di.ApplicationModule
import com.spranga.architecture2017.di.DaggerSprangaAppComponent
import com.spranga.architecture2017.di.SprangaAppComponent
import com.spranga.architecture2017.di.qualifiers.DevTool
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import java.net.CustomURLStreamHandler
import java.net.URL
import java.net.URLStreamHandler
import javax.inject.Inject


/**
 * Created by nietzsche on 03/03/17.
 * https://github.com/facebook/stetho/blob/master/stetho-okhttp3/src/main/java/com/facebook/stetho/okhttp3/StethoInterceptor.java
 * https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/src/main/java/okhttp3/logging/HttpLoggingInterceptor.java
 * http://jordifierro.com/android-http-interceptor-testing
 */
class SprangaApplication : Application(), HasDispatchingActivityInjector {

  @Inject
  lateinit internal var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  @Inject
  @DevTool
  lateinit internal var devTools: Set<@JvmSuppressWildcards Consumer<Application>>

  override fun onCreate() {
    super.onCreate()
    //Bugsee.launch(this, "0740e2bb-fb60-4741-951e-7fec31644aae");
    initialize()
    Fresco.initialize(this)
    val imagePipeline = Fresco.getImagePipeline()
    imagePipeline.clearMemoryCaches()
    imagePipeline.clearDiskCaches()
    imagePipeline.clearCaches()
    appComponent = DaggerSprangaAppComponent.builder().applicationModule(
        ApplicationModule(this)).build()
    appComponent!!.inject(this)
    initDevTools()
  }

  private fun initDevTools() {
    Stream.of(devTools).forEach { it ->
      try {
        it?.accept(this)
      } catch (e: Exception) {
        Log.e(TAG, e.message, e)
      }
    }
  }

  private var mIsInitialized: Boolean = false
  private var mHttpStreamHandler: URLStreamHandler? = null
  private var mHttpsStreamHandler: URLStreamHandler? = null

  fun initialize() {
    if (!this.mIsInitialized) {
      this.mHttpStreamHandler = this.getStreamHandler("http://www.google.com")
      this.mHttpsStreamHandler = this.getStreamHandler("https://www.google.com")
      if (this.mHttpStreamHandler != null && this.mHttpsStreamHandler != null) {
        URL.setURLStreamHandlerFactory({
          if (!"http".equals(it, ignoreCase = true) && !"https".equals(
              it,
              ignoreCase = true)) null else CustomURLStreamHandler(this.mHttpsStreamHandler,
              this.mHttpStreamHandler)
        })
      }

      this.mIsInitialized = true
    }
  }

  private fun getStreamHandler(urlString: String): URLStreamHandler? {
    val fieldName = if (VERSION.SDK_INT >= 24) "handler" else "streamHandler"

    try {
      val url = URL(urlString)
      val handlerField = url.javaClass.getDeclaredField(fieldName)
      handlerField.isAccessible = true
      return handlerField.get(url) as URLStreamHandler
    } catch (var5: Exception) {
      LogWrapper.logException("MYLOG", "Failed to get standard URLStreamHandler for " + urlString,
          var5, Scope.Generation)
      return null
    }

  }

  override fun activityInjector(): DispatchingAndroidInjector<Activity> {
    return dispatchingAndroidInjector
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  companion object {

    private val TAG = SprangaApplication::class.java.simpleName
    var appComponent: SprangaAppComponent? = null
      private set

    fun logger(): Consumer<Bundle>? {
      return appComponent?.logger()
    }
  }
}
