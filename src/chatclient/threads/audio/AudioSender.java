/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.threads.audio;

import chat.remote.interfaces.ClientRemoteItfz;
import chat.remote.interfaces.ServerRemoteItfz;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author ESa10969
 */
public class AudioSender implements Runnable {
    AudioInputStream audStr;
    ServerRemoteItfz server;
    String userFrom;
    ClientRemoteItfz cli;

    public AudioSender(AudioInputStream audStr, ServerRemoteItfz server, String userFrom, 
                       ClientRemoteItfz cli) {
        this.audStr   = audStr;
        this.server   = server;
        this.userFrom = userFrom;
        this.cli      = cli;
    }
    
    
    
    @Override
    public void run() {
        //Variable definition
        int available;
        byte[] readBytes;
        byte[] bytes;
        int count = 0;
        int frameSize;
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            AudioSystem.write(audStr, AudioFileFormat.Type.WAVE,baos);
            audStr.close();
            baos.close();
            
            server.sendAudioClip(userFrom, cli, baos.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(AudioSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
