package com.spranga.architecture2017.di

import android.app.Application
import android.os.Bundle
import com.annimon.stream.function.Consumer
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by nietzsche on 02/04/17.
 */

@Module internal class FirebaseModule {

  @Provides @Singleton fun provideFirebaseAnalytics(application: Application): FirebaseAnalytics {
    return FirebaseAnalytics.getInstance(application)
  }

  @Provides
  @Named("figa_logger")
  fun provideFigaLogger(firebaseAnalytics: FirebaseAnalytics): Consumer<Bundle> {
    return Consumer { bundle -> firebaseAnalytics.logEvent("NETWORK_REQUEST", bundle) }
  }
}
