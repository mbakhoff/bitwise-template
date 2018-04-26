package bitwise;

import java.io.IOException;
import java.io.InputStream;

public class Input {

  /**
   * Reads four input bytes and returns an int value.
   * Byte order is big-endian.
   */
  public static int readInt(InputStream is) throws IOException {
    // steps needed:
    // 1) read 4 bytes from the stream
    // 2) use left shift + OR to combine them

    // note that is.read() returns an int. the value either
    // contains the byte value or -1 if end of stream was
    // reached. if the value is not -1, then it is safe
    // to cast the int to a byte.

    // a more efficent solution would use is.read(byte[])
    // to read multiple bytes with a single call. don't
    // forget to check the return value for the number of
    // bytes that was actually read into the buffer.

    return 0; // TODO: implement
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
    return null; // TODO: implement
  }
}
