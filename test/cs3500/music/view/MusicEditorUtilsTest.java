package cs3500.music.view;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.MusicElement;
import cs3500.music.model.Note;
import cs3500.music.model.Octave;
import cs3500.music.model.OctavePitch;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods of the class MusicEditorUtilsTest.
 */
public class MusicEditorUtilsTest {

  /**
   * Tests the method getElements when given a negative max beat.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetElementsNegBeat() {
    MusicEditorViewUtils.getElements(new ArrayList<OctavePitch>(),
            -1,  new ArrayList<ArrayList<Note>>());
  }

  /**
   * Tests the method getElements when given a null list of octave pitches.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetElementsNullOPs() {
    MusicEditorViewUtils.getElements(null,
            1,  new ArrayList<ArrayList<Note>>());
  }

  /**
   * Tests the method getElements when given a null collection of notes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetElementsNullNotes() {
    MusicEditorViewUtils.getElements(new ArrayList<OctavePitch>(),
            1,  new ArrayList<ArrayList<Note>>());
  }

  /**
   * Tests the method getElements when given an empty collection of notes.
   */
  @Test
  public void testGetElementsNoNotes() {
    ArrayList<OctavePitch> ops = new ArrayList<OctavePitch>();
    ops.add(new OctavePitch(Octave.ZERO, Pitch.B));
    assertEquals(MusicEditorViewUtils.getElements(ops, 1,  new ArrayList<ArrayList<Note>>()),
            new MusicElement[1][1]);
  }

  /**
   * Tests the method getElements when given a nonempty collection of notes.
   */
  @Test
  public void testGetElementsSomeNotes() {
    ArrayList<OctavePitch> ops = new ArrayList<OctavePitch>();
    ops.add(new OctavePitch(Octave.ZERO, Pitch.C));
    ArrayList<Note> notes = new ArrayList<Note>();
    ArrayList<ArrayList<Note>> arrs = new ArrayList<ArrayList<Note>>();
    MusicElement[][] els = new MusicElement[1][2];
    arrs.add(notes);
    els[0][0] = MusicElement.NEW_NOTE;

    notes.add(new Note(0, 1, new OctavePitch(Octave.ZERO, Pitch.C).toInt(),
            0, 0));
    assertEquals(MusicEditorViewUtils.getElements(ops, 1,  arrs), els);
  }


  /**
   * Tests the method getOPRange when given a null list of notes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetOPRangeNullNotes() {
    MusicEditorViewUtils.getOPRange(null);
  }

  /**
   * Tests the method getOPRange when given an empty list of notes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetOPRangeNoNotes() {
    ArrayList<OctavePitch> ops = new ArrayList<OctavePitch>();
    MusicEditorViewUtils.getOPRange(new ArrayList<ArrayList<Note>>());
  }

  /**
   * Tests the method getOPRange when given an empty nonempty list of notes.
   */
  @Test
  public void testGetOPRangeSomeNotes() {
    ArrayList<OctavePitch> ops = new ArrayList<OctavePitch>();
    ops.add(new OctavePitch(Octave.ZERO, Pitch.C));
    ops.add(new OctavePitch(Octave.ZERO, Pitch.CSHARP));
    ops.add(new OctavePitch(Octave.ZERO, Pitch.D));
    ArrayList<Note> notes = new ArrayList<Note>();
    ArrayList<ArrayList<Note>> arrs = new ArrayList<ArrayList<Note>>();
    arrs.add(notes);
    notes.add(new Note(0, 0, 12, 0, 0));
    notes.add(new Note(0, 0, 14, 0, 0));
    assertEquals(MusicEditorViewUtils.getOPRange(arrs), ops);
  }


}
