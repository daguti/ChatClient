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
public interface ServerRemoteItfz extends Remote {
    public boolean loginUser(String user, String pass, ClientRemoteItfz cli) throws RemoteException;
    public void logoutUser(String userName) throws RemoteException;
    public String send(String msg, StyledDocument msgDocument, ClientRemoteItfz cli, String userFrom) throws RemoteException;
    public HashMap<String, ClientRemoteItfz> getConnectedUsers()throws RemoteException;
    public boolean signIn(String user, String pass) throws RemoteException;
    public void sendFile(String fileName, byte[] bytes, ClientRemoteItfz cli, String userFrom) throws RemoteException;
    public void setProfileImage(String user, byte[] bytes) throws RemoteException;
    public void getProfileImage(String user, ClientRemoteItfz cli) throws RemoteException;
}
