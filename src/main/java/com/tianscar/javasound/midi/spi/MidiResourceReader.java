package com.tianscar.javasound.midi.spi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiFileFormat;
import javax.sound.midi.Sequence;
import java.io.IOException;

/**
 * A {@code MidiResourceReader} supplies MIDI resource-reading services. Classes
 * implementing this interface can parse the format information from one or more
 * types of MIDI resource, and can produce a {@link Sequence} object from resources of
 * these types.
 *
 * @author Karstian Lee
 */
public interface MidiResourceReader {

    /**
     * Obtains the MIDI file format of the resource provided.
     * The resource must point to valid MIDI file data.
     *
     * @param  resourceLoader the {@code ClassLoader} to load resource
     * @param  name the resource name from which file format information should be extracted
     * @return a {@code MidiFileFormat} object describing the MIDI file format
     * @throws InvalidMidiDataException if the resource does not point to valid MIDI
     *         file data recognized by the system
     * @throws IOException if an I/O exception occurs
     */
    MidiFileFormat getMidiFileFormat(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException;

    /**
     * Obtains a MIDI sequence from the resource provided. The resource must point to
     * valid MIDI file data.
     *
     * @param  resourceLoader the {@code ClassLoader} to load resource
     * @param  name the resource name for which the {@code Sequence} should be constructed
     * @return a {@code Sequence} object based on the MIDI file data pointed to
     *         by the resource
     * @throws InvalidMidiDataException if the resource does not point to valid MIDI
     *         file data recognized by the system
     * @throws IOException if an I/O exception occurs
     */
    Sequence getSequence(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException;

}
