package cs3500.music.model;

/**
 * Represents a music note with a start beat, end beat
 * and an octave and a pitch.
 * UPDATE: Holds more fields for reading from music files.
 */
public class Note {
  private int startBeat;
  private int endBeat;
  private int instrument;
  private int pitch;
  private int volume;
  private OctavePitch op;

  /**
   * Constructor for a note.
   *
   * @param startBeat  first beat of this note
   * @param endBeat    last beat of this note
   * @param pitch      pitch of the note
   * @param instrument instrument of note
   * @param volume     volume of note
   */
  public Note(int startBeat, int endBeat, int pitch, int instrument, int volume) {
    if (startBeat < 0 || endBeat < startBeat || pitch < 0
            || pitch > 120 || instrument < 0 || volume < 0) {
      throw new IllegalArgumentException("Invalid note parameters.");
    }

    this.startBeat = startBeat;
    this.endBeat = endBeat;
    this.instrument = instrument;
    this.pitch = pitch;
    this.volume = volume;
    this.op = getOP(pitch);
  }

  /**
   * Converts pitch int value into an octave pitch.
   *
   * @param pitch int pitch value
   * @return octave pitch of this note
   */
  private OctavePitch getOP(int pitch) {
    Pitch p = getPitch(pitch % 12);
    Octave o = getOctave(pitch / 12 - 1);
    return new OctavePitch(o, p);
  }

  /**
   * Gets the pitch corresponding to the integer value.
   *
   * @param p pitch number
   * @return pitch corresponding to p
   */
  private Pitch getPitch(int p) {
    Pitch pitch;
    switch (p) {
      case 0:
        pitch = Pitch.C;
        break;
      case 1:
        pitch = Pitch.CSHARP;
        break;
      case 2:
        pitch = Pitch.D;
        break;
      case 3:
        pitch = Pitch.DSHARP;
        break;
      case 4:
        pitch = Pitch.E;
        break;
      case 5:
        pitch = Pitch.F;
        break;
      case 6:
        pitch = Pitch.FSHARP;
        break;
      case 7:
        pitch = Pitch.G;
        break;
      case 8:
        pitch = Pitch.GSHARP;
        break;
      case 9:
        pitch = Pitch.A;
        break;
      case 10:
        pitch = Pitch.ASHARP;
        break;
      default:
        pitch = Pitch.B;
        break;
    }
    return pitch;
  }

  /**
   * Gets the octave corresponding to the integer value.
   *
   * @param o octave number
   * @return octave corresponding to o
   */
  private Octave getOctave(int o) {
    Octave oct;
    switch (o) {
      case 0:
        oct = Octave.ZERO;
        break;
      case 1:
        oct = Octave.ONE;
        break;
      case 2:
        oct = Octave.TWO;
        break;
      case 3:
        oct = Octave.THREE;
        break;
      case 4:
        oct = Octave.FOUR;
        break;
      case 5:
        oct = Octave.FIVE;
        break;
      case 6:
        oct = Octave.SIX;
        break;
      case 7:
        oct = Octave.SEVEN;
        break;
      case 8:
        oct = Octave.EIGHT;
        break;
      case 9:
        oct = Octave.NINE;
        break;
      default:
        oct = Octave.TEN;
        break;
    }
    return oct;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Note)) {
      return false;
    }

    Note that = (Note) o;

    return this.startBeat == that.startBeat && this.endBeat == that.endBeat
            && this.op.equals(that.op);
  }

  /**
   * Gets the integer representation of this note based off of its octavepitch.
   *
   * @return note as int based off of octave pitch
   */
  public int noteAsInt() {
    return op.toInt();
  }

  /**
   * Gets the instrument of this note.
   *
   * @return instrument of note
   */
  public int getInstrument() {
    return instrument;
  }

  /**
   * Gets the pitch of this note.
   *
   * @return pitch of note
   */
  public int getPitchInInt() {
    return pitch;
  }

  /**
   * Gets the volume of this note.
   *
   * @return volume of note
   */
  public int getVolume() {
    return volume;
  }

  @Override
  public int hashCode() {
    int result = startBeat;
    result = 31 * result + endBeat;
    result = 31 * result + op.hashCode();
    return result;
  }

  /**
   * Gets the starting beat of this note.
   *
   * @return first beat of note
   */
  public int getStartBeat() {
    return startBeat;
  }

  /**
   * Gets the ending beat of this note.
   *
   * @return last beat of note
   */
  public int getEndBeat() {
    return endBeat;
  }

  /**
   * Creates a new note with the same start and end beat offset
   * by given value and the same octave and pitch.
   *
   * @param overlayBeat value to offset beats by
   * @return note offset by given value
   */
  public Note changeStart(int overlayBeat) {
    if (overlayBeat < 0) {
      throw new IllegalArgumentException("Negative beat change.");
    }
    return new Note(startBeat + overlayBeat, endBeat + overlayBeat,
            pitch, instrument, volume);
  }

  /**
   * Gets the octave pitch of this note.
   *
   * @return octave and pitch
   */
  public OctavePitch getOctavePitch() {
    return new OctavePitch(op.getOctave(), op.getPitch());
  }

  /**
   * Check if this note is in the given range of beats.
   * @param start of range
   * @param end end of range
   * @return true if in range, false otherwise
   */
  public boolean inRange(int start, int end) {
    return startBeat >= start && startBeat <= end;
  }

  /**
   * Check if this note exists at the given time.
   * @param time for note
   * @return true if note exists at time, false otherwise
   */
  public boolean existsAt(int time) {
    return (startBeat <= time && endBeat >= time);
  }
}
