package com.tianscar.javasound.midi;

import com.tianscar.javasound.midi.spi.MidiResourceReader;
import com.tianscar.javasound.midi.spi.SoundbankResourceReader;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiFileFormat;
import javax.sound.midi.Sequence;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * The factory class to load midi/soundbank from resources.
 *
 * @author Karstian Lee
 */
public class MidiResourceLoader {

    private MidiResourceLoader() {
        throw new UnsupportedOperationException();
    }

    /**
     * Obtains the MIDI file format of the data in the specified resource. The resource
     * must point to valid MIDI file data for a file type recognized
     * by the system.
     * <p>
     * This operation can only succeed for files of a type which can be parsed
     * by an installed file reader.  It may fail with an InvalidMidiDataException
     * even for valid files if no compatible file reader is installed.  It
     * will also fail with an InvalidMidiDataException if a compatible file reader
     * is installed, but encounters errors while determining the file format.
     *
     * @param  resourceLoader the {@code ClassLoader} to load resource
     * @param  name the resource name from which file format information should be
     * extracted
     * @return a <code>MidiFileFormat</code> object describing the MIDI file
     * format
     * @throws InvalidMidiDataException if the resource does not point to valid MIDI
     * file data recognized by the system
     * @throws IOException if an I/O exception occurs while accessing the resource
     */
    public static MidiFileFormat getMidiFileFormat(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException {

        List<MidiResourceReader> providers = getMidiResourceReaders();
        MidiFileFormat format = null;

        for (MidiResourceReader reader : providers) {
            try {
                format = reader.getMidiFileFormat(resourceLoader, name); // throws IOException
                break;
            }
            catch (InvalidMidiDataException ignored) {
            }
        }

        if (format == null) {
            throw new InvalidMidiDataException("resource is not a supported file type");
        }
        else {
            return format;
        }
    }

    /**
     * Obtains a MIDI sequence from the specified resource. The resource must
     * point to valid MIDI file data for a file type recognized
     * by the system.
     * <p>
     * This operation can only succeed for files of a type which can be parsed
     * by an installed file reader.  It may fail with an InvalidMidiDataException
     * even for valid files if no compatible file reader is installed.  It
     * will also fail with an InvalidMidiDataException if a compatible file reader
     * is installed, but encounters errors while constructing the <code>Sequence</code>
     * object from the file data.
     *
     * @param  resourceLoader the {@code ClassLoader} to load resource
     * @param  name the resource name from which the <code>Sequence</code> should be
     * constructed
     * @return a <code>Sequence</code> object based on the MIDI file data
     * pointed to by the resource
     * @throws InvalidMidiDataException if the resource does not point to valid MIDI
     * file data recognized by the system
     * @throws IOException if an I/O exception occurs while accessing the resource
     */
    public static Sequence getSequence(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException {

        List<MidiResourceReader> providers = getMidiResourceReaders();
        Sequence sequence = null;

        for (MidiResourceReader reader : providers) {
            try {
                sequence = reader.getSequence(resourceLoader, name); // throws IOException
                break;
            } catch (InvalidMidiDataException ignored) {
            }
        }

        if (sequence == null) {
            throw new InvalidMidiDataException("could not get sequence from resource");
        }
        else {
            return sequence;
        }
    }

    /**
     * Constructs a <code>Soundbank</code> by reading it from the specified resource.
     * The resource must point to a valid MIDI soundbank file.
     *
     * @param  resourceLoader the {@code ClassLoader} to load resource
     * @param  name the source of the sound bank data
     * @return the sound bank
     * @throws InvalidMidiDataException if the resource does not point to valid MIDI
     * soundbank data recognized by the system
     * @throws IOException if an I/O error occurred when loading the soundbank
     */
    public static Soundbank getSoundbank(ClassLoader resourceLoader, String name) throws InvalidMidiDataException, IOException {

        SoundbankResourceReader sp;
        Soundbank s;

        List<SoundbankResourceReader> providers = getSoundbankResourceReaders();

        for (SoundbankResourceReader provider : providers) {
            sp = provider;
            s = sp.getSoundbank(resourceLoader, name);

            if (s != null) {
                return s;
            }
        }
        throw new InvalidMidiDataException("cannot get soundbank from resource");

    }

    private static List<SoundbankResourceReader> getSoundbankResourceReaders() {
        return getProviders(SoundbankResourceReader.class);
    }

    private static List<MidiResourceReader> getMidiResourceReaders() {
        return getProviders(MidiResourceReader.class);
    }

    private static<T> List<T> getProviders(Class<T> providerClass) {
        List<T> providers = new ArrayList<>();
        for (T t : ServiceLoader.load(providerClass)) {
            if (providerClass.isInstance(t)) providers.add(t);
        }
        return providers;
    }

}
