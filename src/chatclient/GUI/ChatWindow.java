/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.GUI;

import chatclient.StaticData;
import chatclient.emoticons.Emoji;
import chatclient.emoticons.EmojiIcons;
import chatclient.emoticons.GifImageSolver;
import chatclient.threads.SendThread;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import sun.awt.shell.ShellFolder;

/**
 *
 * @author ESa10969
 */
public class ChatWindow extends javax.swing.JFrame {
  private static final long serialVersionUID = 1L;
  private final String userTo;
  private final JFrame frame = this;
  private Image origImg;
  private Font emojiFont;
  private static final String ELEM = AbstractDocument.ElementNameAttribute;
  private static final String ICON = StyleConstants.IconElementName;
  
  /**
   * Creates new form ChatWindow
   * @param userTo
   * @throws java.io.IOException
   */
  public ChatWindow(String userTo) throws IOException {
    initComponents();
    addEvent();
    userPane.setText(userTo);
    this.userTo = userTo;
    setTitle("iChat V1.0");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addEmojiFont();
    addEmojisToPane();
    addAnimalsToPane();
    StyledDocument doc = allMessages.getStyledDocument();
      try {
          doc.insertString(doc.getLength(), "\n", getStartStyle(doc));
      } catch (BadLocationException ex) {
          Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  private void addAnimalsToPane() {
      //Variable definition
      EmojiIcons icons;
      
      icons = new EmojiIcons(animalsPane, messages, "emojis/ANIMALS/", 5);
      icons.addEmojisToPanel("gif");
      emojisPane.setBackground(Color.WHITE);
  }
  
  private void addEmojisToPane() {
      //Variable definition
      EmojiIcons icons;
      
      icons = new EmojiIcons(emojisPane, messages, "emojis/smileys/", 80);
      icons.addEmojisToPanel("png");
      emojisPane.setBackground(Color.WHITE);
  }
  private void addEmojiFont() {
    InputStream is = this.getClass().getResourceAsStream("fonts/OpenSansEmoji.ttf");
      try {
          emojiFont = Font.createFont(Font.TRUETYPE_FONT, is); 
          allMessages.setFont(emojiFont);
          messages.setFont(new Font(emojiFont.getFontName(), Font.PLAIN, 16));
          
          
      } catch (FontFormatException ex) {
          Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  public void addProfileImage(byte[] bytes) {
      //Variable definition
      ImageIcon img;
      
      if(bytes != null) {
        InputStream fis = new ByteArrayInputStream(bytes);
          try {
              origImg       = ImageIO.read(fis);
              Image resImg  = origImg.getScaledInstance(userToImage.getWidth(), userToImage.getHeight(), 0);
              img           = new ImageIcon(resImg);
              userToImage.setIcon(img);
          } catch (IOException ex) {
              Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
          }
        //userToImage.setOpaque(false);
      }
  }
  
  private void addEvent() {
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
          int confirm = JOptionPane.showConfirmDialog(frame, 
                                                      "Are you sure to close this window?", "Really Closing?", 
                                                      JOptionPane.YES_NO_OPTION,
                                                      JOptionPane.QUESTION_MESSAGE);
          if (confirm == JOptionPane.YES_OPTION){
                StaticData.chatWindowsMap.remove(userTo);
                frame.dispose();
              }
      }
    });
  }
  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        userToImage = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userPane = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        allMessages = new javax.swing.JTextPane();
        sendButton = new javax.swing.JButton();
        addFiles = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        messages = new javax.swing.JTextPane();
        emojisScrollPane = new javax.swing.JScrollPane();
        emojisPane = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        animalsPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        userToImage.setBackground(new java.awt.Color(0, 153, 255));
        userToImage.setOpaque(true);
        userToImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userToImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(userToImage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(userToImage, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        userPane.setEditable(false);
        userPane.setFont(new java.awt.Font("Tempus Sans ITC", 1, 11)); // NOI18N
        userPane.setForeground(new java.awt.Color(0, 153, 255));
        userPane.setEnabled(false);
        jScrollPane2.setViewportView(userPane);

        allMessages.setEditable(false);
        allMessages.setFont(new java.awt.Font("Pristina", 0, 14)); // NOI18N
        allMessages.setPreferredSize(new java.awt.Dimension(600, 2000));
        jScrollPane3.setViewportView(allMessages);

        sendButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        sendButton.setText(">");
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendButtonMouseClicked(evt);
            }
        });

