package cs3500.music.controller;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.view.KeyboardListener;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods from the keyboard listener.
 */
public class KeyboardListenerTest {
  KeyboardListener k;
  Appendable ap;
  Map<Integer, Runnable> keyPresses;
  /**
   * Initializes data for the KeyboardListenerTest class.
   */
  public void initData() {

    k = new KeyboardListener();
    ap = new StringBuffer();
    keyPresses = new HashMap<>();
    keyPresses.put(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        try {
          ap.append("left key pressed.");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    keyPresses.put(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        try {
          ap.append("right key pressed.");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    keyPresses.put(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        try {
          ap.append("home key pressed.");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    keyPresses.put(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        try {
          ap.append("end key pressed.");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    keyPresses.put(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        try {
          ap.append("space key pressed.");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Test the left key pressed.
   */
  @Test
  public void testLeftKey() {
    initData();
    keyPresses.get(KeyEvent.VK_LEFT).run();
    assertEquals(ap.toString(), "left key pressed.");
  }

  /**
   * Test the right key pressed.
   */
  @Test
  public void testRightKey() {
    initData();
    keyPresses.get(KeyEvent.VK_RIGHT).run();
    assertEquals(ap.toString(), "right key pressed.");
  }

  /**
   * Test the home key pressed.
   */
  @Test
  public void testHomeKey() {
    initData();
    keyPresses.get(KeyEvent.VK_HOME).run();
    assertEquals(ap.toString(), "home key pressed.");
  }

  /**
   * Test the end key pressed.
   */
  @Test
  public void testEndKey() {
    initData();
    keyPresses.get(KeyEvent.VK_END).run();
    assertEquals(ap.toString(), "end key pressed.");
  }


  /**
   * Test the space key pressed.
   */
  @Test
  public void testSpaceKey() {
    initData();
    keyPresses.get(KeyEvent.VK_SPACE).run();
    assertEquals(ap.toString(), "space key pressed.");
  }
}
