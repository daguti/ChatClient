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
import chatclient.threads.audio.AudioCapturer;
import chatclient.threads.audio.AudioPlayer;
import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

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
  public static File framIcon;
  public static String errStr;
  public static AudioInputStream audioInputStream;
  public static Double duration;
  public static AudioCapturer capturer;
  public static AudioPlayer player;
  public static AudioFormat audioFormat;
}