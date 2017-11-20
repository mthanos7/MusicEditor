package cs3500.music.model;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.view.StringViewImp;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * To test the methods of the class MusicEditorModel.
 */
public class MusicEditorModelTest {
  MusicEditorModel m;
  Note n;
  OctavePitch op;
  Note n2;
  ArrayList<Note> arr;
  StringViewImp view;
  StringBuffer ap;

  /**
   * Initializes data for the MusicEditorModelTest class.
   */
  void initData() {
    m = new MusicEditorModel();
    op = new OctavePitch(Octave.ONE, Pitch.ASHARP);
    n = new Note(0, 1, op.toInt(), 1, 1);
    n2 = new Note(5, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1);
    arr = new ArrayList<Note>();
    arr.add(n);
    arr.add(n2);
    ap = new StringBuffer();
    view = new StringViewImp(ap, m);
  }

  /**
   * Tests the modelAsString method on a model with no music.
   */
  @Test
  public void testModelAsStringEmpty() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
  }

  /**
   * Tests the addNote method when given a null note.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNullNote() {
    initData();
    m.addNote(null);
  }

  /**
   * Tests the addNote method when given a note with duration of 1.
   */
  @Test
  public void testAddSingleNote() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(n);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1 \n0  X  \n");
  }

  /**
   * Test adding a longer note.
   */
  @Test
  public void testAddNoteLongNote() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
    m.addNote(new Note(0, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0  X  \n1  |  \n2  |  \n3  |  \n"
            + "4  |  \n");
  }


  /**
   * Test adding two notes starting at different beats.
   */
  @Test
  public void testAddNoteLongNoteDifBeatsNoSpace() {
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
   * Test assing two notes that don't overlap.
   */
  @Test
  public void testAddNoteLongNoteDifBeatsWithSpace() {
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
   * Test adding two overlapping notes.
   */
  @Test
  public void testAddNoteOverlapNotes() {
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
   * Test adding two notes with different pitches.
   */
  @Test
  public void testAddNoteLongNoteDifPitch() {
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
   * Test adding two notes with different pitches. Loops around past C.
   */
  @Test
  public void testAddNoteNewOctave() {
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
   * Tests the addNote method when given a note starting at a value greater than 0.
   */
  @Test
  public void testAddNoteNotZeroStart() {
    initData();
    m.addNote(new Note(5, 6, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0     \n1     \n2     \n3     \n4     \n5  X  \n");
  }

  /**
   * Tests the addNote method when adding the same note twice.
   */
  @Test
  public void testAddNoteSameTwice() {
    initData();
    m.addNote(n);
    m.addNote(n);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1 \n0  X  \n");
  }

  /**
   * Tests the removeNote method when given a model with no notes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteNoNotes() {
    initData();
    m.removeNote(n);
  }

  /**
   * Tests the removeNote method when given a null note.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteNullNotes() {
    initData();
    m.addNote(n);
    m.removeNote(null);
  }

  /**
   * Tests the removeNote method when given a note not in the piece.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteNotIn() {
    initData();
    m.addNote(n);
    m.removeNote(new Note(5, 5, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
  }

  /**
   * Tests the removeNote method when given a note in the piece.
   */
  @Test
  public void testRemoveNote() {
    initData();
    m.addNote(n);
    m.removeNote(n);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
  }

  /**
   * Tests the removeNote method when given a note that is in the piece twice.
   */
  @Test
  public void testAddSameRemoveNote() {
    initData();
    m.addNote(n);
    m.addNote(n);
    m.removeNote(n);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1 \n0  X  \n");
  }

  /**
   * Tests the removeNote method when there are multiple notes in the piece.
   */
  @Test
  public void testRemoveNoteOthersSame() {
    initData();
    m.addNote(n);
    m.addNote(new Note(5, 6, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1));
    m.removeNote(n);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  B2  \n0     \n1     \n2     \n"
            + "3     \n4     \n5  X  \n");
  }


  /**
   * Tests the getNotes method when there are no notes in the piece.
   */
  @Test
  public void testGetNotesEmpty() {
    initData();
    assertEquals(m.getNotes(0, 0), new ArrayList<Note>());
  }

  /**
   * Tests the getNotes method when there is one note in the piece.
   */
  @Test
  public void testGetNotesOne() {
    initData();
    m.addNote(n);
    ArrayList<Note> arr = new ArrayList<Note>();
    arr.add(n);
    assertTrue(m.getNotes(0, m.getLastBeat()).get(0).contains(arr.get(0)));
  }

  /**
   * Tests the getNotes method when there are multiple notes in the piece.
   */
  @Test
  public void testGetNotesMultiple() {
    initData();
    m.addNote(n);
    m.addNote(n2);
    assertTrue(m.getNotes(0, m.getLastBeat()).get(0).contains(n));
    assertTrue(m.getNotes(0, m.getLastBeat()).get(5).contains(n2));
  }


  /**
   * Test combine when a null collection is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCombineNullNotes() {
    initData();
    m.combine(null, 0);
  }

  /**
   * Test combine when a negative overlay beat is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCombineNegativeOverlay() {
    initData();
    m.combine(arr, -1);
  }

  /**
   * Test combine with an empty collection.
   */
  @Test
  public void testCombineEmpty() {
    initData();
    m.combine(new ArrayList<Note>(), 0);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
  }

  /**
   * Test combine when given a nonempty collection.
   */
  @Test
  public void testCombineNonEmpty() {
    initData();
    m.combine(arr, 0);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1  B1   C2   C#2  D2   D#2  E2   F2   F#2  G2"
            + "   G#2  A2   A#2  B2  \n"
            + "0  X                                                                   \n"
            + "1                                                                      \n"
            + "2                                                                      \n"
            + "3                                                                      \n"
            + "4                                                                      \n");
  }

  /**
   * Test combine when given a nonempty collection and a positive overlay beat.
   */
  @Test
  public void testCombineNonEmptyOverlay() {
    initData();
    m.combine(arr, 1);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1  B1   C2   C#2  D2   D#2  E2   F2   F#2  G2"
            + "   G#2  A2   A#2  B2  \n"
            + "0                                                                      \n"
            + "1  X                                                                   \n"
            + "2                                                                      \n"
            + "3                                                                      \n"
            + "4                                                                      \n"
            + "5                                                                      \n");
  }

  /**
   * Test combine when given a nonempty collection and an overlay beat
   * that puts notes on top of notes in the current piece.
   */
  @Test
  public void testCombineNonEmptyAddOnTopPiece() {
    initData();
    Note n3 = new Note(0, 10, op.toInt(), 1, 1);
    Note n4 = new Note(0, 10, new OctavePitch(Octave.TWO, Pitch.B).toInt(), 1, 1);
    m.addNote(n3);
    m.addNote(n4);
    m.combine(arr, 0);
    view.renderMusicEditor();
    assertEquals(ap.toString(), "  A#1  B1   C2   C#2  D2   D#2  E2   F2   F#2  G2"
            + "   G#2  A2   A#2  B2  \n"
            + "0  X                                                                X  \n"
            + "1  |                                                                |  \n"
            + "2  |                                                                |  \n"
            + "3  |                                                                |  \n"
            + "4  |                                                                |  \n"
            + "5  |                                                                X  \n"
            + "6  |                                                                |  \n"
            + "7  |                                                                |  \n"
            + "8  |                                                                |  \n"
            + "9  |                                                                |  \n");
  }
}