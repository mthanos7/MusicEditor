package cs3500.music.model;

/**
 * Represents different pitches of notes.
 * I've chosen this representation because (for this project)
 * there are only 12 possible pitches for a note.
 */
public enum Pitch {
  C(16.35, "C"), CSHARP(17.32, "C#"), D(18.35, "D"), DSHARP(19.45, "D#"), E(20.60, "E"),
  F(21.83, "F"), FSHARP(23.12, "F#"), G(24.50, "G"), GSHARP(25.96, "G#"), A(27.50, "A"),
  ASHARP(29.14, "A#"), B(30.87, "B");

  private final double frq;
  private final String asString;

  /**
   * Constructor for a pitch.
   * Has frequency at the 0th octave as a field
   * as well as a string representation of this pitch.
   * @param frq frequency at the 0th octave
   * @param asString string representation of this pitch
   */
  Pitch(double frq, String asString) {
    this.asString = asString;
    this.frq = frq;
  }

  /**
   * Gets the pitch that comes after this pitch.
   * @return the next pitch in the sequence
   */
  public Pitch getNext() {
    switch (this) {
      case C:
        return CSHARP;
      case CSHARP:
        return D;
      case D:
        return DSHARP;
      case DSHARP:
        return E;
      case E:
        return F;
      case F:
        return FSHARP;
      case FSHARP:
        return G;
      case G:
        return GSHARP;
      case GSHARP:
        return A;
      case A:
        return ASHARP;
      case ASHARP:
        return B;
      default:
        return C;
    }
  }

  @Override
  public String toString() {
    return this.asString;
  }

  /**
   * Gets the base frequency of this pitch.
   * @return frequency of pitch
   */
  public double getFrq() {
    return frq;
  }

}
