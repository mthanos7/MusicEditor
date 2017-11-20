package cs3500.music.view;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Octave;
import cs3500.music.model.OctavePitch;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;


/**
 * To test the methods of the class StringViewImp.
 */
public class StringViewImpTest {
  MusicEditorModel m;
  Note n;
  OctavePitch op;
  Note n2;
  ArrayList<Note> arr;
  StringViewImp view;
  StringBuffer ap;

  /**
   * Initializes data for the StringViewImpTest class.
   */
  void initData() {
    m = new MusicEditorModel();
    op = new OctavePitch(Octave.ONE, Pitch.ASHARP);
    n = new Note(0, 1, op.toInt(), 1, 1);
    n2 = new Note(5, 5, new OctavePitch(Octave.TWO,
            Pitch.B).toInt(), 1, 1);
    arr = new ArrayList<Note>();
    arr.add(n);
    arr.add(n2);
    ap = new StringBuffer();
    view = new StringViewImp(ap, m);
  }

  /**
   * Tests the constructor when given a null appendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testConstructorNullAppendable() {
    initData();
    StringViewImp view = new StringViewImp(null, m);
  }

  /**
   * Tests the constructor when given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRenderNullNotes() {
    initData();
    StringViewImp view = new StringViewImp(ap,null);
  }


  /**
   * Tests the renderMusicEditor method when passed data from a model with no music.
   */
  @Test
  public void testRenderMusicEditorEmpty() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
  }


  /**
   *Tests the renderMusicEditor method when passed data from a model with a note with duration of 1.
   */
  @Test
  public void testRenderMusicEditorOneNoteDurOne() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(n);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1 \n0  X  \n");
  }

  /**
   * Tests the renderMusicEditor method when passed data from a model with a long note.
   */
  @Test
  public void testRenderMusicEditorLongNote() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(new Note(0, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0  X  \n1  |  \n2  |  \n3  |  \n"
            + "4  |  \n");
  }


  /**
   * Tests the renderMusicEditor method when passed data from a model with two notes.
   */
  @Test
  public void testRenderMusicEditorTwoNotes() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(new Note(0, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    m.addNote(new Note(6, 10, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0  X  \n1  |  \n2  |  \n3  |  \n"
            + "4  |  \n5     \n6  X  \n7  |  \n8  |  \n9  |  \n");
  }

  /**
   * Tests the renderMusicEditor method when passed data from a model with
   * two notes that don't overlap.
   */
  @Test
  public void testRenderMusicEditorTwoNotesNoOverlap() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(new Note(0, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    m.addNote(new Note(7, 10, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0  X  \n1  |  \n2  |  \n3  |  \n"
            + "4  |  \n5     \n6     \n7  X  \n8  |  \n9  |  \n");
  }

  /**
   * Tests the renderMusicEditor method when passed data from a model with
   * two notes that do overlap.
   */
  @Test
  public void testRenderMusicEditorTwoNotesOverlap() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(new Note(0, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    m.addNote(new Note(4, 10, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0  X  \n1  |  \n2  |  \n3  |  \n"
            + "4  X  \n5  |  \n6  |  \n7  |  \n8  |  \n9  |  \n");
  }

  /**
   * Tests the renderMusicEditor method when passed data from a model with
   * two notes with different pitches.
   */
  @Test
  public void testRenderMusicEditorTwoNotesDifPit() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(new Note(0, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    m.addNote(new Note(1, 2, new OctavePitch(Octave.TWO, Pitch.C).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  C2   C#2  D2   D#2  E2   F2   F#2  G2   "
            + "G#2  A2   A#2  B2  \n"
            + "0                                                         X  \n"
            + "1  X                                                      |  \n"
            + "2                                                         |  \n"
            + "3                                                         |  \n"
            + "4                                                         |  \n");
  }

  /**
   * Tests the renderMusicEditor method when passed data from a model with
   * two notes with different octaves.
   */
  @Test
  public void testRenderMusicEditorTwoNotesDifOct() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(new Note(0, 5, new OctavePitch(Octave.TWO, Pitch.C).toInt(), 1, 1));
    m.addNote(new Note(1, 2, new OctavePitch(Octave.THREE, Pitch.C).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  C2   C#2  D2   D#2  E2   F2   F#2  G2   G#2 "
            + " A2   A#2  B2   C3  \n"
            + "0  X                                                              \n"
            + "1  |                                                           X  \n"
            + "2  |                                                              \n"
            + "3  |                                                              \n"
            + "4  |                                                              \n");
  }


  /**
   * Tests the renderMusicEditor method when passed data from a model with
   * a note who starts after zero.
   */
  @Test
  public void testRenderMusicEditorStartNotZero() {
    initData();
    m.addNote(new Note(5, 6, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0     \n1     \n2     \n3     \n4     \n5  X  \n");
  }

  /**
   * Tests the renderMusicEditor method when passed data from a model with
   * the same note twice.
   */
  @Test
  public void testRenderMusicEditorSameNoteTwice() {
    initData();
    m.addNote(n);
    m.addNote(n);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1 \n0  X  \n");
  }

}