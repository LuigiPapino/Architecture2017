package com.spranga.architecture2017.di

import android.app.Activity
import com.spranga.architecture2017.di.main.MainActivitySubComponent
import com.spranga.architecture2017.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by nietzsche on 03/03/17.
 */

@Module(subcomponents = arrayOf(MainActivitySubComponent::class)) abstract class ActivityModule {

  @Binds
  @IntoMap
  @ActivityKey(MainActivity::class)
  internal abstract fun bindMainActivityInjectorFactory(
      builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}
