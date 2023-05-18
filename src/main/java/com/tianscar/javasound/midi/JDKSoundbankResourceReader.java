package com.tianscar.javasound.midi;

import com.tianscar.javasound.midi.spi.SoundbankResourceReader;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;

/**
 * The default implementation of {@link SoundbankResourceReader} which re-directs to {@link javax.sound.midi.spi.SoundbankReader}.
 *
 * @author Karstian Lee
 */
public class JDKSoundbankResourceReader implements SoundbankResourceReader {

    @Override
    public Soundbank getSoundbank(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException {
        try (InputStream stream = resourceLoader.getResourceAsStream(name)) {
            if (stream == null) throw new IOException("could not load resource \"" + name + "\" with ClassLoader \"" + resourceLoader + "\"");
            else return MidiSystem.getSoundbank(stream);
        }
    }

}
