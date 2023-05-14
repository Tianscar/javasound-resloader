package com.tianscar.javasound.sampled;

import com.tianscar.javasound.sampled.spi.AudioResourceReader;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

/**
 * The default implementation of {@link AudioResourceReader} which re-directs to {@link javax.sound.sampled.spi.AudioFileReader}.
 *
 * @author Karstian Lee
 */
public class JDKAudioResourceReader implements AudioResourceReader {

    @Override
    public AudioFileFormat getAudioFileFormat(ClassLoader resourceLoader, String name) throws UnsupportedAudioFileException, IOException {
        InputStream stream = resourceLoader.getResourceAsStream(name);
        if (stream == null) throw new IOException("could not load resource \"" + name + "\" with ClassLoader \"" + resourceLoader + "\"");
        else return AudioSystem.getAudioFileFormat(stream);
    }

    @Override
    public AudioInputStream getAudioInputStream(ClassLoader resourceLoader, String name) throws UnsupportedAudioFileException, IOException {
        InputStream stream = resourceLoader.getResourceAsStream(name);
        if (stream == null) throw new IOException("could not load resource \"" + name + "\" with ClassLoader \"" + resourceLoader + "\"");
        else return AudioSystem.getAudioInputStream(stream);
    }

}
