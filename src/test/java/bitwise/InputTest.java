package bitwise;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;


public class InputTest {

  @Test
  public void testPositiveInt() throws Exception {
    int value = 0x0F1F2F3F;
    InputStream stream = TestUtil.prepare(out -> out.writeInt(value));
    TestUtil.assertEquals(value, Input.readInt(stream));
  }

  @Test
  public void testNegativeInt() throws Exception {
    int value = 0xF0F1F2F3;
    InputStream stream = TestUtil.prepare(out -> out.writeInt(value));
    TestUtil.assertEquals(value, Input.readInt(stream));
  }

  @Test
  public void testUTFNonEmpty() throws Exception {
    InputStream stream = TestUtil.prepare(out -> {
      out.writeInt(7);
      out.write(new byte[]{97, 49, -61, -74, -30, -126, -84});
    });
    assertEquals("a1ö€", Input.readUTF(stream));
  }

  @Test
  public void testTwoUTFNonEmpty() throws Exception {
    InputStream stream = TestUtil.prepare(out -> {
      out.writeInt(3);
      out.write(new byte[]{'a', 'b', 'c'});
      out.writeInt(3);
      out.write(new byte[]{'x', 'y', 'z'});
    });
    assertEquals("abc", Input.readUTF(stream));
    assertEquals("xyz", Input.readUTF(stream));
  }

  @Test
  public void testUTFEmpty() throws Exception {
    InputStream stream = TestUtil.prepare(out -> out.writeInt(0));
    assertEquals("<empty string>", "", Input.readUTF(stream));
  }
}
