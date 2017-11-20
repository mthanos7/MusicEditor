package cs3500.music.view;

import static org.junit.Assert.assertEquals;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelOperations;
import cs3500.music.model.Note;

/**
 * To test the methods of the class MidiViewImplTest.
 */
public class MidiViewImplTest {

  private MusicEditorModelOperations model = new MusicEditorModel();
  private MidiViewImpl midi;

  /**
   * Tests invalid data exception with a single note.
   *
   * @throws MidiUnavailableException
   * @throws InvalidMidiDataException
   */
  @Test
  public void testMidiStringOutputOneNote()
      throws MidiUnavailableException, InvalidMidiDataException {

    model.addNote(new Note(0, 3, 60, 1, 64));
    midi = new MidiViewImpl(model);

   // midi.mockRenderMusicEditor(model.getNotes());

    assertEquals("Pitch as an Integer: 60\n"
               + "Starting Position in microseconds: 1000000\n"
               + "Ending Position in microseconds: 4000000\n"
               + "Instrument Number: 1\n"
               + "Volume: 64\n\n", midi.getOutputAsString());
  }

  /**
   * Tests unavailable exception when given no notes output as a string.
   *
   * @throws MidiUnavailableException
   * @throws InvalidMidiDataException
   */
  @Test
  public void testMidiStringOutNoNotes()
      throws MidiUnavailableException, InvalidMidiDataException {
    midi = new MidiViewImpl(model);
    //midi.mockRenderMusicEditor(model.getNotes());
    assertEquals("", midi.getOutputAsString());
  }
}