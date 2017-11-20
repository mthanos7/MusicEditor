package cs3500.music.model;

/**
 * Represents different octave values.
 * Limited to reasonable values that can be heard by humans: 0-10.
 */
public enum Octave {
  ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
  SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

  private int val;

  /**
   * Constructor for an octave,
   * has the octaves numerical value as a field.
   * @param val numerical value of this octave
   */
  Octave(int val) {
    this.val = val;
  }

  /**
   * Gets the octave that comes after this octave.
   * @return the next octave in the sequence
   */
  public Octave getNext() {
    switch (this) {
      case ZERO:
        return ONE;
      case ONE:
        return TWO;
      case TWO:
        return THREE;
      case THREE:
        return FOUR;
      case FOUR:
        return FIVE;
      case FIVE:
        return SIX;
      case SIX:
        return SEVEN;
      case SEVEN:
        return EIGHT;
      case EIGHT:
        return NINE;
      default:
        return TEN;
    }
  }

  @Override
  public String toString() {
    return Integer.toString(val);
  }

  /**
   * Gets the numerical value of this octave as an int.
   * @return numerical value of octave
   */
  public int getValue() {
    return val;
  }
}
