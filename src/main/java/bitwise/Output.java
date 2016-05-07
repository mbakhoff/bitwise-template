package bitwise;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Output {

  /**
   * Writes an int to the underlying output stream as four bytes.
   * Byte order is big-endian.
   */
  public static void writeInt(OutputStream os, int v) throws IOException {
    // steps needed:
    // 1) use right shift + AND to isolate each byte
    // 2) write each byte separately
    os.write(v >> 24);
    os.write(v >> 16);
    os.write(v >> 8);
    os.write(v);
  }

  /**
   * Writes a string to the underlying output stream using UTF-8 encoding.
   * <p>
   * First, write four bytes using writeInt that show the size of the UTF-8
   * encoded string in bytes (not the string length). Next, write the UTF-8
   * encoded bytes of the string.
   */
  public static void writeUTF(OutputStream os, String str) throws IOException {
    // hint: use String#getBytes(Charset)
    byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
    writeInt(os, bytes.length);
    os.write(bytes);
  }
}
