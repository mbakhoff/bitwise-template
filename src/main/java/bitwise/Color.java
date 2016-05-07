package bitwise;

/**
 * Holds a color value with 8-bit red, green, blue and alpha (transparency) components.
 * The components are packed into a single 32-bit integer, where the
 * component order from left to right is: red, green, blue, alpha.
 */
public class Color {

  private int rgba;

  public int getPacked() {
    return rgba;
  }

  public void setPacked(int rgba) {
    this.rgba = rgba;
  }

  // use right shift + AND to extract the color values

  // samples:
  // given rgba = 0xAABBCCDD
  // getRed   should return 0xAA
  // getGreen should return 0xBB
  // getBlue  should return 0xCC
  // getAlpha should return 0xDD

  public int getRed() {
    return (rgba >> 24) & 0xFF;
  }

  public int getGreen() {
    return (rgba >> 16) & 0xFF;
  }

  public int getBlue() {
    return (rgba >> 8) & 0xFF;
  }

  public int getAlpha() {
    return rgba & 0xFF;
  }

  // two steps are needed to set the value:
  // 1) clear the old value using AND
  // 2) set the new values using left shift + OR

  // sample:
  // given rgba = 0x00FF00AB
  // setAlpha(0xBA) should result in rgba = 0x00FF00BA

  public void setRed(int value) {
    assertBounds(value);
    rgba = (rgba & 0x00FFFFFF) | (value << 24);
  }

  public void setGreen(int value) {
    assertBounds(value);
    rgba = (rgba & 0xFF00FFFF) | (value << 16);
  }

  public void setBlue(int value) {
    assertBounds(value);
    rgba = (rgba & 0xFFFF00FF) | (value << 8);
  }

  public void setAlpha(int value) {
    assertBounds(value);
    rgba = (rgba & 0xFFFFFF00) | value;
  }

  private void assertBounds(int value) {
    if (value < 0 || value > 255)
      throw new IllegalArgumentException(String.valueOf(value));
  }
}
