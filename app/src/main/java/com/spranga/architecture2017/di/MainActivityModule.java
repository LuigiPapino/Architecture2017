package com.spranga.architecture2017.di;

import android.app.Activity;
import com.spranga.architecture2017.ItemListActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by nietzsche on 03/03/17.
 */

@Module(subcomponents = MainActivitySubComponent.class) public abstract class MainActivityModule {

  @Binds @IntoMap @ActivityKey(ItemListActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(
      MainActivitySubComponent.Builder builder);
}
