package com.spranga.architecture2017.di

import android.app.Application
import com.spranga.architecture2017.SprangaApplication
import dagger.Module
import dagger.Provides

/**
 * Created by nietzsche on 02/04/17.
 */

@Module class ApplicationModule(internal var application: SprangaApplication) {

  @Provides internal fun provideApplication(): Application {
    return application
  }

  @Provides internal fun provideSprangaApplication(): SprangaApplication {
    return application
  }
}
