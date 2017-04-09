//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java.net;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MirrorOutputStream extends OutputStream {
  private final OutputStream mMain;
  private final List<OutputStream> mMirrors = new ArrayList();
  private int mMaxMirrorSize = 2147483647;
  private int mCurrentMirrorSize;
  private boolean mIsMaxMirrorSizeExceeded;

  MirrorOutputStream(OutputStream main) {
    this.mMain = main;
  }

  MirrorOutputStream withMaxWriteMirrorSize(int maxMirrorSize) {
    this.mMaxMirrorSize = maxMirrorSize;
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

  public void write(int oneByte) throws IOException {
    this.mMain.write(oneByte);
    if (!this.mIsMaxMirrorSizeExceeded) {
      if (this.mCurrentMirrorSize >= this.mMaxMirrorSize) {
        this.mIsMaxMirrorSizeExceeded = true;
      } else {
        Iterator var2 = this.mMirrors.iterator();

        while (var2.hasNext()) {
          OutputStream stream = (OutputStream) var2.next();
          stream.write(oneByte);
        }

        ++this.mCurrentMirrorSize;
      }
    }
  }

  public void close() throws IOException {
    this.mMain.close();
    Iterator var1 = this.mMirrors.iterator();

    while (var1.hasNext()) {
      OutputStream stream = (OutputStream) var1.next();
      stream.close();
    }
  }

  public void flush() throws IOException {
    this.mMain.flush();
    Iterator var1 = this.mMirrors.iterator();

    while (var1.hasNext()) {
      OutputStream stream = (OutputStream) var1.next();
      stream.flush();
    }
  }

  public void write(byte[] buffer) throws IOException {
    this.mMain.write(buffer);
    if (!this.mIsMaxMirrorSizeExceeded) {
      if (this.mCurrentMirrorSize + buffer.length > this.mMaxMirrorSize) {
        this.mIsMaxMirrorSizeExceeded = true;
      } else {
        Iterator var2 = this.mMirrors.iterator();

        while (var2.hasNext()) {
          OutputStream stream = (OutputStream) var2.next();
          stream.write(buffer);
        }

        this.mCurrentMirrorSize += buffer.length;
      }
    }
  }

  public void write(byte[] buffer, int offset, int count) throws IOException {
    this.mMain.write(buffer, offset, count);
    if (!this.mIsMaxMirrorSizeExceeded) {
      if (this.mCurrentMirrorSize + count >= this.mMaxMirrorSize) {
        this.mIsMaxMirrorSizeExceeded = true;
      } else {
        Iterator var4 = this.mMirrors.iterator();

        while (var4.hasNext()) {
          OutputStream stream = (OutputStream) var4.next();
          stream.write(buffer, offset, count);
        }

        this.mCurrentMirrorSize += count;
      }
    }
  }
}
