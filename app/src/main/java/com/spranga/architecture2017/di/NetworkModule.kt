package com.spranga.architecture2017.di

import android.os.Bundle
import com.annimon.stream.function.Consumer
import com.spranga.architecture2017.data.network.ApiEnvironment
import com.spranga.architecture2017.data.network.ApiService
import com.spranga.architecture2017.data.network.NetworkServices
import com.spranga.figamock.interceptor.FigaMockInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named

/**
 * Created by nietzsche on 02/04/17.
 */
@Module internal class NetworkModule {

  //@Multibinds internal abstract fun interceptorSet(): Set<Interceptor>

  @Provides fun provideNetworkServices(apiEnvironment: ApiEnvironment,
      interceptors: Set<@JvmSuppressWildcards Interceptor>): NetworkServices {
    return NetworkServices(apiEnvironment, interceptors)
  }

  @Provides fun provideApiService(networkServices: NetworkServices): ApiService {
    return networkServices.createApiService()
  }

  @Provides @IntoSet fun provideAuthLoggingInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
  }

  @Provides
  @IntoSet
  fun provideFigaMockInterceptor(@Named("figa_logger") logger: Consumer<Bundle>): Interceptor {
    return FigaMockInterceptor(logger)
  }

  @Provides fun provideApiEnvironment(): ApiEnvironment {
    return ApiEnvironment.Testing
  }
}

