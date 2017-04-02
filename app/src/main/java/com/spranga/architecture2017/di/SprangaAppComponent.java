package com.spranga.architecture2017.di;

import com.spranga.architecture2017.SprangaApplication;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by nietzsche on 03/03/17.
 */

@Singleton @Component(modules = {
    ApplicationModule.class, ActivityModule.class, NetworkModule.class, FirebaseModule.class,
    DevToolModule.class
})
public interface SprangaAppComponent {
  void inject(SprangaApplication sprangaApplication);
}
