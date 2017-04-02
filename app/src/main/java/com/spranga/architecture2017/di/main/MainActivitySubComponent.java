package com.spranga.architecture2017.di.main;

import com.spranga.architecture2017.ui.MainActivity_;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by nietzsche on 03/03/17.
 */
@Subcomponent public interface MainActivitySubComponent extends AndroidInjector<MainActivity_> {
  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<MainActivity_> {
  }
}
