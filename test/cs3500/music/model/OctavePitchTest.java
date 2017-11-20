package cs3500.music.model;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * To test the methods of the class OctavePitch.
 */
public class OctavePitchTest {
  OctavePitch o1;
  OctavePitch o2;
  OctavePitch o3;
  OctavePitch o4;

  //Initializes data for the OctavePitchTest class
  private void initData() {
    o1 = new OctavePitch(Octave.ONE, Pitch.A);
    o2 = new OctavePitch(Octave.ONE, Pitch.A);
    o3 = new OctavePitch(Octave.TWO, Pitch.A);
    o4 = new OctavePitch(Octave.ONE, Pitch.B);
  }

  //To test the constructor exception for a note when given a null pitch
  @Test(expected = IllegalArgumentException.class)
  public void testNewOPNullPitch() {
    OctavePitch n = new OctavePitch(Octave.ONE, null);
  }

  //To test the constructor exception for a note when given a null octave
  @Test(expected = IllegalArgumentException.class)
  public void testNewOPNullOctave() {
    OctavePitch n = new OctavePitch(null, Pitch.A);
  }

  //To test the method equals for two octave pitches
  @Test
  public void testEquals() {
    initData();
    assertTrue(o1.equals(o1));
    assertTrue(o1.equals(o2));
    assertFalse(o1.equals(o3));
    assertFalse(o1.equals(o4));
    assertFalse(o4.equals(o1));
  }

  //To test the method compareTo for two octave pitches
  @Test
  public void testCompareTo() {
    initData();
    assertEquals(o1.compareTo(o1), 0);
    assertEquals(o1.compareTo(o2), 0);
    assertEquals(o1.compareTo(o3),  -1);
    assertEquals(o1.compareTo(o4), -1);
    assertEquals(o4.compareTo(o1), 1);
  }

  //To test the method toString for the class OctavePitch
  @Test
  public void testToString() {
    initData();
    assertEquals(o1.toString(), " A1  ");
    assertEquals(o2.toString(), " A1  ");
    assertEquals(o3.toString(), " A2  ");
    assertEquals(o4.toString(), " B1  ");
    assertEquals(new OctavePitch(Octave.ONE, Pitch.ASHARP).toString(), " A#1 ");
  }

  //To test the method getFrequency for the class OctavePitch
  @Test
  public void testGetFrequency() {
    initData();
    assertTrue(Math.abs(o1.getFrequency() - 55.0) < 0.001);
    assertTrue(Math.abs(new OctavePitch(Octave.ZERO, Pitch.ASHARP).getFrequency()) - 29.14 < 0.001);
    assertTrue(o3.getFrequency() - 110.0 < 0.001);
    assertTrue(o4.getFrequency() - 61.74 < 0.001);
  }
}