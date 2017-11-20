package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cs3500.music.model.MusicEditorModelOperations;
import cs3500.music.model.Note;
import cs3500.music.view.ComplexViewOperations;
import cs3500.music.view.KeyboardListener;
import cs3500.music.view.MouseHandler;


/**
 * Controller that works for complex music editor views. Keeps a timer for time passing in a piece
 * and handles key events.
 */
public class ComplexController implements MusicEditorController {
  private ComplexViewOperations view;
  private MusicEditorModelOperations model;
  private boolean spaceToggled;
  private KeyboardListener kbd;

  /**
   * Constructor for complex controller.
   * @param view complex view
   * @param model music model
   */
  public ComplexController(ComplexViewOperations view, MusicEditorModelOperations model) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Invalid view or model for complex controller.");
    }
    this.view = view;
    renderMusic();
    this.kbd = new KeyboardListener();
    view.setKeyListener(kbd);
    configureKeyBoardListener();
    MouseHandler mh = new MouseHandler(new AddNote() {
      @Override
      public void addNote(int pitch) {
        model.addNote(new Note(model.getTime(), model.getTime() + 2, pitch, 1, 120));
        updateMusic();
      }
    });
    view.setMouseListener(mh);
    this.model = model;
    Timer t = new Timer();
    t.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!spaceToggled) {
          model.increaseTime();
          updateMusic();
        }

      }
    }, 4000, model.getTempo() / 1000);
  }


  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, only for those that
   * the program needs.
   * Last we create our KeyboardListener object, set all its maps and then give it to the view.
   */
  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    keyPresses.put(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        model.decreaseTime();
        updateMusic();
      }
    });
    keyPresses.put(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        model.increaseTime();
        updateMusic();
      }
    });
    keyPresses.put(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        model.resetTime();
        updateMusic();
      }
    });
    keyPresses.put(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        model.goToEndTime();
        updateMusic();
      }
    });
    keyPresses.put(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        spaceToggled = !spaceToggled;
        updateMusic();
      }
    });

    kbd.setKeyPressedMap(keyPresses);
  }



  @Override
  public void renderMusic() {
    view.renderMusicEditor();
  }

  /**
   * Update the music view based on the new state of the model.
   */
  private void updateMusic() {
    view.updateMusic();
  }
}
