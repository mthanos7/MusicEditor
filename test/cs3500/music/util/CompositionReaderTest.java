package cs3500.music.util;

import java.io.StringReader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelOperations;

/**
 * Tests the class CompositionReader.
 */
public class CompositionReaderTest {
  
  Readable rd = new StringReader("");
  MusicEditorModelOperations model = new MusicEditorModel();

  /**
   * Tests an IllegalArgumentException when the CompositionReader is given a null Readable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompReaderConstructorFailsNullFile() {
    new CompositionReader(null, model);
  }

  /**
   * Tests an IllegalArgumentException when the CompositionReader is given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompReaderConstructorFailsNullModel() {
    new CompositionReader(rd, null);
  }
  
}
