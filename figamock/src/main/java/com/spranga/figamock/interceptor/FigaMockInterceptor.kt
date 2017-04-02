package com.spranga.figamock.interceptor

import android.os.Bundle
import com.annimon.stream.function.Consumer
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by nietzsche on 02/04/17.
 */
class FigaMockInterceptor(internal var logger: Consumer<Bundle>) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val bundle = Bundle()
    //collect request data
    val request = chain.request()
    bundle.putString("REQUEST_VERB", request.method())
    bundle.putString("REQUEST_URL", request.url().encodedPath())
    bundle.putInt("REQUEST_HEADER_COUNT", request.headers().size())

    //execute request
    val response: Response
    response = chain.proceed(request)

    //collect response data
    bundle.putString("RESPONSE_CODE", "" + response.code())
    bundle.putString("RESPONSE_MSG", response.message())
    bundle.putInt("RESPONSE_HEADER_COUNT", response.headers().size())
    val duration = response.receivedResponseAtMillis() - response.sentRequestAtMillis()
    bundle.putLong("RESPONSE_DURATION", duration)

    logger.accept(bundle)

    return response
  }
}
