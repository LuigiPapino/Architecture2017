package com.spranga.architecture2017;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.annimon.stream.Stream;
import com.spranga.architecture2017.di.DaggerSprangaAppComponent;
import com.spranga.architecture2017.di.SprangaAppComponent;
import com.spranga.architecture2017.di.qualifiers.DevTool;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import io.reactivex.functions.Consumer;
import java.util.Set;
import javax.inject.Inject;

/**
 * Created by nietzsche on 03/03/17.
 */
public class SprangaApplication extends Application implements HasDispatchingActivityInjector {

  private static final String TAG = SprangaApplication.class.getSimpleName();
  private static SprangaAppComponent appComponent;
  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
  @Inject
  @DevTool
  Set<Consumer<Application>> devTools;

  public static SprangaAppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = DaggerSprangaAppComponent.create();
    appComponent.inject(this);
    initDevTools();
  }

  private void initDevTools() {
    Stream.of(devTools).forEach(it -> {
      try {
        it.accept(this);
      } catch (Exception e) {
        Log.e(TAG, e.getMessage(), e);
      }
    });
  }

  @Override
  public DispatchingAndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }
}
