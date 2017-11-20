package cs3500.music;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.ReadOnlyModel;
import cs3500.music.view.GuiViewImp;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicEditorViewOperations;
import cs3500.music.view.StringViewImp;

/**
 * Factory for different music editor views. Takes in a string and returns
 * an instance of the corresponding view.
 */
public class ViewFactory {
  private ReadOnlyModel model;

  /**
   * Constructor for view factory.
   * @param model read-only model for view
   */
  public ViewFactory(ReadOnlyModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Null model");
    }
    this.model = model;
  }

  /**
   * Generates a view of the music editor based on the given string.
   * @param view string for what view to produce
   * @return view based on given string
   */
  public MusicEditorViewOperations getView(String view) {
    MusicEditorViewOperations newView = null;


    
    switch (view.toLowerCase()) {
      case "console":
        newView = new StringViewImp(new StringBuffer(), model);
        break;
      case "visual":
        newView = new GuiViewImp(model);
        break;
      case "midi":
        try {
          newView = new MidiViewImpl(model);
        } catch (MidiUnavailableException e) {
          e.printStackTrace();
        } catch (InvalidMidiDataException e) {
          e.getMessage();
        }
        break;
      default:
        throw new IllegalArgumentException(
                "Enter console, visual or midi to generate desired view");
    }
    
    return newView;
  }
}
