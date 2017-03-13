package com.spranga.architecture2017.di.main;

import com.spranga.architecture2017.ItemListActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by nietzsche on 03/03/17.
 */
@Subcomponent public interface MainActivitySubComponent extends AndroidInjector<ItemListActivity> {
  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<ItemListActivity> {
  }
}
