package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collection;

import cs3500.music.view.MusicEditorViewUtils;

/**
 * Represents the state of a music editor by using
 * a list of lists of notes organized by their starting beat.
 */
public class MusicEditorModel implements MusicEditorModelOperations {
  private ArrayList<ArrayList<Note>> notes;
  private int tempo;
  private int time;

  /**
   * Constructs a MusicEditorModel object.
   * Initializes the list of lists of notes to an empty list.
   */
  public MusicEditorModel() {
    this.notes = new ArrayList<ArrayList<Note>>();
    this.tempo = 200000;
    this.time = 0;
  }


  @Override
  public void removeNote(Note n) {
    if (n == null) {
      throw new IllegalArgumentException("Invalid note.");
    }
    boolean foundN = false;
    for (ArrayList<Note> arr : notes) {
      for (Note note : arr) {
        if (note.equals(n)) {
          foundN = true;
          arr.remove(n);
          break;
        }
      }
    }
    if (!foundN) {
      throw new IllegalArgumentException("Note is not in song.");
    }
  }

  @Override
  public void addNote(Note n) {
    if (n == null) {
      throw new IllegalArgumentException("Invalid note.");
    }
    int start = n.getStartBeat();
    if (start >= notes.size()) {
      int dif = start - notes.size();
      for (int i = 0; i <= dif; i++) {
        notes.add(new ArrayList<Note>());
      }
    }
    notes.get(start).add(n);
  }

  @Override
  public void combine(Collection<Note> newNotes, int overlayBeat) {
    if (newNotes == null) {
      throw new IllegalArgumentException("Invalid model to combine.");
    }
    if (overlayBeat < 0) {
      throw new IllegalArgumentException("Invalid overlay beat.");
    }
    for (Note n : newNotes) {
      Note n2 = n.changeStart(overlayBeat);
      addNote(n2);
    }
  }

  @Override
  public int getTime() {
    return time;
  }

  @Override
  public ArrayList<ArrayList<Note>> getNotes(int start, int end) {
    ArrayList<ArrayList<Note>> copy = new  ArrayList<ArrayList<Note>>();
    for (ArrayList<Note> arr : notes) {
      ArrayList<Note> arr2 = new ArrayList<Note>();
      copy.add(arr2);
      for (Note note : arr) {
        if (note.inRange(start, end)) {
          arr2.add(note.changeStart(0));
        }
      }
    }
    return copy;
  }

  @Override
  public int getLastBeat() {
    int max = -1;
    for (ArrayList<Note> arr : notes) {
      for (Note n : arr) {
        if (n.getEndBeat() > max) {
          max = n.getEndBeat();
        }
      }
    }
    return max;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public void increaseTime() {
    time++;
  }

  @Override
  public void decreaseTime() {
    time--;
  }

  @Override
  public void resetTime() {
    time = 0;
  }

  @Override
  public void goToEndTime() {
    time = getLastBeat();
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public ArrayList<Note> getContinuedNotes() {
    ArrayList<Note> contNotes = new ArrayList<Note>();
    for (ArrayList<Note> arr : notes) {
      for (Note note : arr) {
        if (note.existsAt(time)) {
          contNotes.add(note);
        }
      }
    }
    return contNotes;
  }

  @Override
  public ArrayList<OctavePitch> getOPRange() {
    return MusicEditorViewUtils.getOPRange(notes);
  }

}
