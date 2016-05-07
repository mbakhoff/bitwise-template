package bitwise;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Input {

  /**
   * Reads four input bytes and returns an int value.
   * Byte order is big-endian.
   */
  public static int readInt(InputStream is) throws IOException {
    // steps needed:
    // 1) read 4 bytes from the stream (readNBytes)
    // 2) use AND to mask out sign extension of each byte
    // 3) use left shift + OR to combine them

    byte[] b = is.readNBytes(4);
    int result = 0;
    result = result | ((b[0] & 0xFF) << 24);
    result = result | ((b[1] & 0xFF) << 16);
    result = result | ((b[2] & 0xFF) << 8);
    result = result | (b[3] & 0xFF);
    return result;
  }

  /**
   * Reads in a string that has been encoded using the UTF-8 format.
   * <p>
   * First, four bytes are read and used to construct an
   * integer in exactly the manner of the readInt method.
   * This integer value specifies the number of additional
   * bytes to be read. These bytes are then converted to a
   * String using UTF-8 encoding.
   */
  public static String readUTF(InputStream is) throws IOException {
    // hint: String has an useful constructor for byte[]
    int length = readInt(is);
    byte[] stringBytes = is.readNBytes(length);
    return new String(stringBytes, StandardCharsets.UTF_8);
  }
}
