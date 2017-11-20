package cs3500.music.model;

/**
 * Represents a music element which has a duration of one beat.
 * This can be a note, a new note, or a rest.
 */
public enum MusicElement {
  NEW_NOTE("  X  "), CONTINUED_NOTE("  |  "), REST("     ");

  private String asString;

  /**
   * Constructor for a music element,
   * has the string representation of this element as a field.
   * @param asString string representation of this element
   */
  MusicElement(String asString) {
    this.asString = asString;
  }

  @Override
  public String toString() {
    return asString;
  }
}
