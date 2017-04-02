package com.spranga.architecture2017.di;

import android.app.Activity;
import com.spranga.architecture2017.di.main.MainActivitySubComponent;
import com.spranga.architecture2017.ui.MainActivity_;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by nietzsche on 03/03/17.
 */

@Module(subcomponents = MainActivitySubComponent.class) public abstract class ActivityModule {

  @Binds @IntoMap @ActivityKey(MainActivity_.class)
  abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(
      MainActivitySubComponent.Builder builder);
}