        addFiles.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        addFiles.setText("+");
        addFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addFilesMouseClicked(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N

        jScrollPane1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N

        messages.setFont(new java.awt.Font("Pristina", 0, 14)); // NOI18N
        messages.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messagesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(messages);

        jTabbedPane1.addTab("Write", jScrollPane1);

        emojisPane.setEnabled(false);
        emojisScrollPane.setViewportView(emojisPane);

        jTabbedPane1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/chatclient/GUI/img/rsz_01_y.png")), emojisScrollPane); // NOI18N

        jScrollPane4.setViewportView(animalsPane);

        jTabbedPane1.addTab("Animated", jScrollPane4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(458, 458, 458)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Emoticons");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void sendButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendButtonMouseClicked
    sendMessage();
  }//GEN-LAST:event_sendButtonMouseClicked

    private void addFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addFilesMouseClicked
        // Variable definition
        JFileChooser chooser = new JFileChooser();
        File sendFile;
        int returnVal;
        
        returnVal = chooser.showOpenDialog(this);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            sendFile = chooser.getSelectedFile();
            try {
                Thread t;
                SendThread sender;
                FileInputStream fIn = new FileInputStream(sendFile);
                JProgressBar bar = new JProgressBar(0, fIn.available());
                bar.setValue(0);
                bar.setStringPainted(true);
                addSendItem(StaticData.userName, fIn, sendFile.getName(), bar);
                sender = new SendThread(fIn, userTo, sendFile.getName(), 
                                        StaticData.usersMap.get(userTo), StaticData.userName, bar);
                t = new Thread(sender);
                t.start();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addFilesMouseClicked

    private void userToImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userToImageMouseClicked
        Image resizImg = origImg.getScaledInstance(200, 200, 0);
        ImageIcon resizIcon = new ImageIcon(resizImg);
        JLabel lbl = new JLabel(resizIcon);
        lbl.setSize(resizIcon.getIconWidth(), resizIcon.getIconHeight());
        JOptionPane.showMessageDialog(null, lbl, userTo, 
                                 JOptionPane.PLAIN_MESSAGE, null);
        
    }//GEN-LAST:event_userToImageMouseClicked

    private void messagesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messagesKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }//GEN-LAST:event_messagesKeyPressed

  private void sendMessage() {
    String txt = messages.getText();
    
    txt = Emoji.replaceInText(txt);
    //allMessages.setText(allMessages.getText() + "\n" + txt);
    addStyledText(StaticData.userName, txt, messages.getStyledDocument());
    try {
        StaticData.server.send(txt, messages.getStyledDocument(), StaticData.usersMap.get(userTo), StaticData.userName);
    } catch (RemoteException ex) {
        Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
    }
    messages.setText("");
  }
  
