package com.tianscar.javasound.resspi.test;

import com.tianscar.javasound.sampled.AudioResourceLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.*;
import java.io.IOException;

public class JDKReaderTest {

    @Test
    @DisplayName("wav -> pcm, play with SPI")
    public void readAndPlayWAV() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioFileFormat fileFormat = AudioResourceLoader.getAudioFileFormat(Thread.currentThread().getContextClassLoader(), "fbodemo1.wav");
        System.out.println("file format: " + fileFormat);
        System.out.println("file audio format: " + fileFormat.getFormat());
        AudioInputStream stream = AudioResourceLoader.getAudioInputStream(Thread.currentThread().getContextClassLoader(), "fbodemo1.wav");
        System.out.println("stream: " + stream);
        System.out.println("stream audio format: " + stream.getFormat());
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, stream.getFormat());
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open();
        line.start();
        byte[] buf = new byte[1024 * 8];
        while (true) {
            int r = stream.read(buf, 0, buf.length);
            if (r < 0) {
                break;
            }
            line.write(buf, 0, r);
        }
        line.drain();
        line.stop();
        line.close();
        stream.close();
    }

}
