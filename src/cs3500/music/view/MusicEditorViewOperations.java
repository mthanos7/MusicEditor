package cs3500.music.view;

/**
 * This is the interface of the music editor view.
 * It enables users to simply render some view of the piece of music
 * when given a collection of notes.
 */
public interface MusicEditorViewOperations {
  /**
   * Renders the view of the music editor.
   * Throws IllegalArgumentException if given a null collection of notes.
   */
  void renderMusicEditor() throws IllegalArgumentException;

}
