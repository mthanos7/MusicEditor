package cs3500.music.view;

import java.util.ArrayList;

import cs3500.music.model.MusicElement;
import cs3500.music.model.Note;
import cs3500.music.model.Octave;
import cs3500.music.model.OctavePitch;
import cs3500.music.model.Pitch;

/**
 * Utils class to help string and GIU views. Given a collection of notes,
 * can find the range of pitches in a piece,
 * the last beat, and the most important note at each beat
 * (in order new note, continued note, rest).
 */
public class MusicEditorViewUtils {
  /**
   * Gets a list of music elements in the piece.
   * If more than one note take up a beat, new notes will be stored rather than continued notes.
   *
   * @param ops      all octave pitches in piece
   * @param maxBeat  max beat of notes
   * @return music elements in piece
   */
  public static MusicElement[][] getElements(ArrayList<OctavePitch> ops, int maxBeat,
                                             ArrayList<ArrayList<Note>> notes) {
    if (ops == null || maxBeat < 0 || notes == null) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    MusicElement[][] elements = new MusicElement[ops.size()][maxBeat + 1];


    for (ArrayList<Note> arr : notes) {
      for (Note n : arr) {
        int opIdx = ops.indexOf(n.getOctavePitch());
        elements[opIdx][n.getStartBeat()] = MusicElement.NEW_NOTE;
        for (int i = n.getStartBeat() + 1; i < n.getEndBeat(); i++) {
          if (elements[opIdx][i] != MusicElement.NEW_NOTE) {
            elements[opIdx][i] = MusicElement.CONTINUED_NOTE;
          }
        }
      }
    }

    return elements;
  }


  /**
   * Gets a list of all pitches in the piece.
   *
   * @return range of all pitches in the piece
   */
  public static ArrayList<OctavePitch> getOPRange(ArrayList<ArrayList<Note>> notes) {
    if (notes == null) {
      throw new IllegalArgumentException("Invalid notes.");
    }
    OctavePitch min = getMinOP(notes);
    OctavePitch max = getMaxOP(notes);


    return pitchRange(min, max);
  }

  /**
   * Gets the last octave pitch in the piece.
   *
   * @return last octave pitch
   */
  private static OctavePitch getMaxOP(ArrayList<ArrayList<Note>> notes) {
    OctavePitch max = new OctavePitch(Octave.ZERO, Pitch.C);
    for (ArrayList<Note> arr : notes) {
      for (Note n : arr) {
        if (n.getOctavePitch().compareTo(max) >= 0) {
          max = n.getOctavePitch();
        }
      }
    }

    return max;
  }

  /**
   * Gets the first octave pitch in the piece.
   *
   * @return first octave pitch
   */
  private static OctavePitch getMinOP(ArrayList<ArrayList<Note>> notes) {
    OctavePitch min = new OctavePitch(Octave.TEN, Pitch.B);
    for (ArrayList<Note> arr : notes) {
      for (Note n : arr) {
        if (n.getOctavePitch().compareTo(min) <= 0) {
          min = n.getOctavePitch();
        }
      }
    }
    return min;
  }

  /**
   * Gets all pitches from the lowest given pitch to the highest given pitch.
   *
   * @param lowest  lower bound of pitches to add
   * @param highest upper bound of pitches to add
   * @return list of range of octave pitches
   */
  private static ArrayList<OctavePitch> pitchRange(OctavePitch lowest, OctavePitch highest) {
    if (lowest == null) {
      throw new IllegalArgumentException("Invalid lowest pitch.");
    }
    if (highest == null) {
      throw new IllegalArgumentException("Invalid highest pitch.");
    }
    if (lowest.compareTo(highest) > 0) {
      throw new IllegalArgumentException("Invalid pitch combination.");
    }
    ArrayList<OctavePitch> pitches = new ArrayList<OctavePitch>();
    OctavePitch cur = lowest;
    while (!cur.equals(highest)) {
      pitches.add(cur);
      if (cur.getPitch() == Pitch.B) {
        cur = new OctavePitch(cur.getOctave().getNext(), cur.getPitch().getNext());
      } else {
        cur = new OctavePitch(cur.getOctave(), cur.getPitch().getNext());
      }
    }
    pitches.add(highest);
    return pitches;
  }
}
