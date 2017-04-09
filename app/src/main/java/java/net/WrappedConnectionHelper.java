//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java.net;

import android.os.Bundle;
import android.util.Log;
import com.bugsee.library.network.NetworkEventsShared;
import com.bugsee.library.network.data.BufferInfo;
import com.bugsee.library.network.data.ConnectionStateToReport;
import com.bugsee.library.util.LogWrapper;
import com.bugsee.library.util.StringUtils;
import com.spranga.architecture2017.SprangaApplication;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

class WrappedConnectionHelper {
  private static final String TAG = "MYLOG";
  private final HttpURLConnection mWrappedConnection;
  private final boolean mIsVerbose;
  private final String mLogTag;
  private final MirrorInputStream.StateChangedListener mInputStreamStateChangedListener;
  private MirrorOutputStream mOutputStream;
  private ByteArrayOutputStream mOutputStreamMirror = new ByteArrayOutputStream();
  private MirrorInputStream mInputStream;
  private ByteArrayOutputStream mInputStreamMirror = new ByteArrayOutputStream();
  private ConnectionStateToReport mState;
  private String mEventId;

  WrappedConnectionHelper(HttpURLConnection wrappedConnection, boolean isVerbose, String logTag) {
    this.mState = ConnectionStateToReport.Before;
    this.mInputStreamStateChangedListener = new MirrorInputStream.StateChangedListener() {
      public void onClosed() {
        Log.d(TAG, "onClosed() called");
        //NetworkEventsCollector.getInstance().postInputStreamClosedEvent(WrappedConnectionHelper.this.mWrappedConnection, WrappedConnectionHelper.this.getInputStreamInfo(), WrappedConnectionHelper.this.mEventId);
      }
    };
    this.mWrappedConnection = wrappedConnection;
    this.mIsVerbose = isVerbose;
    this.mLogTag = logTag;
  }

  public static boolean isAndroidBodySource(Object object) {
    if (object == null) {
      return false;
    } else {
      String simpleClassName = StringUtils.getSimpleClassName(object.getClass().getName());
      return "RealBufferedSource$1".equals(simpleClassName);
    }
  }

  OutputStream getOutputStream(HttpURLConnection wrappedConnection) throws IOException {
    if (this.mOutputStream == null) {
      this.mOutputStream =
          (new MirrorOutputStream(wrappedConnection.getOutputStream())).withMaxWriteMirrorSize(
              NetworkEventsShared.MAX_BODY_LENGTH);
      this.mOutputStream.addMirror(this.mOutputStreamMirror);
    }

    return this.mOutputStream;
  }

  InputStream getInputStream(HttpURLConnection wrappedConnection) throws IOException {
    if (this.mInputStream == null) {
      InputStream wrappedConnectionInputStream = wrappedConnection.getInputStream();
      if (isAndroidBodySource(wrappedConnectionInputStream)
          || wrappedConnectionInputStream.markSupported()) {
        return wrappedConnectionInputStream;
      }

      this.mInputStream =
          (new MirrorInputStream(wrappedConnectionInputStream)).withMaxWriteMirrorSize(
              NetworkEventsShared.MAX_BODY_LENGTH)
              .withStateChangedListener(this.mInputStreamStateChangedListener);
      this.mInputStream.addMirror(this.mInputStreamMirror);
    }

    return this.mInputStream;
  }

  void postBeforeEventIfNecessary() {
    try {
      if (this.mState == ConnectionStateToReport.Before) {
        if (this.mIsVerbose) {
          LogWrapper.info(this.mLogTag, "postBeforeEvent " + System.identityHashCode(this));
        }

        this.mState = ConnectionStateToReport.Complete;
        Log.d(TAG, "postBeforeEventIfNecessary() called");
        //        NetworkEventsCollector.getInstance().postBeforeEvent(this.mWrappedConnection, this.getEventId());
      }
    } catch (OutOfMemoryError | Exception var2) {
      LogWrapper.logException(this.mLogTag, "Failed to generate 'before' network event.", var2);
    }
  }

  void postCompleteEventIfNecessary() {
    this.postCompleteEventIfNecessary(null);
  }

  void postCompleteEventIfNecessary(Exception exception) {
    try {
      if (this.mState == ConnectionStateToReport.Complete) {
        if (this.mIsVerbose) {
          LogWrapper.info(this.mLogTag, "postCompleteEvent " + System.identityHashCode(this));
        }

        this.mState = ConnectionStateToReport.Finished;
        HttpURLConnection c = this.mWrappedConnection;
        Bundle bundle = new Bundle();
        bundle.putString("REQUEST_VERB", c.getRequestMethod());
        bundle.putString("REQUEST_URL", c.getURL().getPath());

        bundle.putString("RESPONSE_CODE", "" + c.getResponseCode());
        bundle.putString("RESPONSE_MSG", c.getResponseMessage());

        SprangaApplication.Companion.logger().accept(bundle);
        //NetworkEventsCollector.getInstance().postCompleteEvent(this.mWrappedConnection, this.getOutputStreamInfo(), exception, this.getEventId());
      }
    } catch (OutOfMemoryError | Exception var3) {
      LogWrapper.logException(this.mLogTag, "Failed to generate 'complete' network event.", var3);
    }
  }

  private BufferInfo getOutputStreamInfo() {
    if (this.mOutputStream == null) {
      return null;
    } else {
      byte[] buffer = this.mOutputStream.isMaxMirrorSizeExceeded() ? null
          : this.mOutputStreamMirror.toByteArray();
      return new BufferInfo(buffer, this.mOutputStream.isMaxMirrorSizeExceeded());
    }
  }

  private BufferInfo getInputStreamInfo() {
    if (this.mInputStream == null) {
      return null;
    } else {
      byte[] buffer = this.mInputStream.isMaxMirrorSizeExceeded() ? null
          : this.mInputStreamMirror.toByteArray();
      return new BufferInfo(buffer, this.mInputStream.isMaxMirrorSizeExceeded());
    }
  }

  private String getEventId() {
    if (this.mEventId == null) {
      this.mEventId = UUID.randomUUID().toString();
    }

    return this.mEventId;
  }
}
