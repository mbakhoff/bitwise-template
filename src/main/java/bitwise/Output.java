package bitwise;

import java.io.IOException;
import java.io.OutputStream;

public class Output {

  /**
   * Writes an int to the underlying output stream as four bytes, high byte first.
   */
  public static void writeInt(OutputStream os, int v) throws IOException {
    // TODO: implement
  }

  /**
   * Writes a string to the underlying output stream using UTF-8 encoding.
   * <p>
   * First, four bytes are written to the output stream as if by the writeInt
   * method giving the number of bytes to follow. This value is the number of
   * bytes actually written out, not the length of the string. Following the
   * length, UTF-8 encoded bytes of the string are outputted.
   */
  public static void writeUTF(OutputStream os, String str) throws IOException {
    // TODO: implement
  }
}
