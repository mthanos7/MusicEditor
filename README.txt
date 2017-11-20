MODEL

Interfaces:
MusicEditorOperations -
This interface ensures that classes that extend it will users to remove and add notes from their
model. Users will also be able to get a collection of all the notes in their model.
They can also combine or overlay two pieces of
music.
UPDATE: String representation moved to view.

UPDATE:
ReadOnlyModel -
Read only model created to pass to views to get info about model. MusicEditorOperations now extends
this interface.

Classes:
MusicEditorModel -
Represents the state of a music editor by using a list of lists of notes organized by their
starting beat. Each list in the list of lists of notes stores notes whose starting beats are
equal to the index of the list of notes in the list of lists of notes. Meaning, a note starting at
zero would be added to the first list of notes. A note starting at five would be added to the fifth
list of notes. These notes are simply added to the end of their list.
In this implementation, it is very simple to add, remove, and combine music.
Class invariants: List of list of notes will never be empty or null.
Constructor initializes it to an empty list.
Methods only add to these lists and the list cannot be accessed outside of the class.
UPDATE:
Added tempo field so the composition reader wouldn't need to have anything to do with the view.
UPDATE:
Added methods for ReadOnlyModel.

OctavePitch -
Class to represent a specific pitch, meaning one that has a pitch (like a note letter value)
and an octave for that pitch. Chosen because octave and pitch are values that don't
have much meaning without each other in music. Every note must have both an octave and a pitch.
Class invariants: Octave and pitch are valid values from their respective enums.
Constructor ensures that neither are null.
These fields are not changed by any methods and are immutable (because they're enums).

Note -
Represents a music note with a start beat, end beat and an octave and a pitch.
Chosen because this representation is a pretty intuitive way to store significant
information about a given note.
UPDATE: Added new fields to the note that keep track of the
instrument, volume, and pitch as integers.
Class invariants: Start beat will always be a positive number.
End beat will always be a positive number less than or equal to the start beat.
These are both established by the constructor and preserved through all the methods.
These values are not modifiable from outside of the class.
The octave pitch of this note will never be null.
This value is not accessible outside of this class.
Method for getting the octave pitch of this note returns a copy.

Enums:
MusicElement -
Represents a music element which has a duration of one beat. This can be a note, a new note,
or a rest. I've chosen to use this representation because it allows use of both notes with pitch
and rests in a similar context. There are only three relevant values in this implementation of the
music editor model. It also makes it simple to determine what the string representation
of a single beat should be.

Octave -
Represents different octave values.
Limited to reasonable values that can be heard by humans: 0-10.

Pitch -
Represents different pitches of notes. I've chosen this representation because (for this project)
there are only 12 possible pitches for a note.
Pitches know their own "base" frequency, meaning their frequency at octave 0. This is useful
for comparing pitches to each other.
They also know what pitch (in terms of alphabetical values) comes after them. This is useful for
figuring out what pitch comes after this one when trying to get a range of pitches and also
for comparing pitches to each other.

VIEW

Interfaces:
MusicEditorModelOperations-
This interface ensures that classes that extend it will users to render a view of their music.
It has only one method to do this which takes a collection of notes and renders a view.

CompositionBuilder-
A builder of compositions.  Since we do not know in advance what
the name of the main type is for a model, we parameterize this builder interface
by an unknown type.

UPDATE:
ComplexViewOperatiosn
View interface for views that need mouse and key listeners.

UPDATE:
AddNote -
Command interface for adding a note when using mouse listener.

Classes:

UPDATE:
Composite View-
New view for both the GUI view and MIDI view at the same time. Initializes both views
in constructor and takes a read-only model for passing information to the other two views.

MusicEditorViewUtils-
This class can find the range of pitches in a piece,
the last beat, and the most important note at each beat (in order new note, continued note, rest).
These methods are needed by both the GUI and String models, so they have been put into this class
as static methods.

StringViewImp-
Representation of the state of the music editor as a string.
Using an appendable, builds the string representation and then prints it.
Appendable object is used for testing purposes.
UPDATE: Has read only model.
Class invariants: Appendable object will never be null.

GuiViewImp-
Visual representation of the music editor. Interprets collection of notes and passes information
onto its frame field, then initialized it. This frame is what holds and displays the
visual representation.
UPDATE: Has read only model and represents current time with integer from model


GuiViewFrame-
A frame for the GUI view. Organizes its two panels. One represents the keyboard and
the other represents the piece of music. This frame keeps track of the sliding red bar in
the notes panel which is controlled by key presses and allows you to keep track of
the music and see what notes play at each beat on the piano.
UPDATE: No longer has class current time, uses model's time value.
Class invariants: Panel objects and CurTime object will never be null.

NotesPanel-
This class represents the notes in a piece of music as a visual view.
It uses the range of pitches in a piece, the last beat,
and the most important note at each beat (in order new note, continued note, rest).
It displays the notes at each pitch by measure, and shows what pitch and measure they exist at.
This panel also has a sliding red bar that allows you to keep track of the
music and see what notes play at each beat on the piano.
Class invariants: List of OctavePitches, 2D array of music elements and CurTime are never null.
MaxBeat is also always greater than zero.
UPDATE: No longer has current time, uses model's time value.

PianoPanel-
This class creates a visual view of a piano. It uses the range of pitches in a piece, the last beat,
and the most important note at each beat (in order new note, continued note, rest).
It displays the notes played at each pitch on the piano based on where you are in the song.
Class invariants: Collection of notes and CurTime are never null.
UPDATE: No longer has current time, uses model's time value.

MidiViewImp-
This class represents the MIDI view for the given list of notes in the model. It parses the given
collection of Notes into MidiMessages to be sent by the receiver. On every note, the receiver will
send a message that plays the note on the notes startBeat and a message that stops the note at
the notes endBeat. It also takes in an Integer called "delay" which represents the dalay,
in microseconds, between each note that is played on the MIDI.
UPDATE: Has read only model.

CompositionReader-
This will take in a txt file and parse it into a music editor model. The resulting music
editor model can be outputted as a string view on the console, a gui view, or as a MIDI.


UPDATE:
KeyboardListener-
Class to handle key events. Has a map of integers to runnables. These commands
pause and play the song when space is pressed. Arrow keys move song around red line (as opposed to
red line moving). Home and end keys bring you to beginning and end of piece respectively.

UPDATE:
MouseHandler-
Class to handle mouse events. Has an AddNote command created in the controller to add a note
to the model. Does not work perfectly-- notes aren't added at the exact key they are pressed (but
they are added).

CONTROLLER

Complex Controller-
Complex controller takes a complex view and a read only model. Has a timer to trigger
time passing based on the tempo given by the read only model. Changes time then updates the model.
Also has key and mouse handlers (read more about these above). Creates key map and AddNote command
for keys and mouse. Plays the midi along with the gui view.

Simple Controller-
Very simply controller only for the console view.

AddNote-
Command function object for adding a note to the piece on mouse events.
Adds a note of fixed duration, instrument and volume at the current time.
Variable field of note is the pitch which comes from the location of the click.

OTHER

MusicEditor-
Main method for running the music editor. Run with command line arguments for the type of view
you want and song file you want to play from.

ViewFactory-
Helps main method get desired view based on command line input.
