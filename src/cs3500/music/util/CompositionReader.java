package cs3500.music.util;


import cs3500.music.model.MusicEditorModelOperations;
import cs3500.music.model.Note;

/**
 * This class will read and parse the given input file.
 * While parsing the input file, notes will
 * be added to the model, and its tempo will also be set.
 */
public class CompositionReader implements CompositionBuilder<MusicEditorModelOperations> {

  Readable file;
  MusicEditorModelOperations model;

  /**
   * Constructor that adds notes to the model based on the given input file.
   *
   * @param file the file to be parsed
   * @param model the music editor model
   */
  public CompositionReader(Readable file, MusicEditorModelOperations model) {
    if (file == null) {
      throw new IllegalArgumentException("Invalid file parameter.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Invalid model parameter.");
    }

    this.file = file;
    this.model = model;
    this.model = MusicReader.parseFile(file, this);
  }


  @Override
  public MusicEditorModelOperations build() {
    return model;
  }

  @Override
  public CompositionBuilder<MusicEditorModelOperations> setTempo(int tempo) {
    this.model.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<MusicEditorModelOperations> addNote(int start, int end, int instrument,
                                                                int pitch, int volume) {
    this.model.addNote(new Note(start, end, pitch, instrument, volume));
    return this;
  }

}