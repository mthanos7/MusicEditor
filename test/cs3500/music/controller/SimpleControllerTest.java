package cs3500.music.controller;

import org.junit.Test;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.view.StringViewImp;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods for the simple controller.
 */
public class SimpleControllerTest {
  SimpleController s;
  StringViewImp view;
  MusicEditorModel model;
  Appendable ap;

  /**
   * Initializes data for the SimpleControllerTest class.
   */
  public void initData() {
    ap = new StringBuffer();
    model = new MusicEditorModel();
    view = new StringViewImp(ap, model);
    s = new SimpleController(view, model);
  }

  /**
   * Test the constructor when given a null view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    SimpleController s2 = new SimpleController(null, model);
  }


  /**
   * Test the constructor when given a null model.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    SimpleController s2 = new SimpleController(view, null);
  }

  /**
   * Test the render music method with a model without notes.
   */
  @Test
  public void testRenderMusicNoNotes() {
    initData();
    view.renderMusicEditor();
    assertEquals(ap.toString(), "");
  }

  /**
   * Test the render music method with a model with notes.
   */
  @Test
  public void testRenderMusicNotes() {
    initData();
    model.addNote(new Note(0, 2, 1, 1, 1));
    view.renderMusicEditor();
    assertEquals(ap.toString(), " C#10 \n0  X  \n1  |  \n");
  }
}
