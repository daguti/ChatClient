/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.threads.audio;

import chatclient.StaticData;
import static chatclient.StaticData.audioInputStream;
import static chatclient.StaticData.duration;
import static chatclient.StaticData.errStr;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author ESa10969
 */
public class AudioCapturer implements Runnable {
    
    TargetDataLine line;
    Thread thread;
    
    public AudioCapturer() {
        
    }
    
    public void start() {
      errStr = null;
      thread = new Thread(this);
      thread.setName("Capture");
      thread.start();
    }

    public void stop() {
      thread = null;
    }

    private void shutDown(String message) {
      if ((errStr = message) != null && thread != null) {
        thread = null;
       
        System.err.println(errStr);
      }
    }
    
    @Override
    public void run() {
        duration = 0.0;
        audioInputStream = null;

        AudioFormat format = StaticData.audioFormat;

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
          shutDown("Line matching " + info + " not supported.");
          return;
        }
        
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
        } catch (LineUnavailableException ex) {
            shutDown("Unable to open the line: " + ex);
            return;
        } catch (SecurityException ex) {
            shutDown(ex.toString());
            //JavaSound.showInfoDialog();
            return;
        } catch (Exception ex) {
            shutDown(ex.toString());
            return;
        }

        // play back the captured audio data
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int frameSizeInBytes = format.getFrameSize();
        int bufferLengthInFrames = line.getBufferSize() / 8;
        int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
        byte[] data = new byte[bufferLengthInBytes];
        int numBytesRead;

        line.start();

        while (thread != null) {
          if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
            break;
          }
          out.write(data, 0, numBytesRead);
        }

        // we reached the end of the stream.
        // stop and close the line.
        line.stop();
        line.close();
        line = null;

        // stop and close the output stream
        try {
          out.flush();
          out.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }

        // load bytes into the audio input stream for playback

        byte audioBytes[] = out.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
        audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);

        long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format
            .getFrameRate());
        duration = milliseconds / 1000.0;

        try {
          audioInputStream.reset();
        } catch (IOException ex) {
          ex.printStackTrace();
          return;
        }

    }
    
}
