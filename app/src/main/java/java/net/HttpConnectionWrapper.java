//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java.net;

import android.annotation.TargetApi;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Permission;
import java.util.List;
import java.util.Map;

class HttpConnectionWrapper extends HttpURLConnection {
  private static final boolean sIsVerbose = false;
  private static final String sLogTag = HttpConnectionWrapper.class.getSimpleName();
  private final WrappedConnectionHelper mWrappedConnectionHelper;
  private HttpURLConnection mWrappedConnection;

  HttpConnectionWrapper(HttpURLConnection wrappedConnection) {
    super(null);
    this.mWrappedConnection = wrappedConnection;
    this.mWrappedConnectionHelper =
        new WrappedConnectionHelper(this.mWrappedConnection, false, sLogTag);
  }

  public void disconnect() {
    this.mWrappedConnection.disconnect();
  }

  public boolean usingProxy() {
    return this.mWrappedConnection.usingProxy();
  }

  public void connect() throws IOException {
    this.postBeforeEventIfNecessary();
    this.mWrappedConnection.connect();
  }

  public InputStream getErrorStream() {
    this.postBeforeEventIfNecessary();

    InputStream result;
    try {
      result = this.mWrappedConnection.getErrorStream();
    } catch (Exception var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public Permission getPermission() throws IOException {
    return this.mWrappedConnection.getPermission();
  }

  public String getRequestMethod() {
    return this.mWrappedConnection.getRequestMethod();
  }

  public void setRequestMethod(String method) throws ProtocolException {
    this.mWrappedConnection.setRequestMethod(method);
  }

  public int getResponseCode() throws IOException {
    this.postBeforeEventIfNecessary();

    int result;
    try {
      result = this.mWrappedConnection.getResponseCode();
    } catch (IOException var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public String getResponseMessage() throws IOException {
    this.postBeforeEventIfNecessary();

    String result;
    try {
      result = this.mWrappedConnection.getResponseMessage();
    } catch (IOException var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public String getContentEncoding() {
    this.postBeforeEventIfNecessary();

    String result;
    try {
      result = this.mWrappedConnection.getContentEncoding();
    } catch (Exception var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public boolean getInstanceFollowRedirects() {
    return this.mWrappedConnection.getInstanceFollowRedirects();
  }

  public void setInstanceFollowRedirects(boolean followRedirects) {
    this.mWrappedConnection.setInstanceFollowRedirects(followRedirects);
  }

  public long getHeaderFieldDate(String field, long defaultValue) {
    this.postBeforeEventIfNecessary();

    long result;
    try {
      result = this.mWrappedConnection.getHeaderFieldDate(field, defaultValue);
    } catch (Exception var7) {
      this.postCompleteEventIfNecessary(var7);
      throw var7;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  @TargetApi(19) public void setFixedLengthStreamingMode(long contentLength) {
    this.mWrappedConnection.setFixedLengthStreamingMode(contentLength);
  }

  public void setFixedLengthStreamingMode(int contentLength) {
    this.mWrappedConnection.setFixedLengthStreamingMode(contentLength);
  }

  public void setChunkedStreamingMode(int chunkLength) {
    this.mWrappedConnection.setChunkedStreamingMode(chunkLength);
  }

  public boolean getAllowUserInteraction() {
    return this.mWrappedConnection.getAllowUserInteraction();
  }

  public void setAllowUserInteraction(boolean newValue) {
    this.mWrappedConnection.setAllowUserInteraction(newValue);
  }

  public Object getContent() throws IOException {
    this.postBeforeEventIfNecessary();

    Object result;
    try {
      result = this.mWrappedConnection.getContent();
    } catch (IOException var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public Object getContent(Class[] types) throws IOException {
    this.postBeforeEventIfNecessary();

    Object result;
    try {
      result = this.mWrappedConnection.getContent(types);
    } catch (IOException var4) {
      this.postCompleteEventIfNecessary(var4);
      throw var4;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public int getContentLength() {
    this.postBeforeEventIfNecessary();

    int result;
    try {
      result = this.mWrappedConnection.getContentLength();
    } catch (Exception var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public String getContentType() {
    this.postBeforeEventIfNecessary();

    String result;
    try {
      result = this.mWrappedConnection.getContentType();
    } catch (Exception var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public long getDate() {
    return this.mWrappedConnection.getDate();
  }

  public boolean getDefaultUseCaches() {
    return this.mWrappedConnection.getDefaultUseCaches();
  }

  public void setDefaultUseCaches(boolean newValue) {
    this.mWrappedConnection.setDefaultUseCaches(newValue);
  }

  public boolean getDoInput() {
    return this.mWrappedConnection.getDoInput();
  }

  public void setDoInput(boolean newValue) {
    this.mWrappedConnection.setDoInput(newValue);
  }

  public boolean getDoOutput() {
    return this.mWrappedConnection.getDoOutput();
  }

  public void setDoOutput(boolean newValue) {
    this.mWrappedConnection.setDoOutput(newValue);
  }

  public long getExpiration() {
    return this.mWrappedConnection.getExpiration();
  }

  public String getHeaderField(int pos) {
    this.postBeforeEventIfNecessary();

    String result;
    try {
      result = this.mWrappedConnection.getHeaderField(pos);
    } catch (Exception var4) {
      this.postCompleteEventIfNecessary(var4);
      throw var4;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public Map<String, List<String>> getHeaderFields() {
    this.postBeforeEventIfNecessary();

    Map result;
    try {
      result = this.mWrappedConnection.getHeaderFields();
    } catch (Exception var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public Map<String, List<String>> getRequestProperties() {
    return this.mWrappedConnection.getRequestProperties();
  }

  public void addRequestProperty(String field, String newValue) {
    this.mWrappedConnection.addRequestProperty(field, newValue);
  }

  public String getHeaderField(String key) {
    this.postBeforeEventIfNecessary();

    String result;
    try {
      result = this.mWrappedConnection.getHeaderField(key);
    } catch (Exception var4) {
      this.postCompleteEventIfNecessary(var4);
      throw var4;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public int getHeaderFieldInt(String field, int defaultValue) {
    this.postBeforeEventIfNecessary();

    int result;
    try {
      result = this.mWrappedConnection.getHeaderFieldInt(field, defaultValue);
    } catch (Exception var5) {
      this.postCompleteEventIfNecessary(var5);
      throw var5;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public String getHeaderFieldKey(int posn) {
    this.postBeforeEventIfNecessary();

    String result;
    try {
      result = this.mWrappedConnection.getHeaderFieldKey(posn);
    } catch (Exception var4) {
      this.postCompleteEventIfNecessary(var4);
      throw var4;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public long getIfModifiedSince() {
    return this.mWrappedConnection.getIfModifiedSince();
  }

  public void setIfModifiedSince(long newValue) {
    this.mWrappedConnection.setIfModifiedSince(newValue);
  }

  public InputStream getInputStream() throws IOException {
    this.postBeforeEventIfNecessary();

    InputStream result;
    try {
      result = this.mWrappedConnectionHelper.getInputStream(this.mWrappedConnection);
    } catch (IOException var3) {
      this.postCompleteEventIfNecessary(var3);
      throw var3;
    }

    this.postCompleteEventIfNecessary();
    return result;
  }

  public long getLastModified() {
    return this.mWrappedConnection.getLastModified();
  }

  public OutputStream getOutputStream() throws IOException {
    this.postBeforeEventIfNecessary();
    return this.mWrappedConnectionHelper.getOutputStream(this.mWrappedConnection);
  }

  public String getRequestProperty(String field) {
    return this.mWrappedConnection.getRequestProperty(field);
  }

  public URL getURL() {
    return this.mWrappedConnection.getURL();
  }

  public boolean getUseCaches() {
    return this.mWrappedConnection.getUseCaches();
  }

  public void setUseCaches(boolean newValue) {
    this.mWrappedConnection.setUseCaches(newValue);
  }

  public void setRequestProperty(String field, String newValue) {
    this.mWrappedConnection.setRequestProperty(field, newValue);
  }

  public int getConnectTimeout() {
    return this.mWrappedConnection.getConnectTimeout();
  }

  public void setConnectTimeout(int timeoutMillis) {
    this.mWrappedConnection.setConnectTimeout(timeoutMillis);
  }

  public int getReadTimeout() {
    return this.mWrappedConnection.getReadTimeout();
  }

  public void setReadTimeout(int timeoutMillis) {
    this.mWrappedConnection.setReadTimeout(timeoutMillis);
  }

  public String toString() {
    return this.mWrappedConnection.toString();
  }

  private void postBeforeEventIfNecessary() {
    this.mWrappedConnectionHelper.postBeforeEventIfNecessary();
  }

  private void postCompleteEventIfNecessary() {
    this.mWrappedConnectionHelper.postCompleteEventIfNecessary();
  }

  private void postCompleteEventIfNecessary(Exception exception) {
    this.mWrappedConnectionHelper.postCompleteEventIfNecessary(exception);
  }
}
