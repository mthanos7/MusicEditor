package cs3500.music.view;

import org.junit.Test;
import java.util.ArrayList;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelOperations;
import cs3500.music.model.MusicElement;
import cs3500.music.model.Note;
import cs3500.music.model.OctavePitch;

/**
 * To test the methods of the class GuiViewImp.
 */
public class GuiViewImpTest {
  GuiViewImp view;
  MusicEditorModelOperations m;
  KeyboardListener k;

  /**
   * Initializes data for the GuiViewImpTest class.
   */
  public void initData() {
    m = new MusicEditorModel();
    view = new GuiViewImp(m);

  }


  /**
   * Tests the constructor for a GuiViewFrame when passed a null list of OctavePitches.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFrameNullOPs() {
    initData();
    GuiViewFrame f = new GuiViewFrame(null, 0, new MusicElement[0][0],
            new ArrayList<ArrayList<Note>>(), m.getTime());
  }

  /**
   * Tests the constructor for a GuiViewFrame when passed a negative max beat.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFrameNegBeat() {
    initData();
    GuiViewFrame f = new GuiViewFrame(new ArrayList<OctavePitch>(), -1,
            new MusicElement[0][0], new ArrayList<ArrayList<Note>>(), m.getTime());
  }

  /**
   * Tests the constructor for a GuiViewFrame when passed a null 2D array of MusicElements.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFrameNullElements() {
    initData();
    GuiViewFrame f = new GuiViewFrame(new ArrayList<OctavePitch>(), 0,
            null, new ArrayList<ArrayList<Note>>(), m.getTime());
  }

  /**
   * Tests the constructor for a GuiViewFrame when passed a null collection of notes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFrameNullNotes() {
    initData();
    GuiViewFrame f = new GuiViewFrame(new ArrayList<OctavePitch>(), 0,
            new MusicElement[0][0], null, 0);
  }

  /**
   * Tests the constructor for a NotesPanel when passed a negative CurTime.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNotesPanelNegCurTime() {
    initData();
    NotesPanel f = new NotesPanel(new ArrayList<OctavePitch>(), 0,
            new MusicElement[0][0], -1);
  }

  /**
   * Tests the constructor for a PianoPanel when passed a negative CurTime.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPianoPanelNegCurTime() {
    initData();
    PianoPanel f = new PianoPanel( new ArrayList<ArrayList<Note>>(), -1);
  }

}





