package cs3500.music.controller;


import cs3500.music.model.MusicEditorModelOperations;
import cs3500.music.view.MusicEditorViewOperations;

/**
 * Simple controller for view such as the String view that do not care about time or
 * key/mouse events.
 */
public class SimpleController implements MusicEditorController {
  private MusicEditorViewOperations view;

  /**
   * Constructor for a simple controller.
   * @param view simple view
   * @param model music model
   */
  public SimpleController(MusicEditorViewOperations view, MusicEditorModelOperations model) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Invalid view or model for simple controller.");
    }
    this.view = view;
    renderMusic();
  }


  @Override
  public void renderMusic() {
    view.renderMusicEditor();
  }
}
