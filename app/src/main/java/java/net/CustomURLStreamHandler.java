//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java.net;

import com.bugsee.library.serverapi.data.event.Scope;
import com.bugsee.library.util.LogWrapper;
import com.bugsee.library.util.ReflectionUtils;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.net.ssl.HttpsURLConnection;

public class CustomURLStreamHandler extends URLStreamHandler {
  public static final String HTTP_PROTOCOL = "http";
  public static final String HTTPS_PROTOCOL = "https";
  private static final String sLogTag = CustomURLStreamHandler.class.getSimpleName();
  private URLStreamHandler mHttpsStreamHandler;
  private URLStreamHandler mHttpStreamHandler;
  private Method mHttpsOpenConnectionMethod;
  private Method mHttpOpenConnectionMethod;

  public CustomURLStreamHandler(URLStreamHandler httpsStreamHandler,
      URLStreamHandler httpStreamHandler) {
    this.mHttpsStreamHandler = httpsStreamHandler;
    this.mHttpStreamHandler = httpStreamHandler;
    this.initializeMethods();
  }

  protected URLConnection openConnection(URL url) throws IOException {
    boolean isHttps = "https".equals(url.getProtocol());

    try {
      URLConnection connection =
          isHttps ? (URLConnection) this.mHttpsOpenConnectionMethod.invoke(this.mHttpsStreamHandler,
              url)
              : (URLConnection) this.mHttpOpenConnectionMethod.invoke(this.mHttpStreamHandler, url);
      return connection instanceof HttpsURLConnection ? new HttpsConnectionWrapper(
          (HttpsURLConnection) connection)
          : (connection instanceof HttpURLConnection ? new HttpConnectionWrapper(
              (HttpURLConnection) connection) : connection);
    } catch (Exception var5) {
      String message = "Failed to wrap base openConnection(). isHttps = " + isHttps;
      LogWrapper.logException(sLogTag, message, var5, Scope.Generation);
      throw new IOException(message, var5);
    }
  }

  private void initializeMethods() {
    Class[] methodParams = new Class[] { URL.class };

    try {
      this.mHttpsOpenConnectionMethod =
          ReflectionUtils.findInheritedMethod(null, this.mHttpsStreamHandler.getClass(),
              "openConnection", methodParams, true);
      this.mHttpsOpenConnectionMethod.setAccessible(true);
      this.mHttpOpenConnectionMethod =
          ReflectionUtils.findInheritedMethod(null, this.mHttpStreamHandler.getClass(),
              "openConnection", methodParams, true);
      this.mHttpOpenConnectionMethod.setAccessible(true);
    } catch (NoSuchMethodException var3) {
      if (this.mHttpsStreamHandler != null) {
        this.mHttpOpenConnectionMethod = this.mHttpsOpenConnectionMethod;
      }
    }
  }
}
