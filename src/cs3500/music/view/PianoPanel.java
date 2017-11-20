package cs3500.music.view;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

import cs3500.music.model.Note;


/**
 * This class creates a visual view of a piano.
 * It uses the range of pitches in a piece, the last beat,
 * and the most important note at each beat (in order new note, continued note, rest).
 * It displays the notes played at each pitch on the piano based on where you are in the song.
 */
public class PianoPanel extends JPanel {
  private ArrayList<ArrayList<Note>>  notes;
  private int curTime;
  private ArrayList<Integer> highlightKeys;


  /**
   * Constructor for a PianoPanel.
   * @param notes all the notes in the piece of music
   * @param curTime current time of piece
   */
  public PianoPanel(ArrayList<ArrayList<Note>> notes, int curTime) {
    if (notes == null || curTime < 0) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    this.notes = notes;
    this.curTime = curTime;
    highlightKeys = getHighlightedKeys();
  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(850, 200);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawKeys(g, highlightKeys);
  }

  /**
   * Finds all the keys that should be highlighted at the current time.
   * @return all keys to highlight at this time
   */
  private ArrayList<Integer> getHighlightedKeys() {
    ArrayList<Integer> highlightKeys = new ArrayList<Integer>();
    int i = 0;
    for (ArrayList<Note> arr : notes) {
      for (Note n : arr) {
        if (n.getStartBeat() <= curTime && n.getEndBeat() > curTime) {
          highlightKeys.add(n.noteAsInt());
        }
        i++;
      }
    }
    return highlightKeys;
  }

  /**
   * Draw all keys of the piano.
   * @param g for rendering images
   * @param highlightKeys keys to highlight
   */
  private void drawKeys(Graphics g, ArrayList<Integer> highlightKeys) {
    int currKey = 0;
    int currOctaveKey = 0;
    int lastKeyNumber = 10 * 12;
    int keyImgPosX = 40;

    while (currKey < lastKeyNumber) {
      drawWhiteKey(g, keyImgPosX, highlightKeys.contains(currKey));
      if (currOctaveKey == 11) {
        currOctaveKey = 0;
        currKey += 1;
      } else if (currOctaveKey == 4) {
        currOctaveKey += 1;
        currKey += 1;
      } else {
        currOctaveKey += 2;
        currKey += 2;
      }
      keyImgPosX += 10;
    }

    currKey = 1;
    currOctaveKey = 1;
    keyImgPosX = 47;

    while (currKey <= lastKeyNumber) {
      drawBlackKey(g, keyImgPosX, highlightKeys.contains(currKey));
      if (currOctaveKey == 10) {
        currOctaveKey = 1;
        currKey += 3;
        keyImgPosX += 20;
      } else if (currOctaveKey == 3) {
        currOctaveKey += 3;
        currKey += 3;
        keyImgPosX += 20;
      } else {
        currOctaveKey += 2;
        currKey += 2;
        keyImgPosX += 10;
      }
    }
    g.drawRect(40, 0, 702, 99);
  }

  /**
   * Draw a black key of the piano.
   * @param g for rendering images
   * @param keyImgPosX current key's x-coord
   * @param isPressed if the current key is pressed
   */
  private void drawBlackKey(Graphics g, int keyImgPosX, boolean isPressed) {
    g.setColor(Color.BLACK);
    g.fillRect(keyImgPosX, 0, 6, 50);

    if (isPressed) {
      g.setColor(Color.PINK);
    } else {
      g.setColor(Color.BLACK);
    }

    g.fillRect(keyImgPosX + 1, 0, 4, 48);
  }

  /**
   * Draw a white key of the piano.
   * @param g for rendering images
   * @param keyImgPosX current key's x-coord
   * @param isPressed if the current key is pressed
   */
  private void drawWhiteKey(Graphics g, int keyImgPosX, boolean isPressed) {
    g.setColor(Color.BLACK);
    g.fillRect(keyImgPosX, 0, 12, 100);

    if (isPressed) {
      g.setColor(Color.RED);
    } else {
      g.setColor(Color.WHITE);
    }

    g.fillRect(keyImgPosX + 1, 0, 12, 98);
  }

  /**
   * Updates panel based on updated model.
   * @param time current time
   */
  public void updatedModel(int time) {
    this.curTime = time;
    this.highlightKeys = getHighlightedKeys();
    repaint();
  }
}
