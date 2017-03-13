package com.spranga.architecture2017.di;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.spranga.architecture2017.di.qualifiers.DevTool;
import com.squareup.leakcanary.LeakCanary;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by luigipapino on 09/03/2017.
 */

@Module() public class DevToolModule {
  @Provides
  @IntoSet
  @DevTool
  static Consumer<Application> provideStetho() {
    return Stetho::initializeWithDefaults;
  }

  @Provides
  @IntoSet
  @DevTool
  static Consumer<Application> provideLeakCanary() {
    return application -> {
      if (LeakCanary.isInAnalyzerProcess(application)) {
        // This process is dedicated to LeakCanary for heap analysis.
        // You should not init your app in this process.
        return;
      }
      LeakCanary.install(application);
    };
  }

  @Provides
  @IntoSet
  @DevTool
  static Consumer<Application> provideAndroidDevMetrics() {
    return AndroidDevMetrics::initWith;
  }

  @Provides
  @IntoSet
  static Interceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return interceptor;
  }

  @Provides
  @IntoSet
  static Interceptor provideStethoLoggingInterceptor() {
    return new StethoInterceptor();
  }
}
