package cs3500.music.view;

import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelOperations;
import cs3500.music.model.ReadOnlyModel;


import static org.junit.Assert.assertEquals;

/**
 * Test the methods for the composite view.
 */
public class CompositeViewTest {

  MusicEditorModelOperations mod = new MusicEditorModel();
  CompositeView comp = new CompositeView(mod);
  GuiViewImp view;
  ReadOnlyModel model = mod;

  /**
   * Test constructor exceptions.
   * @throws MidiUnavailableException
   * @throws InvalidMidiDataException
   */
  @Test
  public void testException()
          throws MidiUnavailableException, InvalidMidiDataException {
    MidiViewImpl midi = new MidiViewImpl(mod);
    assertEquals("", midi.getOutputAsString());
  }
}
