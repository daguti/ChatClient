/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.remote;

import chat.remote.interfaces.ClientRemoteItfz;
import chatclient.GUI.ChatWindow;
import chatclient.StaticData;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ESa10969
 */
public class ClientRemote extends UnicastRemoteObject implements ClientRemoteItfz {
    String userName;
    public ClientRemote(String username) throws RemoteException {}
    
    @Override
    public void write(String msg, StyledDocument msgDocument, String userFrom) throws RemoteException {
        if(StaticData.chatWindowsMap.get(userFrom) == null) createChatWindow(userFrom);
        StaticData.chatWindowsMap.get(userFrom).addStyledText(userFrom,msg,msgDocument);
    }

    @Override
    public String getUsername() throws RemoteException {
        return userName;
    }

    @Override
    public void updateConnectedUsers(HashMap<String, ClientRemoteItfz> userConMap) throws RemoteException {
        StaticData.usersMap = userConMap;
        String[] userList = new String[StaticData.usersMap.size()];
        int i = 0;
        
        for(String userName : StaticData.usersMap.keySet()) {
            userList[i] = userName;
            i++;
        }
        if(StaticData.guiList != null)StaticData.guiList.setTable(userList);
    }

    @Override
    public void receiveFile(String fileName, byte[] bytes, String userFrom) throws RemoteException {
        //Variable definition
        InputStream in = new ByteArrayInputStream(bytes);
        if(StaticData.chatWindowsMap.get(userFrom) == null) createChatWindow(userFrom);
        StaticData.chatWindowsMap.get(userFrom).addSendItem(userFrom, in, fileName,null);
    }
    
    private void createChatWindow(String userFrom) {
        try {
            StaticData.chatWindowsMap.put(userFrom, new ChatWindow(userFrom));
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    StaticData.server.getProfileImage(userFrom, StaticData.cliRem);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientRemote.class.getName()).log(Level.SEVERE, null, ex);
                }
                StaticData.chatWindowsMap.get(userFrom).setVisible(true);
            });
        } catch (IOException ex) {
            Logger.getLogger(ClientRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void getProfileImage(String user, byte[] bytes) throws RemoteException {
        ChatWindow win = StaticData.chatWindowsMap.get(user);
        if(win == null) {
            StaticData.guiList.addProfileImage(bytes);
        } else {
            win.addProfileImage(bytes);
        }
    }

    @Override
    public void getAudioClip(String userFrom, byte[] bytes) throws RemoteException {
        //Variable definition
        ChatWindow win             = StaticData.chatWindowsMap.get(userFrom);
        ByteArrayInputStream baiut = new ByteArrayInputStream(bytes);
        AudioInputStream stream    = null;
        
        try {
            stream = AudioSystem.getAudioInputStream(baiut);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ClientRemote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(win == null){
            createChatWindow(userFrom);
            win = StaticData.chatWindowsMap.get(userFrom);
        }
        StaticData.audioInputStream = stream;
        win.setAudioClipOnMessages(userFrom);
    }
}
