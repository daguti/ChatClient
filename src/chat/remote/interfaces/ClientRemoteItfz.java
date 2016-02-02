/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.remote.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ESa10969
 */
public interface ClientRemoteItfz extends Remote {
    public void write (String msg, StyledDocument msgDocument, String userFrom)throws RemoteException ;
    public String getUsername()throws RemoteException;
    public void updateConnectedUsers(HashMap<String, ClientRemoteItfz> userConMap) throws RemoteException;
    public void receiveFile(String fileName, byte[] bytes, String userFrom) throws RemoteException;
    public void getProfileImage(String user, byte[] bytes) throws RemoteException;
    public void getAudioClip(String userFrom, byte[] bytes) throws RemoteException;
}
