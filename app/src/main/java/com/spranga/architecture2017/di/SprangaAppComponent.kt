package com.spranga.architecture2017.di

import android.os.Bundle
import com.annimon.stream.function.Consumer
import com.spranga.architecture2017.SprangaApplication
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by nietzsche on 03/03/17.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ActivityModule::class, NetworkModule::class,
    FirebaseModule::class, DevToolModule::class))
interface SprangaAppComponent {

  @Named("figa_logger")
  fun logger(): Consumer<Bundle>?
  fun inject(sprangaApplication: SprangaApplication)
}
