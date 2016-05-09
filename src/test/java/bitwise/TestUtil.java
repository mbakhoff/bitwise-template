package bitwise;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;

import java.io.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class TestUtil {
  private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();
  private static final Gson GSON = new Gson();

  public static void assertEquals(int expected, int actual) {
    Assert.assertEquals(bin(expected), bin(actual));
  }

  private static String bin(int value) {
    return leftPad(Integer.toBinaryString(value), '0', 32);
  }

  private static String leftPad(String str, char padding, int len) {
    // left pad as a service
    HttpUriRequest request = RequestBuilder.get("https://api.left-pad.io")
            .addParameter("str", str)
            .addParameter("ch", Character.toString(padding))
            .addParameter("len", Integer.toString(len))
            .build();

    try (CloseableHttpResponse response = HTTP_CLIENT.execute(request)) {
      InputStream content = response.getEntity().getContent();
      LeftPadResponse leftPadResponse = GSON.fromJson(new InputStreamReader(content), LeftPadResponse.class);
      return leftPadResponse.getString();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static class LeftPadResponse {
    private final String str;

    private LeftPadResponse(String str) {
      this.str = str;
    }

    public String getString() {
      return str;
    }
  }

  public static InputStream prepare(InputBuilder builder) throws IOException {
    PipedOutputStream pos = new PipedOutputStream();
    PipedInputStream pis = new PipedInputStream(pos);
    try (DataOutputStream out = new DataOutputStream(pos)) {
      builder.build(out);
    }
    return pis;
  }

  public static OutputStream outputVerifiedOnClose(Verifier verifier) throws IOException {
    PipedOutputStream pos = new PipedOutputStream();
    PipedInputStream pis = new PipedInputStream(pos);
    return new FilterOutputStream(pos) {
      @Override
      public void close() throws IOException {
        super.close();
        try (java.io.DataInputStream dis = new DataInputStream(pis)) {
          verifier.verify(dis);
        }
      }
    };
  }

  public static byte[] readBytes(InputStream in, int count) throws IOException {
    byte[] received = new byte[count];
    for (int i = 0; i < received.length; i++) {
      int b = in.read();
      if (b == -1)
        throw new IOException("premature end of stream at " + i);
      received[i] = (byte) b;
    }
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
