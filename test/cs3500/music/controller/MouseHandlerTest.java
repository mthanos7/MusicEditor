package cs3500.music.controller;

import org.junit.Test;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.io.IOException;

import cs3500.music.view.MouseHandler;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods from the mouse handler.
 */
public class MouseHandlerTest {

  MouseHandler mh;
  Appendable ap;

  /**
   * Initializes data for the MouseHandlerTest class.
   */
  public void initData() {

    ap = new StringBuffer();
    mh = new MouseHandler(new AddNote() {
      @Override
      public void addNote(int pitch) {
        try {
          ap.append("note added!");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Test the left key pressed in bound click.
   */
  @Test
  public void testClickInBounds() {
    initData();
    mh.mouseClicked(new MouseEvent(new Canvas(), 0, 0, 0,
            200, 230, 1, false));
    assertEquals(ap.toString(), "note added!");
  }


  /**
   * Test the left key pressed out of bounds click.
   */
  @Test
  public void testClickOutOfBounds() {
    initData();
    mh.mouseClicked(new MouseEvent(new Canvas(), 0, 0, 0,
            100, 400, 1, false));
    assertEquals(ap.toString(), "");
  }

}