package cs3500.music.view;

/**
 * Interface for complex music editor views. These views must be able to
 * update the view based on a change in the model at given times.
 */
public interface ComplexViewOperations extends MusicEditorViewOperations {
  /**
   * Update music based on changes in the model.
   */
  void updateMusic();

  /**
   * Sets the key listener for this complex view.
   * @param k key listener
   */
  void setKeyListener(KeyboardListener k);

  /**
   * Sets the mouse handler for this complex view.
   * @param m mouse handler
   */
  void setMouseListener(MouseHandler m);

}
