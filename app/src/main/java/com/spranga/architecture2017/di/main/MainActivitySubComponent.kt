package com.spranga.architecture2017.di.main

import com.spranga.architecture2017.ui.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by nietzsche on 03/03/17.
 */
@Subcomponent interface MainActivitySubComponent : AndroidInjector<MainActivity> {
  @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
