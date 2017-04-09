//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MirrorInputStream extends InputStream {
  private final InputStream mMain;
  private final List<OutputStream> mMirrors = new ArrayList();
  private int mMaxMirrorSize = 2147483647;
  private int mCurrentMirrorSize;
  private boolean mIsMaxMirrorSizeExceeded;
  private MirrorInputStream.StateChangedListener mStateChangedListener;

  MirrorInputStream(InputStream main) {
    this.mMain = main;
  }

  MirrorInputStream withMaxWriteMirrorSize(int maxMirrorSize) {
    this.mMaxMirrorSize = maxMirrorSize;
    return this;
  }

  MirrorInputStream withStateChangedListener(
      MirrorInputStream.StateChangedListener stateChangedListener) {
    this.mStateChangedListener = stateChangedListener;
    return this;
  }

  void addMirror(OutputStream stream) {
    this.mMirrors.add(stream);
  }

  void removeMirror(OutputStream stream) {
    this.mMirrors.remove(stream);
  }

  boolean isMaxMirrorSizeExceeded() {
    return this.mIsMaxMirrorSizeExceeded;
  }

  public int available() throws IOException {
    return this.mMain.available();
  }

  public void close() throws IOException {
    this.mMain.close();
    if (this.mStateChangedListener != null) {
      this.mStateChangedListener.onClosed();
    }
  }

  public void mark(int readlimit) {
    this.mMain.mark(readlimit);
  }

  public boolean markSupported() {
    return this.mMain.markSupported();
  }

  public int read(byte[] buffer) throws IOException {
    int readCount = this.mMain.read(buffer);
    if (!this.mIsMaxMirrorSizeExceeded && readCount > 0) {
      if (this.mCurrentMirrorSize + readCount > this.mMaxMirrorSize) {
        this.mIsMaxMirrorSizeExceeded = true;
      } else {
        Iterator var3 = this.mMirrors.iterator();

        while (var3.hasNext()) {
          OutputStream mirror = (OutputStream) var3.next();
          mirror.write(buffer, 0, readCount);
        }

        this.mCurrentMirrorSize += readCount;
      }

      return readCount;
    } else {
      return readCount;
    }
  }

  public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
    int readCount = this.mMain.read(buffer, byteOffset, byteCount);
    if (!this.mIsMaxMirrorSizeExceeded && readCount > 0) {
      if (this.mCurrentMirrorSize + readCount > this.mMaxMirrorSize) {
        this.mIsMaxMirrorSizeExceeded = true;
      } else {
        Iterator var5 = this.mMirrors.iterator();

        while (var5.hasNext()) {
          OutputStream mirror = (OutputStream) var5.next();
          mirror.write(buffer, byteOffset, readCount);
        }

        this.mCurrentMirrorSize += readCount;
      }

      return readCount;
    } else {
      return readCount;
    }
  }

  public synchronized void reset() throws IOException {
    this.mMain.reset();
  }

  public long skip(long byteCount) throws IOException {
    return this.mMain.skip(byteCount);
  }

  public int read() throws IOException {
    int readByte = this.mMain.read();
    if (!this.mIsMaxMirrorSizeExceeded && readByte >= 0) {
      if (this.mCurrentMirrorSize >= this.mMaxMirrorSize) {
        this.mIsMaxMirrorSizeExceeded = true;
      } else {
        Iterator var2 = this.mMirrors.iterator();

        while (var2.hasNext()) {
          OutputStream stream = (OutputStream) var2.next();
          stream.write(readByte);
        }

        ++this.mCurrentMirrorSize;
      }

      return readByte;
    } else {
      return readByte;
    }
  }

  interface StateChangedListener {
    void onClosed();
  }
}
