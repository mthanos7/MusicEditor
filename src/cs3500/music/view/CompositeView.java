package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.MusicEditorModelOperations;

/**
 * The composite view is a view that displays the gui view and also plays the midi view at the
 * same time. It has both of these as fields and calls their methods.
 */
public class CompositeView implements ComplexViewOperations {
  private GuiViewImp guiView;
  private MidiViewImpl midiView;

  /**
   * Constructor for composite view.
   * @param model read only model
   */
  public CompositeView(MusicEditorModelOperations model) {
    guiView = new GuiViewImp(model);
    try {
      midiView = new MidiViewImpl(model);
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateMusic() {
    guiView.updateMusic();
    midiView.updateMusic();
  }

  @Override
  public void setKeyListener(KeyboardListener k) {
    guiView.setKeyListener(k);
  }

  @Override
  public void setMouseListener(MouseHandler m) {
    guiView.setMouseListener(m);
  }

  @Override
  public void renderMusicEditor() throws IllegalArgumentException {
    guiView.renderMusicEditor();
    midiView.renderMusicEditor();
  }
}
