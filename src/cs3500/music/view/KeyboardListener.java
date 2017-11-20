package cs3500.music.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * This class represents a keyboard listener. It is configurable by the controller that
 * instantiates it. It keeps a map of key presses to runnable commands to execute when
 * keys are pressed.
 */
public class KeyboardListener implements KeyListener {
  private Map<Integer, Runnable> keyPressedMap;


  /**
   * Set the map for key pressed events. Key pressed events in Java Swing are integer codes
   */

  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    if (map == null) {
      throw new IllegalArgumentException("Invalid key map");
    }
    keyPressedMap = map;
  }


  @Override
  public void keyTyped(KeyEvent e) {
    //does nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //does nothing
  }
}
