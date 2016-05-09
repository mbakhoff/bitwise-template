package bitwise;

/**
 * Holds a color value with 8-bit red, green, blue and alpha components.
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

  public int getRed() {
    return ((rgba >> 24) & 0xFF);
  }

  public int getGreen() {
    return ((rgba >> 16) & 0xFF);
  }

  public int getBlue() {
    return ((rgba >> 8) & 0xFF);
  }

  public int getAlpha() {
    return (rgba & 0xFF);
  }

  public void setRed(int value) {
    assertBounds(value);
    rgba &= 0x00FFFFFF;
    rgba |= (value << 24);
  }

  public void setGreen(int value) {
    assertBounds(value);
    rgba &= 0xFF00FFFF;
    rgba |= (value << 16);
  }

  public void setBlue(int value) {
    assertBounds(value);
    rgba &= 0xFFFF00FF;
    rgba |= (value << 8);
  }

  public void setAlpha(int value) {
    assertBounds(value);
    rgba &= 0xFFFFFF00;
    rgba |= value;
  }

  private void assertBounds(int value) {
    if (value < 0 || value > 255)
      throw new IllegalArgumentException(String.valueOf(value));
  }
}
