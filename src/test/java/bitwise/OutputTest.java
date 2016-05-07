package bitwise;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class OutputTest {

  @Test
  public void testPositiveInt() throws Exception {
    int value = 0x0F1F2F3F;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Output.writeInt(baos, value);
    TestUtil.verify(baos, in -> {
      TestUtil.assertEquals(value, in.readInt());
      assertEquals("has no extra bytes", -1, in.read());
    });
  }

  @Test
  public void testNegativeInt() throws Exception {
    int value = 0xF0F1F2F3;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Output.writeInt(baos, value);
    TestUtil.verify(baos, in -> {
      TestUtil.assertEquals(value, in.readInt());
      assertEquals("has no extra bytes", -1, in.read());
    });
  }

  @Test
  public void testUTFNonEmpty() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Output.writeUTF(baos, "a1ö€");
    TestUtil.verify(baos, in -> {
      assertEquals("string length in bytes", 7, in.readInt());
      assertArrayEquals("string contents",
          new byte[]{97, 49, -61, -74, -30, -126, -84},
          in.readNBytes(7));
      assertEquals("has no extra bytes", -1, in.read());
    });
  }

  @Test
  public void testUTFEmpty() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Output.writeUTF(baos, "");
    TestUtil.verify(baos, in -> {
      assertEquals("string length in bytes", 0, in.readInt());
      assertEquals("has no extra bytes", -1, in.read());
    });
  }
}
