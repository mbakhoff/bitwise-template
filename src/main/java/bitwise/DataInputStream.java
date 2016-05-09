package bitwise;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInputStream extends FilterInputStream {

  public DataInputStream(InputStream in) {
    super(in);
  }

  /**
   * Reads four input bytes and returns an int value.
   */
  public int readInt() throws IOException {
    byte[] b = new byte[4];
    in.read(b);
    return java.nio.ByteBuffer.wrap(b).getInt();
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
  public String readUTF() throws IOException {
    byte[] bString = new byte[readInt()];
    in.read(bString);
    return new String(bString);
  }
}
