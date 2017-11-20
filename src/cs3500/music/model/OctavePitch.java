package cs3500.music.model;

import java.util.Objects;

/**
 * Class to represent a specific pitch, meaning one that
 * has a pitch (like a note letter value) and an octave for that pitch.
 */
public class OctavePitch implements Comparable<OctavePitch> {
  private Octave octave;
  private Pitch pitch;

  /**
   * Constructor for an instance of OctavePitch.
   * Throws an error if given null octave or pitch.
   *
   * @param octave octave for this octave pitch
   * @param pitch  pitch for this octave pitch
   */
  public OctavePitch(Octave octave, Pitch pitch) {
    if (octave == null || pitch == null) {
      throw new IllegalArgumentException("Invalid octave or pitch");
    }
    this.octave = octave;
    this.pitch = pitch;
  }

  /**
   * Gets the octave component of this octave pitch.
   *
   * @return octave of this octave pitch
   */
  public Octave getOctave() {
    return this.octave;
  }

  /**
   * Gets the pitch component of this octave pitch.
   *
   * @return pitch of this octave pitch
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof OctavePitch)) {
      return false;
    }

    OctavePitch that = (OctavePitch) o;

    return this.pitch == that.pitch && this.octave == that.octave;
  }

  @Override
  public int hashCode() {
    return Objects.hash(octave, pitch);
  }

  @Override
  public String toString() {
    String ret = pitch.toString() + octave.toString();
    if (ret.length() == 2) {
      ret = (" " + ret + "  ");
    }
    else if (ret.length() == 3) {
      ret = (" " + ret + " ");
    }
    else if (ret.length() == 4) {
      ret = (ret + " ");
    }
    return ret;
  }

  @Override
  public int compareTo(OctavePitch o) {
    if (this.getFrequency() < o.getFrequency()) {
      return -1;
    }
    else if (this.getFrequency() > o.getFrequency()) {
      return 1;
    }
    else {
      return 0;
    }
  }

  /**
   * Gets the frequency of this pitch (letter value) at the given octave.
   * @return frequency of this pitch at the given octave
   */
  public double getFrequency() {
    double pow = octave.getValue();
    return pitch.getFrq() * Math.pow(2.0, pow);
  }

  /**
   * Gets a new octave pitch with the same octave and pitch.
   * @return copy of this octave pitch
   */
  public OctavePitch copy() {
    return new OctavePitch(octave, pitch);
  }

  /**
   * Gets the integer representation of this octavepitch.
   * @return int based off of octave pitch
   */
  public int toInt() {
    return (this.octave.ordinal() + 1) * 12 + this.pitch.ordinal();
  }
}
