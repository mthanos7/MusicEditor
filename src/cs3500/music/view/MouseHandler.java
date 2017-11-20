package cs3500.music.view;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import cs3500.music.controller.AddNote;

/**
 * Represents a mouse listener.
 */
public class MouseHandler implements MouseListener {

  private int x; // mouse x position
  private int y; // mouse y position
  private AddNote addNote;

  /**
   * Constructor for a mouse handler.
   * @param addNote command for adding a note
   */
  public MouseHandler(AddNote addNote) {
    this.x = x;
    this.y = y;
    this.addNote = addNote;
  }

  /**
   * If the mouseClickedMap contains the keyEvent, then run the function that corresponds to it.
   *
   * @param e the MouseEvent
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    x = e.getX();
    y = e.getY();
    if (x > 40 && x < 740 && y < 325 && y > 225) {
      int pitch = findKey(x, y);
      System.out.println(pitch);
      addNote.addNote(pitch);

    }
  }

  /**
   * Finds key corresponding to mouse click.
   * @param x pos of click
   * @param y pos of click
   * @return key corresponding to mouse click
   */
  private int findKey(int x, int y) {
    return (x - 40) / 12;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    //do nothing
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //do nothing
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //do nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //do nothing
  }

}