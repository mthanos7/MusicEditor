package cs3500.music.view;

import java.util.ArrayList;

import cs3500.music.model.MusicElement;
import cs3500.music.model.Note;
import cs3500.music.model.OctavePitch;
import cs3500.music.model.ReadOnlyModel;


/**
 * This is the class that contains all of the GUI view components. It is able to
 * interpret a collection of notes from the model and use that information
 * to create a frame and then initialize it.
 */
public class GuiViewImp implements ComplexViewOperations {
  private ReadOnlyModel model;
  private GuiViewFrame gvf;

  /**
   * Constructor for gui view.
   * @param model read only  model
   */
  public GuiViewImp(ReadOnlyModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid read only model.");
    }
    this.model = model;
  }

  @Override
  public void renderMusicEditor() {
    ArrayList<ArrayList<Note>> notes = model.getNotes(model.getTime(), model.getLastBeat());
    ArrayList<OctavePitch> ops = MusicEditorViewUtils.getOPRange(
            model.getNotes(0, model.getLastBeat()));
    int maxBeat = model.getLastBeat();
    MusicElement[][] elements = MusicEditorViewUtils.getElements(ops, maxBeat, notes);
    gvf = new GuiViewFrame(ops, maxBeat, elements, notes, model.getTime());
    gvf.initialize();
  }

  @Override
  public void setKeyListener(KeyboardListener k) {
    gvf.addKeyListener(k);
  }

  @Override
  public void setMouseListener(MouseHandler m) {
    gvf.addMouseListener(m);
  }

  @Override
  public void updateMusic() {
    int curTime = model.getTime();
    ArrayList<ArrayList<Note>> notes = model.getNotes(curTime, curTime + 65);
    ArrayList<OctavePitch> ops = model.getOPRange();
    int maxBeat = model.getLastBeat();

    MusicElement[][] elements = MusicEditorViewUtils.getElements(ops, maxBeat, notes);

    for (Note n : model.getContinuedNotes()) {
      int opIdx = ops.indexOf(n.getOctavePitch());
      for (int i = curTime; i < n.getEndBeat(); i++) {
        if (elements[opIdx][i] != MusicElement.NEW_NOTE) {
          elements[opIdx][i] = MusicElement.CONTINUED_NOTE;
        }
      }
    }

    gvf.updatedModel(maxBeat, elements, notes, model.getTime());
  }



}
