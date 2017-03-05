package com.spranga.architecture2017;

import android.app.Activity;
import android.app.Application;
import com.spranga.architecture2017.di.DaggerSprangaAppComponent;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import javax.inject.Inject;

/**
 * Created by nietzsche on 03/03/17.
 */

public class SprangaApplication extends Application implements HasDispatchingActivityInjector {

  @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override public void onCreate() {
    super.onCreate();
    DaggerSprangaAppComponent.create().inject(this);
  }

  @Override public DispatchingAndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }
}
