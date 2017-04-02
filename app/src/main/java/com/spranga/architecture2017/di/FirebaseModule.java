package com.spranga.architecture2017.di;

import android.app.Application;
import com.google.firebase.analytics.FirebaseAnalytics;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by nietzsche on 02/04/17.
 */

@Module abstract class FirebaseModule {

  @Provides @Singleton static FirebaseAnalytics provideFirebaseAnalytics(Application application) {
    return FirebaseAnalytics.getInstance(application);
  }
}
