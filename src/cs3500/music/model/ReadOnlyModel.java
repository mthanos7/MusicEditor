package cs3500.music.model;

import java.util.ArrayList;

/**
 * This is the interface for a read-only music editor model. Gets notes, time, last beat,
 * and the tempo of a piece.
 */
public interface ReadOnlyModel {

  /**
   * Gets the current time (beat) of the model.
   * @return current time
   */
  int getTime();

  /**
   * Gets all the notes in the model that start inside the given range.
   * @param start beat
   * @param end beat
   * @return notes inside range
   */
  ArrayList<ArrayList<Note>> getNotes(int start, int end);

  /**
   * Gets the last beat of a piece of music.
   * @return last beat
   */
  int getLastBeat();

  /**
   * Gets the tempo of this model.
   * @return tempo
   */
  int getTempo();

  /**
   * Gets all the continued notes at the current beat in a model.
   * @return notes continuing at current beat
   */
  ArrayList<Note> getContinuedNotes();


  /**
   * Gets the range of OPs for whole piece.
   * @return range of ops
   */
  ArrayList<OctavePitch> getOPRange();
}
