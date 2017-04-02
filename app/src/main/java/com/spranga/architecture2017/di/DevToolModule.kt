package com.spranga.architecture2017.di

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.frogermcs.androiddevmetrics.AndroidDevMetrics
import com.spranga.architecture2017.di.qualifiers.DevTool
import com.squareup.leakcanary.LeakCanary
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import io.reactivex.functions.Consumer
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by luigipapino on 09/03/2017.
 */

@Module internal class DevToolModule {
  @Provides
  @IntoSet
  @DevTool
  internal fun provideStetho(): Consumer<Application> {
    return Consumer { Stetho.initializeWithDefaults(it) }
  }

  @Provides
  @IntoSet
  @DevTool
  internal fun provideLeakCanary(): Consumer<Application> {
    return Consumer {
      LeakCanary.install(it)
    }
  }

  @Provides
  @IntoSet
  @DevTool
  internal fun provideAndroidDevMetrics(): Consumer<Application> {
    return Consumer { AndroidDevMetrics.initWith(it) }
  }

  @Provides
  @IntoSet
  internal fun provideHttpLoggingInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
  }

  @Provides
  @IntoSet
  internal fun provideStethoLoggingInterceptor(): Interceptor {
    return StethoInterceptor()
  }
}
