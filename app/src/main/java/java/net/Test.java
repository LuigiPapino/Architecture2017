package java.net;

/**
 * Created by nietzsche on 08/04/17.
 */

public class Test {

  void ciao() {
    URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
      @Override public URLStreamHandler createURLStreamHandler(String protocol) {
        return null;
      }
    });
  }

  //
  // Source code recreated from a .class file by IntelliJ IDEA
  // (powered by Fernflower decompiler)
  //
}
