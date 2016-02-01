/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient;

import chat.remote.interfaces.ClientRemoteItfz;
import chat.remote.interfaces.ServerRemoteItfz;
import chatclient.GUI.ChatWindow;
import chatclient.GUI.UserList;
import java.util.HashMap;

/**
 *
 * @author ESa10969
 */
public class StaticData {
  public static ServerRemoteItfz server;
  public static ClientRemoteItfz cliRem;
  public static HashMap<String,ClientRemoteItfz> usersMap;
  public static HashMap<String,ChatWindow> chatWindowsMap;
  public static String userName;
  public static UserList guiList;
}