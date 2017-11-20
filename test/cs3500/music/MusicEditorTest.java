package cs3500.music;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests the MusicEditor class.
 */
public class MusicEditorTest {
  
  String output = "";

  /**
   * Tests that the main method fails if its argument(s) is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMainMethodFailsNullArgs() {
    MusicEditor.main(null);
  }

  /**
   * Tests that the main method fails if it is passed an insufficient number of arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMainMethodFailsNotEnoughArguments() {
    String[] args = {"file"};
    MusicEditor.main(args);
  }

  /**
   * Tests that the main method fails if it is passed too many arguments.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMainMethodFailsTooMuchArguments() {
    String[] args = {"file", "view", "typo"};
    MusicEditor.main(args);
  }

  /**
   * Tests that the main method fails if the given file name is null.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMainMethodFailsNullFileName() {
    String[] args = {null, "console"};
    MusicEditor.main(args);
  }

  /**
   * Tests that the main method fails if the specified view name is null.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMainMethodFailsNullViewName() {
    String[] args = {"test\\cs3500\\music\\mary-little-lamb.txt", null};
    MusicEditor.main(args);
  }

  /**
   * Tests that the main method fails if the given file is not found.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMainMethodFailsFileNotFound() {
    String[] args = {"test\\cs3500\\music\\df-votd.txt", "midi"};
    MusicEditor.main(args);
  }

  /**
   * Tests that the main method fails if the file is incorrect.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMainMethodFailsIncorrectFile() {
    String[] args = {"hello.exe", "console"};
    MusicEditor.main(args);
  }

  /**
   * Determines if the given function can run without failing.
   * 
   * @param function the function used to test for errors
   * @return true if the function does not fail, false otherwise
   */
  private boolean doesNotFail(Runnable function) {
    try {
      function.run();
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}