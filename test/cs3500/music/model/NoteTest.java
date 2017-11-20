package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * To test the methods of the class Note.
 */
public class NoteTest {
  Note aSharp6;
  Note aSharp62;
  Note aSharp5;
  Note cSharp6;
  Note aSharpBig;

  //Initializes data for the NoteTest class
  private void initData() {
    aSharp6 = new Note(0, 1, new OctavePitch(Octave.SIX, Pitch.ASHARP).toInt(), 1, 1);
    aSharp62 = new Note(0, 1, new OctavePitch(Octave.SIX, Pitch.ASHARP).toInt(), 1, 1);
    aSharp5 = new Note(0, 1, new OctavePitch(Octave.FIVE, Pitch.ASHARP).toInt(), 1, 1);
    cSharp6 = new Note(0, 1, new OctavePitch(Octave.SIX, Pitch.CSHARP).toInt(), 1, 1);
    aSharpBig = new Note(10, 100, new OctavePitch(Octave.SIX, Pitch.ASHARP).toInt(), 1, 1);
  }

  //To test the constructor exception for a note when given a  negative start beat
  @Test(expected = IllegalArgumentException.class)
  public void testNewNoteNegStart() {
    Note n = new Note(-1, 0, new OctavePitch(Octave.EIGHT, Pitch.A).toInt(), 1, 1);
  }

  //To test the constructor exception for a note when given an end beat less than start beat
  @Test(expected = IllegalArgumentException.class)
  public void testNewNoteLessEnd() {
    Note n = new Note(2, 1, new OctavePitch(Octave.EIGHT, Pitch.A).toInt(), 1, 1);
  }

  //To test the constructor exception for a note when given an end beat less than start beat
  @Test(expected = IllegalArgumentException.class)
  public void testNewNoteNullOP() {
    Note n = new Note(2, 3, -1, 0, 0);
  }

  //To test the method equals for two notes
  @Test
  public void testEquals() {
    initData();
    assertTrue(aSharp6.equals(aSharp6));
    assertTrue(aSharp6.equals(aSharp62));
    assertFalse(aSharp6.equals(aSharp5));
    assertFalse(aSharp6.equals(cSharp6));
    assertFalse(aSharp6.equals(aSharpBig));
  }

  //To test the exception for changeStart when given a negative note
  @Test(expected = IllegalArgumentException.class)
  public void testChangeStartNeg() {
    initData();
    aSharp5.changeStart(-1);
  }

  //To test the method changeStart for the note class
  @Test
  public void testChangeStart() {
    initData();
    assertEquals(aSharp6.changeStart(0), aSharp6);
    assertEquals(aSharp6.changeStart(1),
            new Note(1, 2, new OctavePitch(Octave.SIX, Pitch.ASHARP).toInt(), 1, 1));
  }


}