package com.spranga.architecture2017.data.network.interceptor;

import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by nietzsche on 02/04/17.
 */
public class FigaMockInterceptor implements Interceptor {

  FirebaseAnalytics firebaseAnalytics;

  public FigaMockInterceptor(FirebaseAnalytics firebaseAnalytics) {
    this.firebaseAnalytics = firebaseAnalytics;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Bundle bundle = new Bundle();

    Request request = chain.request();
    bundle.putString("REQUEST_VERB", request.method());
    bundle.putString("REQUEST_URL", request.url().encodedPath().toString());
    bundle.putInt("REQUEST_HEADER_COUNT", request.headers().size());

    Response response;

    response = chain.proceed(request);

    bundle.putString("RESPONSE_CODE", "" + response.code());
    bundle.putString("RESPONSE_MSG", response.message());
    bundle.putInt("RESPONSE_HEADER_COUNT", response.headers().size());

    long duration = response.receivedResponseAtMillis() - response.sentRequestAtMillis();
    bundle.putLong("RESPONSE_DURATION", duration);

    firebaseAnalytics.logEvent("NETWORK_REQUEST", bundle);
    return response;
  }
}
