package com.spranga.architecture2017.di;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.spranga.architecture2017.data.network.ApiEnvironment;
import com.spranga.architecture2017.data.network.ApiService;
import com.spranga.architecture2017.data.network.NetworkServices;
import com.spranga.architecture2017.data.network.interceptor.FigaMockInterceptor;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import dagger.multibindings.Multibinds;
import java.util.Set;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by nietzsche on 02/04/17.
 */
@Module abstract class NetworkModule {
  @Provides static NetworkServices provideNetworkServices(ApiEnvironment apiEnvironment,
      Set<Interceptor> interceptors) {
    return new NetworkServices(apiEnvironment, interceptors);
  }

  @Provides static ApiService provideApiService(NetworkServices networkServices) {
    return networkServices.createApiService();
  }

  @Provides @IntoSet static Interceptor provideAuthLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return interceptor;
  }

  @Provides @IntoSet
  static Interceptor provideFigaMockInterceptor(FirebaseAnalytics firebaseAnalytics) {
    return new FigaMockInterceptor(firebaseAnalytics);
  }

  @Provides static ApiEnvironment provideApiEnvironment() {
    return ApiEnvironment.Testing;
  }

  @Multibinds abstract Set<Interceptor> interceptorSet();
}
