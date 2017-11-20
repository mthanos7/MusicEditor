package cs3500.music.view;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyModel;

/**
 * This represents the midi view for the music model. It parses the notes into
 * MidiMessages to be sent by the receiver and then plays the song.
 */
public class MidiViewImpl implements ComplexViewOperations {
  private final Synthesizer synth;
  private Receiver receiver;
  private int tempo;
  private Appendable output = new StringBuffer();
  private ReadOnlyModel model;

  /**
   * Constructor for MidiViewImpl.
   *
   * @throws MidiUnavailableException if any given midi data is unavailable
   * @throws InvalidMidiDataException if any given midi data is invalid
   * @param model read-only model
   */
  public MidiViewImpl(ReadOnlyModel model)
          throws MidiUnavailableException, InvalidMidiDataException {

    this.synth = MidiSystem.getSynthesizer();
    this.receiver = synth.getReceiver();
    this.synth.open();
    this.tempo = model.getTempo();
    this.model = model;
    setChannelInstruments();
  }

  /**
   * Sets the channels' instruments to the instrument number on the MIDI's list
   * of instruments - 1.
   *
   * @throws InvalidMidiDataException if any given midi data is invalid
   */
  private void setChannelInstruments() throws InvalidMidiDataException {
    for (int i = 0; i < 10; i++) {
      MidiMessage setInst = new ShortMessage(ShortMessage.PROGRAM_CHANGE, i, i, 64);
      this.receiver.send(setInst, 0);
    }
  }


  /**
   * Returns the string representation of the midi that was played.
   * For testing purposes only.
   *
   * @return the string representation of the played midi
   */
  public String getOutputAsString() {
    return output.toString();
  }

  /**
   * Parses each of the notes in the given collection into MidiMessages to be sent
   * by the midi reciever. It also updates the song's total length in milliseconds.
   * The totalLengthInMilliseonds field will be used on the Thread.sleep() method
   * to make sure the entire midi is played and the user is allowed to enter more
   * commands immediately after the midi is finished playing.
   *
   * @param notes the list of notes
   * @throws InvalidMidiDataException if any given midi data is invalid
   * @throws InterruptedException if Thread.sleep() is not working properly (?)
   */
  private void playNotes(ArrayList<ArrayList<Note>> notes)
          throws InvalidMidiDataException, InterruptedException {
    try {
      this.receiver = synth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    setChannelInstruments();
    int totalLengthInMilliseconds = 0;
    totalLengthInMilliseconds = updateSongLength(totalLengthInMilliseconds, model.getLastBeat());

    for (ArrayList<Note> arr : notes) {
      for (Note n : arr) {
        playNote(n);
      }
    }
  }

  /**
   * Gets the end position of the note, in milliseconds and updates the songs total length in
   * milliseconds if the end position of the note is greater than the current song length.
   *
   * @param totalLengthInMilliseconds the given length in milliseconds
   * @param lastBeat last beat in the piece
   * @return the update total length of the song in milliseconds
   */
  private int updateSongLength(int totalLengthInMilliseconds, int lastBeat) {
    if ((lastBeat * tempo) / 1000 > totalLengthInMilliseconds)  {
      return (lastBeat * tempo) / 1000;
    } else {
      return totalLengthInMilliseconds;
    }
  }

  /**
   * Parses the given note as a MidiMessage to be sent by the receiver by assigning
   * a series of local variables to the midi's representation of each field in the Note.
   *
   * <p>start  = the timestamp of the start of the note in microseconds.
   *
   * <p>end    = the timestamp of the end of the note in microseconds.
   *
   * <p>pitch  = the Integer representation of the note's pitch.
   *
   * <p>inst   = the Integer representation of the note's instrument.
   *
   * <p>volume = the volume of the note.
   *
   * <p>Also note that 2000000 microseconds have been added to both the start and end of each
   *    note that way the first note of the midi will be played on second after the command to
   *    run the midi has been executed.
   *
   * @param note the note
   * @throws InvalidMidiDataException if any given midi data is invalid
   */
  private void playNote(Note note) throws InvalidMidiDataException {
    int start = note.getStartBeat() * tempo + 2000000;
    int end = note.getEndBeat() * tempo + 2000000;
    int pitch = note.getPitchInInt();
    int inst = note.getInstrument();
    int volume = note.getVolume();

    MidiMessage startBeat = new ShortMessage(ShortMessage.NOTE_ON, inst - 1, pitch, volume);
    MidiMessage endBeat = new ShortMessage(ShortMessage.NOTE_OFF, inst - 1, pitch, volume);
    this.receiver.send(startBeat, synth.getMicrosecondPosition());
    this.receiver.send(endBeat, synth.getMicrosecondPosition() + end);

    try {
      output.append("Pitch as an Integer: " + pitch + "\n"
              + "Starting Position in microseconds: " + start + "\n"
              + "Ending Position in microseconds: " + end + "\n"
              + "Instrument Number: " + inst + "\n"
              + "Volume: " + volume + "\n\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void renderMusicEditor() {
    // only needs to play when controller tells it to be updated based on the model
  }


  /**
   * Same as {@link MidiViewImpl#playNotes(ArrayList)} except that it does not let the receiver
   * send MidiMessages. This will be used to test the string representation of the output for
   * the midi without actually playing the midi.
   *
   * @param notes the notes
   * @throws InvalidMidiDataException if any given midi data is invalid
   */
  private void mockPlayNotes(Collection<Note> notes) throws InvalidMidiDataException {
    for (Note note : notes) {
      mockPlayNote(note);
    }
  }

  /**
   * Same as {@link MidiViewImpl#playNote(Note)} except that it only appends the string
   * representation of the midi note and not actually play the midi.
   *
   * @param note note to play
   * @throws InvalidMidiDataException if any given midi data is invalid
   */
  private void mockPlayNote(Note note) throws InvalidMidiDataException {
    int start = note.getStartBeat() * tempo / 1000000;
    int end = note.getEndBeat() * tempo / 1000000;
    int pitch = note.getPitchInInt();
    int inst = note.getInstrument();
    int volume = note.getVolume();

    try {
      output.append("Pitch as an Integer: " + pitch + "\n"
              + "Starting Position in microseconds: " + start + "\n"
              + "Ending Position in microseconds: " + end + "\n"
              + "Instrument Number: " + inst + "\n"
              + "Volume: " + volume + "\n\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This will mock the output of the midi without playing the midi.
   *
   * @param notes the notes
   * @throws InvalidMidiDataException if any given midi data is invalid
   */
  public void mockRenderMusicEditor(Collection<Note> notes) throws InvalidMidiDataException {
    this.mockPlayNotes(notes);
  }

  @Override
  public void updateMusic() {
    try {
      playNotes(model.getNotes(model.getTime(), model.getTime()));
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setKeyListener(KeyboardListener k) {
    //do nothing
  }

  @Override
  public void setMouseListener(MouseHandler m) {
    //do nothing
  }
}
