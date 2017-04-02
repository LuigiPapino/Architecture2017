package com.spranga.architecture2017.data.network;

import android.support.annotation.NonNull;
import com.annimon.stream.Stream;
import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import java.util.Set;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by nietzsche on 02/04/17.
 */

public class NetworkServices {

  private Retrofit apiRetrofit;

  public NetworkServices(@NonNull ApiEnvironment apiEnvironment, Set<Interceptor> interceptors) {
    Retrofit.Builder builder = new Retrofit.Builder().
        client(createClient(interceptors))
        .baseUrl(apiEnvironment.baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(LoganSquareConverterFactory.create());

    apiRetrofit = builder.build();
  }

  public ApiService createApiService() {
    return apiRetrofit.create(ApiService.class);
  }

  private OkHttpClient createClient(Set<Interceptor> interceptors) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    Stream.of(interceptors).forEach(builder::addInterceptor);
    return builder.build();
  }
}
