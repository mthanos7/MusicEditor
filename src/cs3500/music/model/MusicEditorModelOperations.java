package cs3500.music.model;

import java.util.Collection;

/**
 * This is the interface of the music editor model.
 * It enables users to remove and add notes.
 * Users can also get a string representation of the state of their music editor
 * and a collection of copies of all the notes in this model.
 * They can also combine or overlay two pieces of music.
 * UPDATE: String representation moved to view.
 */
public interface MusicEditorModelOperations extends ReadOnlyModel {

  /**
   * Removes the first found instance of the given note from the model.
   * Throws IllegalArgumentException if the octave note
   * passed in is null.
   * @param n note to remove
   */
  void removeNote(Note n) throws IllegalArgumentException;

  /**
   * Adds the given note to the model.
   * Throws IllegalArgumentException if the given note is null.
   * @param n note to add
   */
  void addNote(Note n) throws IllegalArgumentException;


  /**
   * Adds all the given notes to this model.
   * Will overlay at the given beat, so users have the flexibility to
   * put one song on top of the other or overlap them only partially or
   * have them play consecutively.
   * Throws IllegalArgumentException if given a null collection of notes
   * or a negative overlay beat.
   * @param newNotes collection of notes to be added
   * @param overlayBeat where to overlay that onto this
   */
  void combine(Collection<Note> newNotes, int overlayBeat) throws IllegalArgumentException;

  /**
   * Sets the tempo of this model to the given value.
   * @param tempo new tempo for the model.
   */
  public void setTempo(int tempo);

  /**
   * Increase the current beat of the model.
   */
  void increaseTime();

  /**
   * Decrease the current beat of the model.
   */
  void decreaseTime();

  /**
   * Reset the current beat of the model to 0.
   */
  void resetTime();

  /**
   * Set the current beat of the model to the last beat.
   */
  void goToEndTime();
}
