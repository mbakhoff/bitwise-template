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
    return 0; // TODO: implement
  }

  public int getGreen() {
    return 0; // TODO: implement
  }

  public int getBlue() {
    return 0; // TODO: implement
  }

  public int getAlpha() {
    return 0; // TODO: implement
  }

  // two steps are needed to set the value:
  // 1) clear the old value using AND
  // 2) set the new values using left shift + OR

  // sample:
  // given rgba = 0x00FF00AB
  // setAlpha(0xBA) should result in rgba = 0x00FF00BA

  public void setRed(int value) {
    assertBounds(value);
    // TODO: implement
  }

  public void setGreen(int value) {
    assertBounds(value);
    // TODO: implement
  }

  public void setBlue(int value) {
    assertBounds(value);
    // TODO: implement
  }

  public void setAlpha(int value) {
    assertBounds(value);
    // TODO: implement
  }

  private void assertBounds(int value) {
    if (value < 0 || value > 255)
      throw new IllegalArgumentException(String.valueOf(value));
  }
}
