package cs3500.music.view;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Font;


import cs3500.music.model.MusicElement;
import cs3500.music.model.OctavePitch;

/**
 * This class represents the notes in a piece of music as a visual view.
 * It uses the range of pitches in a piece, the last beat,
 * and the most important note at each beat (in order new note, continued note, rest).
 * It displays the notes at each pitch by measure.
 * This panel also has a sliding red bar that allows you to keep track of the
 * music and see what notes play at each beat on the piano.
 */
public class NotesPanel extends JPanel {

  private ArrayList<OctavePitch> ops;
  private int maxBeat;
  private MusicElement[][] elements;
  private int curTime;

  /**
   * Constructor for a NotesPanel.
   * @param ops list of all octave pitches in a song
   * @param maxBeat last beat of the song
   * @param elements all the most important notes at each beat
   * @param curTime current time of piece
   */
  public NotesPanel(ArrayList<OctavePitch> ops, int maxBeat, MusicElement[][] elements, int curTime) {
    if (ops == null || maxBeat < 0 || elements == null || curTime < 0) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    this.ops = ops;
    this.maxBeat = maxBeat;
    this.elements = elements;
    this.curTime = curTime;
    setPreferredSize(getPreferredSize());
  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(maxBeat * 13, ops.size() * 9);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawPitches(g);
    drawMeasures(g);
    drawNotes(g);
    drawRedLine(g);
  }

  /**
   * Draws the red line to show where you are in the piece.
   * @param g for rendering images
   */
  private void drawRedLine(Graphics g) {
    g.setColor(Color.RED);
    g.drawLine(40, 10,
            40, ops.size() * 9);
  }

  /**
   * Draws the the notes in the piece.
   * @param g for rendering images
   */
  private void drawNotes(Graphics g) {
    g.setColor(Color.GREEN);
    for (int i = 0; i < elements[0].length; i++) {
      for (int j = elements.length - 1; j >= 0; j--) {
          if (elements[j][i] == MusicElement.NEW_NOTE) {
          g.setColor(Color.BLACK);
          g.fillRect(40 + i * 13 + 1 - curTime * 13, ops.size() * 9 - (10 * (j + 2)) + 21,
                  13, 9);
        }
        else if (elements[j][i] == MusicElement.CONTINUED_NOTE) {
          g.setColor(Color.GREEN);
          g.fillRect(40 + i * 13 + 1 - curTime * 13, ops.size() * 9 - (10 * (j + 2)) + 21,
                  13, 9);
        }
      }
    }
  }

  /**
   * Draws the the measures in the piece.
   * @param g for rendering images
   */
  private void drawMeasures(Graphics g) {
    for (int i = 0; i < ops.size(); i++) {
      g.setColor(Color.WHITE);
      g.fillRect(40,ops.size() * 9 - 20 - (i * 10), (maxBeat + 1) * 13, 10);
      g.setColor(Color.BLACK);
      g.drawRect(40,ops.size() * 9 - 20 - (i * 10), (maxBeat + 1) * 13, 10);
    }
    for (int i = 0; i < Math.floor(maxBeat / 4) + 1; i++) {
      if (i * 4 >= curTime) {
        g.drawString(Integer.toString((i * 4)), 37 + i * 52 - curTime * 13, 8);
      }
      g.drawLine(40 + i * 52, ops.size() * 9 - 10, 40 + i * 52,ops.size()
              * 9 - 20 - (10 * (ops.size() - 1)));
    }
  }

  /**
   * Draws the the pitches in the piece.
   * @param g for rendering images
   */
  private void drawPitches(Graphics g) {
    ArrayList<OctavePitch> temp = new ArrayList<OctavePitch>();
    for (int i = ops.size() - 1; i >= 0; i--) {
      temp.add(ops.get(i));
    }
    for (int i = 0; i < ops.size(); i++) {
      g.setFont(new Font("Time Roman", Font.PLAIN, 10));
      g.drawString(ops.get(i).toString(), 10, ops.size() * 9 - 10 - (i * 10));
    }
  }

  /**
   * Updates the view based on the updated model.
   * @param maxBeat last beat in piece
   * @param elements music elements in piece
   * @param time current time (in beats)
   */
  public void updatedModel(int maxBeat, MusicElement[][] elements, int time) {
    if (ops == null || maxBeat < 0 || elements == null) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    if (ops.size() > 8) {
      this.ops = ops;
    }
    this.maxBeat = maxBeat;
    this.elements = elements;
    this.curTime = time;
    repaint();
  }
}