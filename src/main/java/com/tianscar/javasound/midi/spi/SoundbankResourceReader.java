package com.tianscar.javasound.midi.spi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * A {@code SoundbankResourceReader} supplies soundbank resource-reading services. Concrete
 * subclasses of {@code SoundbankReader} parse a given soundbank resource, producing
 * a {@link javax.sound.midi.Soundbank} object that can be loaded into a
 * {@link javax.sound.midi.Synthesizer}.
 *
 * @author Karstian Lee
 */
public interface SoundbankResourceReader {

    /**
     * Obtains a soundbank object from the resource provided.
     *
     * @param  resourceLoader the {@code ClassLoader} to load resource
     * @param  name resource name representing the soundbank.
     * @return soundbank object
     * @throws InvalidMidiDataException if the resource does not point to valid MIDI
     *         soundbank data recognized by this soundbank reader
     * @throws IOException if an I/O error occurs
     */
    Soundbank getSoundbank(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException;

}
