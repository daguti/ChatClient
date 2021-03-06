/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.threads.audio;

import chatclient.StaticData;
import static chatclient.StaticData.errStr;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author ESa10969
 */
public class AudioPlayer implements Runnable {
    SourceDataLine line;
    final int bufSize = 16384;
    Thread thread;
    AudioInputStream audioInputStream;
    
    public AudioPlayer(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }
    
    public void start() {
      errStr = null;
      thread = new Thread(this);
      thread.setName("Playback");
      thread.start();
    }

    public void stop() {
      thread = null;
    }

    private void shutDown(String message) {
      if ((errStr = message) != null) {
        System.err.println(errStr);
      }
      if (thread != null) {
        thread = null;
        
      }
    }

    public void run() {

      // make sure we have something to play
      if (audioInputStream == null) {
        shutDown("No loaded audio to play back");
        return;
      }
      // reset to the beginnning of the stream
      try {
        audioInputStream.reset();
      } catch (Exception e) {
        shutDown("Unable to reset the stream\n" + e);
        return;
      }

      AudioFormat format = StaticData.audioFormat;

      AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format,
          audioInputStream);

      if (playbackInputStream == null) {
        shutDown("Unable to convert stream of format " + audioInputStream + " to format " + format);
        return;
      }

      // define the required attributes for our line,
      // and make sure a compatible line is supported.

      DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
      if (!AudioSystem.isLineSupported(info)) {
        shutDown("Line matching " + info + " not supported.");
        return;
      }

      // get and open the source data line for playback.

      try {
        line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format, bufSize);
      } catch (LineUnavailableException ex) {
        shutDown("Unable to open the line: " + ex);
        return;
      }

      // play back the captured audio data

      int frameSizeInBytes = format.getFrameSize();
      int bufferLengthInFrames = line.getBufferSize() / 8;
      int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
      byte[] data = new byte[bufferLengthInBytes];
      int numBytesRead = 0;

      // start the source data line
      line.start();

      while (thread != null) {
        try {
          if ((numBytesRead = playbackInputStream.read(data)) == -1) {
            break;
          }
          int numBytesRemaining = numBytesRead;
          while (numBytesRemaining > 0) {
            numBytesRemaining -= line.write(data, 0, numBytesRemaining);
          }
        } catch (Exception e) {
          shutDown("Error during playback: " + e);
          break;
        }
      }
      // we reached the end of the stream.
      // let the data play out, then
      // stop and close the line.
      if (thread != null) {
        line.drain();
      }
      line.stop();
      line.close();
      line = null;
      shutDown(null);
    }
}
