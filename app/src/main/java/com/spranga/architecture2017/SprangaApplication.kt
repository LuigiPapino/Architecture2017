package com.spranga.architecture2017

import android.app.Activity
import android.app.Application
import android.util.Log
import com.annimon.stream.Stream
import com.spranga.architecture2017.di.ApplicationModule
import com.spranga.architecture2017.di.DaggerSprangaAppComponent
import com.spranga.architecture2017.di.SprangaAppComponent
import com.spranga.architecture2017.di.qualifiers.DevTool
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import io.reactivex.functions.Consumer
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

  override fun activityInjector(): DispatchingAndroidInjector<Activity> {
    return dispatchingAndroidInjector
  }

  companion object {

    private val TAG = SprangaApplication::class.java.simpleName
    var appComponent: SprangaAppComponent? = null
      private set
  }
}
