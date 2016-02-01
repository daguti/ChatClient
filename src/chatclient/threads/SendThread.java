/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.threads;

import chat.remote.interfaces.ClientRemoteItfz;
import chatclient.StaticData;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author ESa10969
 */
public class SendThread implements Runnable {

    InputStream      fIn;
    String           userTo;
    String           fileName;
    ClientRemoteItfz cli;
    String           userName;
    JProgressBar     bar;
    
    public SendThread(InputStream fIn, String userTo, String fileName, 
                      ClientRemoteItfz cli, String userName, JProgressBar bar) {
        this.fIn      = fIn;
        this.userTo   = userTo;
        this.fileName = fileName;
        this.cli      = cli;
        this.userName = userName;
        this.bar      = bar;
    }
    
    @Override
    public void run() {
        byte[] bytes;
        int available;
        try {
            available = fIn.available();
            bytes = new byte[available];
            for(int i = 0; i < available; i++) {
                bytes[i] = (byte) fIn.read();
                bar.setValue(i);
            }
            bar.setVisible(false);
            StaticData.server.sendFile(fileName, bytes, cli, userName);
            fIn.close();
        } catch (RemoteException ex) {
            Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