  public void addSendItem(String user, InputStream in, String fileName, JProgressBar bar) {
      //Variable definition
      StyledDocument styleDocument = allMessages.getStyledDocument();
      Style primaryStyle           = getPrimaryStyle(styleDocument);
      Style secondaryStyle         = getSecondaryStyle(styleDocument, primaryStyle);
      StyleContext context         = new StyleContext();
      Style labelStyle             = context.getStyle(StyleContext.DEFAULT_STYLE);
      Icon icon;
      JLabel label                 = new JLabel();
      JPanel panel                 = new JPanel();
      
      icon = getIconForFileType(fileName);
      label.setIcon(icon);
      label.setText(fileName);
      panel.add(label);
      if(bar != null) {
          panel.add(bar);
      }
      StyleConstants.setComponent(labelStyle, panel);
      try {
          addLabelListener(label, in, fileName);
          styleDocument.insertString(styleDocument.getLength(), user + ": \n", primaryStyle);
          styleDocument.insertString(styleDocument.getLength(), "Ignored", labelStyle);
          //allMessages.insertComponent(label);
          //styleDocument.insertString(styleDocument.getLength(), " ", iconStyle);
          styleDocument.insertString(styleDocument.getLength(),"\n", secondaryStyle);
      } catch (BadLocationException ex) {
          Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  private Icon getIconForFileType(String fileName) {
      //variable definition
      Icon icon = null;
      ShellFolder sf;
      File file;
      
      try {
          file = new File(System.getProperty("user.dir") + "/" + fileName);
          file.createNewFile();
          sf = ShellFolder.getShellFolder(file);
          icon = new ImageIcon(sf.getIcon(true));
          if(file.exists()) file.delete();
      } catch (FileNotFoundException ex) {
          Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
      return icon;
  }
  public void addLabelListener(JLabel label, InputStream in, String fileName) {     
      label.addMouseListener(new MouseAdapter(){
          public void mouseClicked(MouseEvent e) {
              if (e.getClickCount() == 2) {
                  JFileChooser chooser = new JFileChooser();
                  chooser.setSelectedFile(new File(fileName));
                  chooser.showSaveDialog(frame);
                  try {
                      int content;
                      File saveFile = chooser.getSelectedFile();
                      saveFile.createNewFile();
                      FileOutputStream fOut = new FileOutputStream(saveFile);
                      while((content = in.read()) != -1) {
                          fOut.write(content);
                      }
                      fOut.close();
                      in.close();
                  } catch (IOException ex) {
                      Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }
      });
  }
  public Style getPrimaryStyle(StyledDocument styleDocument) {
      Style primaryStyle = styleDocument.addStyle("Primary", null);
      StyleConstants.setFontFamily(primaryStyle, emojiFont.getFamily());
      StyleConstants.setFontSize(primaryStyle, 16);
      StyleConstants.setForeground(primaryStyle, new Color(0x552AFF));
      StyleConstants.setUnderline(primaryStyle, true);
      return primaryStyle;
  }
  
  public Style getSecondaryStyle(StyledDocument styleDocument, Style primaryStyle) {
      Style secondaryStyle = styleDocument.addStyle("Secondary", primaryStyle);
      StyleConstants.setFontSize(secondaryStyle, 16);
      StyleConstants.setUnderline(secondaryStyle, false);
      StyleConstants.setForeground(secondaryStyle, Color.black);
      return secondaryStyle;
  }
  public Style getStartStyle(StyledDocument styleDocument) {
      Style secondaryStyle = styleDocument.addStyle("Start", null);
      StyleConstants.setFontSize(secondaryStyle, 2);
      StyleConstants.setUnderline(secondaryStyle, false);
      StyleConstants.setForeground(secondaryStyle, Color.black);
      return secondaryStyle;
  }
  
  public void addStyledText(String user, String msg, StyledDocument msgDocument) {
      //Variable definition
      StyledDocument styleDocument = allMessages.getStyledDocument();
      Style primaryStyle           = getPrimaryStyle(styleDocument);
      Style secondaryStyle         = getSecondaryStyle(styleDocument, primaryStyle);
      ElementIterator iterator     = new ElementIterator(msgDocument);
      Element element;
      int cnt = 0;
      
      try {
        styleDocument.insertString(styleDocument.getLength(), user + ": \n", primaryStyle);
        allMessages.setCaretPosition(styleDocument.getLength());
          while ((element = iterator.next()) != null) {
            AttributeSet as = element.getAttributes();                            
            if (element.getName().equals("content")) {
                String elmStr = element.toString();
                String splElm[] = elmStr.substring(elmStr.lastIndexOf(')') + 2).split(",");
                int ini = splElm[0].trim().equals("0") ? Integer.valueOf(splElm[0].trim()) : Integer.valueOf(splElm[0].trim()) - 1;
                int fin = ini == 0 ? Integer.valueOf(splElm[1].trim()) - 1 : Integer.valueOf(splElm[1].trim());
                if(ini < msg.length() && fin <= msg.length()) {
                    styleDocument.insertString(styleDocument.getLength(), msg.substring(ini, fin) , secondaryStyle);
                    allMessages.setCaretPosition(styleDocument.getLength());
                }
            } else if (as.containsAttribute(ELEM, ICON)) {
                ImageIcon imgIc = (ImageIcon) StyleConstants.getIcon(as);
                if(imgIc.getDescription() != null) {
                    File file = new File(this.getClass().getClassLoader().getResource("chatclient/emoticons/emojis/ANIMALS/" + imgIc.getDescription()).getFile());
                    if(file.exists())imgIc = new ImageIcon(GifImageSolver.readImgFromFile(file, messages));
                }
                allMessages.insertIcon(imgIc);
            }
          }
          styleDocument.insertString(styleDocument.getLength(), "\n" , secondaryStyle);
      } catch (BadLocationException ex) {
          Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
      allMessages.setCaretPosition(styleDocument.getLength());
  }
  
  public void receiveMessage(String msg) {
      allMessages.setText(allMessages.getText() + "\n" + msg);
  }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFiles;
    private javax.swing.JTextPane allMessages;
    private javax.swing.JTextPane animalsPane;
    private javax.swing.JTextPane emojisPane;
    private javax.swing.JScrollPane emojisScrollPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane messages;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextPane userPane;
    private javax.swing.JLabel userToImage;
    // End of variables declaration//GEN-END:variables
}
