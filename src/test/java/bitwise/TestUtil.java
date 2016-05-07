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
    return "0".repeat(Math.max(0, 32 - str.length())) + str;
  }

  public static InputStream prepare(InputBuilder builder) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    builder.build(new DataOutputStream(baos));
    return new ByteArrayInputStream(baos.toByteArray());
  }

  public static void verify(ByteArrayOutputStream baos, Verifier verifier) throws IOException {
    verifier.verify(new DataInputStream(new ByteArrayInputStream(baos.toByteArray())));
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
