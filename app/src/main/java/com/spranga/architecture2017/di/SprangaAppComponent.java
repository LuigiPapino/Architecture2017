package com.spranga.architecture2017.di;

import com.spranga.architecture2017.SprangaApplication;
import dagger.Component;

/**
 * Created by nietzsche on 03/03/17.
 */

@Component(modules = { ActivityModule.class, DevToolModule.class })
public interface SprangaAppComponent {
  void inject(SprangaApplication sprangaApplication);
}
