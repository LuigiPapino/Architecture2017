package com.spranga.architecture2017.data.network;

import android.support.annotation.NonNull;

/**
 * Created by nietzsche on 02/04/17.
 */

public enum ApiEnvironment {

  Testing("https://jsonplaceholder.typicode.com");
  public String baseUrl;

  ApiEnvironment(@NonNull String baseUrl) {
    this.baseUrl = baseUrl;
  }
}
