package cs3500.music.view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import cs3500.music.model.MusicElement;
import cs3500.music.model.Note;
import cs3500.music.model.OctavePitch;

/**
 * A frame for the GUI view. Has two panels. One represents the keyboard and
 * the other represents the piece of music. Listens for key events to move the red bar
 * on the notes frame. This is done using the nested class CurTime, which ensures that the time
 * is kept constant between the piano and note panels.
 */
public class GuiViewFrame extends javax.swing.JFrame {
  PianoPanel pianoPanel;
  NotesPanel notesPanel;

  /**
   * Creates new GuiView.
   * @param ops list of octave pitches in a piece of music
   * @param maxBeat the last beat in a piece of music
   * @param elements most important note value at each beat and pitch
*                 (in order new note, continued note, rest)
   * @param notes list of all notes in piece of music
   * @param curTime current time of the piece
   */
  public GuiViewFrame(ArrayList<OctavePitch> ops, int maxBeat, MusicElement[][] elements,
                      ArrayList<ArrayList<Note>> notes, int curTime) {
    if (ops == null || maxBeat < 0 || elements == null || notes == null) {
      throw new IllegalArgumentException("Invalid arguments.");
    }


    // the frame
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    // the container
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    this.add(container);

    // notes panel
    notesPanel = new NotesPanel(ops, maxBeat, elements, curTime);
    JScrollPane scroll = new JScrollPane(notesPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroll.getHorizontalScrollBar().setUnitIncrement(20);
    scroll.getVerticalScrollBar().setUnitIncrement(20);
    container.add(scroll);
    scroll.setPreferredSize(new Dimension(850, 200));

    // piano panel
    pianoPanel = new PianoPanel(notes, curTime);
    container.add(pianoPanel);


    this.getContentPane().add(container);

    // sizes the frame
    this.pack();


  }

  /**
   * Initializes the frame by making it visible.
   */
  public void initialize() {
    this.setVisible(true);
  }

  /**
   * Updates view based on updated model.
   * @param maxBeat new max beat
   * @param elements new music elements
   * @param notes new notes
   * @param time new current time
   */
  public void updatedModel(int maxBeat, MusicElement[][] elements,
                           ArrayList<ArrayList<Note>> notes, int time) {
    if (maxBeat < 0 || elements == null || notes == null) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    notesPanel.updatedModel(maxBeat, elements, time);
    pianoPanel.updatedModel(time);
  }

}
