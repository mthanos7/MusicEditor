package cs3500.music;

import cs3500.music.controller.ComplexController;
import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelOperations;
import cs3500.music.util.CompositionReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ComplexViewOperations;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/*
 * This is the class that will take in the main method. The main method will take in two
 * arguments that represent the file to read from and the desired view output respectively.
 */
public class MusicEditor {
  
  /**
   * The main methods will take in an array of Strings. The array must be two Strings or else an
   * exception will be thrown.
   * 
   * <p>The first String will represent the file name.
   * 
   * <p>The second String will represent the view name.
   * 
   * @param args the list of Strings the user will input
   * 
   * @throws IllegalArgumentException if args is null
   * @throws IllegalArgumentException if args.length is not 2
   * @throws IllegalArgumentException if args[0] is null
   * @throws IllegalArgumentException if args[1] is null
   * @throws IOException if the file name cannot be found
   * @throws InvalidMidiDataException if any given methods that involves midi throws an error
   * @throws MidiUnavailableException if given midi is unavailable
   */
  public static void main(String[] args) {
//    if (args == null) { TODO jar cant find files
//      throw new IllegalArgumentException("Invalid arguments for main method.");
//    }
//    if (args.length < 2) {
//      throw new IllegalArgumentException("Invalid length of arguments for main method.");
//    }
//    if (args[0] == null) {
//      throw new IllegalArgumentException("Invalid first argument for main method.");
//    }
//    if (args[1] == null) {
//      throw new IllegalArgumentException("Invalid second argument for main method.");
//    }
    
    MusicEditorModelOperations model = new MusicEditorModel();

    try {
      //CompositionReader comp = new CompositionReader(new FileReader(args[0]), model); TODO swap later
      CompositionReader comp = new CompositionReader(new FileReader("mystery-2.txt"), model);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot find file.");
    }
    ComplexViewOperations view = new CompositeView(model);

//    ViewFactory vf = new ViewFactory(model);
//    MusicEditorViewOperations view = vf.getView("midi");

//    ComplexViewOperations view = null;
//    try {
//      view = new MidiViewImpl(model);
//    } catch (MidiUnavailableException e) {
//      e.printStackTrace();
//    } catch (InvalidMidiDataException e) {
//      e.printStackTrace();
//    }


    MusicEditorController c = new ComplexController(view, model);

    // MusicEditorViewOperations view = ViewFactory.getView(args[1], model.getTempo());
//Timer t = new Timer();
//    t.scheduleAtFixedRate(new TimerTask() {
//      @Override
//      public void run() {
//        model.addNote(new Note((int)(Math.random() * 65), (int)(Math.random() * 65) , 1, 64, 85));
//      }
//    }, 500, 1000);
  }
}

