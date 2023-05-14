package com.tianscar.javasound.midi;

import com.tianscar.javasound.midi.spi.MidiResourceReader;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiFileFormat;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.IOException;
import java.io.InputStream;

/**
 * The default implementation of {@link MidiResourceReader} which re-directs to {@link javax.sound.midi.spi.MidiFileReader}.
 *
 * @author Karstian Lee
 */
public class JDKMidiResourceReader implements MidiResourceReader {

    @Override
    public MidiFileFormat getMidiFileFormat(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException {
        InputStream stream = resourceLoader.getResourceAsStream(name);
        if (stream == null) throw new IOException("could not load resource \"" + name + "\" with ClassLoader \"" + resourceLoader + "\"");
        else return MidiSystem.getMidiFileFormat(stream);
    }

    @Override
    public Sequence getSequence(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException {
        InputStream stream = resourceLoader.getResourceAsStream(name);
        if (stream == null) throw new IOException("could not load resource \"" + name + "\" with ClassLoader \"" + resourceLoader + "\"");
        else return MidiSystem.getSequence(stream);
    }

}
