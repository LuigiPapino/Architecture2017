package com.spranga.architecture2017.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nietzsche on 02/04/17.
 */

@Module public class ApplicationModule {

  Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application provideApplication() {
    return application;
  }
}
