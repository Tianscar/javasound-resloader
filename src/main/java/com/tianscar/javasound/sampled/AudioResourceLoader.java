package com.tianscar.javasound.sampled;

import com.tianscar.javasound.sampled.spi.AudioResourceReader;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * The factory class to load audio from resources
 */
public class AudioResourceLoader {

    /**
     * Obtains the audio file format of the specified resource.  The resource must
     * point to valid audio file data.
     * @param resourceLoader the {@code ClassLoader} to load resource
     * @param name the resource name from which file format information should be
     * extracted
     * @return an <code>AudioFileFormat</code> object describing the audio file format
     * @throws UnsupportedAudioFileException if the resource does not point to valid audio
     * file data recognized by the system
     * @throws IOException if an input/output exception occurs
     */
    public static AudioFileFormat getAudioFileFormat(ClassLoader resourceLoader, String name)
            throws UnsupportedAudioFileException, IOException {

        List<AudioResourceReader> providers = getAudioResourceReaders();
        AudioFileFormat format = null;

        for (AudioResourceReader reader : providers) {
            try {
                format = reader.getAudioFileFormat(resourceLoader, name); // throws IOException
                break;
            }
            catch (UnsupportedAudioFileException ignored) {
            }
        }

        if (format == null) {
            throw new UnsupportedAudioFileException("file is not a supported file type");
        }
        else {
            return format;
        }
    }

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
    public static AudioInputStream getAudioInputStream(ClassLoader resourceLoader, String name)
            throws UnsupportedAudioFileException, IOException {

        List<AudioResourceReader> providers = getAudioResourceReaders();
        AudioInputStream audioStream = null;

        for (AudioResourceReader reader : providers) {
            try {
                audioStream = reader.getAudioInputStream(resourceLoader, name); // throws IOException
                break;
            }
            catch (UnsupportedAudioFileException ignored) {
            }
        }

        if (audioStream == null) {
            throw new UnsupportedAudioFileException("could not get audio input stream from input resource");
        }
        else {
            return audioStream;
        }
    }

    /**
     * Obtains the set of audio file readers that are currently installed on the system.
     * @return a List of
     * {@link javax.sound.sampled.spi.AudioFileReader
     * AudioFileReader}
     * objects representing the installed audio file readers.  If no audio file
     * readers are available on the system, an empty List is returned.
     */
    private static List<AudioResourceReader> getAudioResourceReaders() {
        return getProviders(AudioResourceReader.class);
    }

    private static<T> List<T> getProviders(Class<T> providerClass) {
        List<T> providers = new ArrayList<>();
        for (T t : ServiceLoader.load(providerClass)) {
            if (providerClass.isInstance(t)) providers.add(t);
        }
        return providers;
    }

}
