package cs3500.music.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.music.model.MusicElement;
import cs3500.music.model.Note;
import cs3500.music.model.OctavePitch;
import cs3500.music.model.ReadOnlyModel;

/**
 * Representation of the state of the music editor as a string.
 * Builds the string representation and then prints it.
 */
public class StringViewImp implements MusicEditorViewOperations {
  private Appendable ap;
  private ReadOnlyModel model;


  /**
   * Constructor for a StringViewImp.
   * Throws an IllegalStateException if given a null appendable.
   * @param ap for giving output
   */
  public StringViewImp(Appendable ap, ReadOnlyModel model) throws IllegalStateException {
    if (ap == null) {
      throw new IllegalStateException("Appendable object has not been initialized.");
    }
    if (model == null) {
      throw new IllegalStateException("Read only model has not been initialized.");
    }
    this.ap = ap;
    this.model = model;
  }

  @Override
  public void renderMusicEditor() {
    if (model.getNotes(0, model.getLastBeat()) == null) {
      throw new IllegalArgumentException("Invalid collection of notes.");
    }
    try {
      ap.append(musicAsString());
    } catch (IOException e) {
      e.getMessage();
    }
    System.out.print(ap.toString());
  }


  /**
   * Builds a string that represents the entire piece of music.
   * @return string representation of the music editor
   */
  private String musicAsString() {
    int maxBeat = model.getLastBeat();
    ArrayList<ArrayList<Note>> notes = model.getNotes(0, maxBeat);
    boolean empty = true;
    for (ArrayList<Note> arr : notes) {
      for (Note n : arr) {
        empty = false;
      }
    }
    if (empty) {
      return "";
    }
    ArrayList<OctavePitch> ops = MusicEditorViewUtils.getOPRange(notes);
    int numLen = getNumLen(maxBeat);
    MusicElement[][] elements = MusicEditorViewUtils.getElements(ops, maxBeat + 1, notes);

    String ret = "";
    for (int i = 0; i < numLen; i++) {
      ret += " ";
    }
    for (int i = 0; i < ops.size(); i++) {
      ret += ops.get(i).toString();
    }
    return ret + "\n" + buildString(elements, maxBeat, numLen, ops);
  }

  /**
   * Gets number of digits in the last beat of the piece.
   * 
   * @param maxBeat last beat of the piece
   * @return number of digits in last beat
   */
  private int getNumLen(int maxBeat) {
    int numLen = 1;
    if (maxBeat - 1 >= 10000) {
      numLen = 5;
    }
    else if (maxBeat - 1 >= 1000) {
      numLen = 4;
    }
    else if (maxBeat - 1 >= 100) {
      numLen = 3;
    }
    else if (maxBeat - 1 >= 10) {
      numLen = 2;
    }
    return numLen;
  }

  /**
   * Creates a string of notes in the piece labeled by their beat number.
   * If more than one note take up a beat, new notes will be shown over continued notes.
   * 
   * @param elements 2d array of music elements
   * @param maxBeat max beat of notes
   * @param numLen digits in biggest beat number
   * @param ops all octave pitches in piece
   * @return notes in piece labeled by beat number
   */
  private String buildString(MusicElement[][] elements,
                             int maxBeat, int numLen, ArrayList<OctavePitch> ops) {
    String ret = "";
    for (int i = 0; i < maxBeat; i++) {
      String num = Integer.toString(i);
      while (num.length() < numLen) {
        num = " " + num;
      }
      ret += num;
      for (int j = 0; j < ops.size(); j++) {
        if (elements[j][i] == null) {
          ret += MusicElement.REST.toString();
        } else {
          ret += elements[j][i].toString();
        }
      }
      ret += "\n";
    }
    return ret;
  }
}
