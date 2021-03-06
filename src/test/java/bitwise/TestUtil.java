package bitwise;

import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestUtil {

  public static void assertEquals(int expected, int actual) {
    Assert.assertEquals(bin(expected), bin(actual));
  }

  private static String bin(int value) {
    return leftPad(Integer.toBinaryString(value));
  }

  private static String leftPad(String str) {
    StringBuilder sb = new StringBuilder(32);
    for (int i = 0; i < 32 - str.length(); i++)
      sb.append('0');
    return sb.append(str).toString();
  }

  public static InputStream prepare(InputBuilder builder) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    builder.build(new DataOutputStream(baos));
    return new ByteArrayInputStream(baos.toByteArray());
  }

  public static void verify(ByteArrayOutputStream baos, Verifier verifier) throws IOException {
    verifier.verify(new DataInputStream(new ByteArrayInputStream(baos.toByteArray())));
  }

  public static byte[] readBytes(DataInputStream in, int count) throws IOException {
    byte[] received = new byte[count];
    in.readFully(received);
    return received;
  }

  @FunctionalInterface
  public interface Verifier {
    void verify(DataInputStream s) throws IOException;
  }

  @FunctionalInterface
  public interface InputBuilder {
    void build(DataOutputStream s) throws IOException;
  }
}
