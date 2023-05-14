package com.tianscar.javasound.sampled.spi;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Provider for audio resource-reading services.  Classes providing concrete
 * implementations can parse the format information from one or more types of
 * audio resource, and can produce audio input streams from resources of these types.
 *
 * @author Karstian Lee
 */
public interface AudioResourceReader {

    /**
     * Obtains the audio file format of the resource provided.  The resource must
     * point to valid audio file data.
     * @param resourceLoader the {@code ClassLoader} to load resource
     * @param name the resource name from which file format information should be
     * extracted
     * @return an <code>AudioFileFormat</code> object describing the audio file format
     * @throws UnsupportedAudioFileException if the resource does not point to valid audio
     * file data recognized by the system
     * @throws IOException if an I/O exception occurs
     */
    AudioFileFormat getAudioFileFormat(ClassLoader resourceLoader, String name) throws UnsupportedAudioFileException, IOException;

    /**
     * Obtains an audio input stream from the resource provided.  The resource must
     * point to valid audio file data.
     * @param resourceLoader the {@code ClassLoader} to load resource
     * @param name the resource name for which the <code>AudioInputStream</code> should be
     * constructed
     * @return an <code>AudioInputStream</code> object based on the audio file data pointed
     * to by the resource
     * @throws UnsupportedAudioFileException if the resource does not point to valid audio
     * file data recognized by the system
     * @throws IOException if an I/O exception occurs
     */
    AudioInputStream getAudioInputStream(ClassLoader resourceLoader, String name) throws UnsupportedAudioFileException, IOException;

}
