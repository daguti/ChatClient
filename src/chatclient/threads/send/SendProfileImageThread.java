/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.threads.send;

import chatclient.StaticData;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ESa10969
 */
public class SendProfileImageThread implements Runnable {
    File image;
    String userName;
    
    public SendProfileImageThread(File image, String userName) {
        this.image    = image;
        this.userName = userName;
    }
    
    @Override
    public void run() {
        byte[] bytes;
        
        try {
            BufferedImage originalImage = ImageIO.read(image);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "png", baos );
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
            StaticData.guiList.addProfileImage(bytes);
            StaticData.server.setProfileImage(userName, bytes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SendProfileImageThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SendProfileImageThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
