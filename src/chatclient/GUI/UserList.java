/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.GUI;

import chatclient.StaticData;
import chatclient.threads.send.SendProfileImageThread;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ESa10969
 */
public class UserList extends javax.swing.JFrame {
  private static final long serialVersionUID = 1L;
  private final JFrame frame = this;
  private final String user;
  
  public JTable getUserTable() {
    return userTable;
  }
  public UserList(String[] userList, String user) throws RemoteException {
    initComponents();
      try {
          this.setIconImage(ImageIO.read(StaticData.framIcon));
      } catch (IOException ex) {
          Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
      }
    userPane.setText(user);
    setTitle("iChat V1.0");
    this.setLocationRelativeTo(null);
    this.user = user;
    setTableModel();
    setTable(userList);
    StaticData.userName = user;
    StaticData.chatWindowsMap = new HashMap<>();
    addEvents();
    StaticData.guiList = this;
    StaticData.server.getProfileImage(StaticData.userName, StaticData.cliRem);
  }
  
  public void addProfileImage(byte[] bytes) {
      //Variable definition
      ImageIcon img;
      
      
      if(bytes != null) {
        InputStream fis = new ByteArrayInputStream(bytes);
          try {
              Image origImg = ImageIO.read(fis);
              Image resImg  = origImg.getScaledInstance(profilePhoto.getWidth(), profilePhoto.getHeight(), 0);
              img = new ImageIcon(resImg);
              profilePhoto.setIcon(img);
          } catch (IOException ex) {
              Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }
  
  private ImageIcon loadStateIcons(String imgName) {
    return new ImageIcon(this.getClass().getResource("img/" + imgName.toLowerCase() + ".png"));
  }
  
  private void addEvents() {
    userTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent me) {
        JTable table =(JTable) me.getSource();
        Point p = me.getPoint();
        int row = table.rowAtPoint(p);
        String userTo = (String)userTable.getModel().getValueAt(row, 0);
        
        if (me.getClickCount() == 2 && StaticData.chatWindowsMap.get(userTo) == null) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                StaticData.chatWindowsMap.put(userTo, new ChatWindow(userTo));
            } catch (IOException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    StaticData.server.getProfileImage(userTo, StaticData.cliRem);
                } catch (RemoteException ex) {
                    Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
                }
                StaticData.chatWindowsMap.get(userTo).setVisible(true);
            });
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } else if (StaticData.chatWindowsMap.get(userTo) != null){
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            java.awt.EventQueue.invokeLater(() -> {
                StaticData.chatWindowsMap.get(userTo).setVisible(true);
            });
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
      }
    });
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
          int confirm = JOptionPane.showConfirmDialog(frame, 
                                    "Are you sure to close this window?", "Really Closing?", 
                                    JOptionPane.YES_NO_OPTION);
          if(confirm == JOptionPane.YES_OPTION) {
              disconnect();
              System.exit(0);
          }
      }
    });
  }
  
  private boolean disconnect() {
      try {
          StaticData.server.logoutUser(StaticData.userName);
      } catch (RemoteException ex) {
          Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
      }
    return true;
  }
  
  private void setTableModel() {
    DefaultTableModel model = new DefaultTableModel(0, 2) {
      @Override
      public Class<?> getColumnClass(int column) {
          switch(column) {
              case 0: return String.class;
              case 1: return ImageIcon.class;
              default: return Object.class;
          }
      }
       @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
    };
    model.setColumnIdentifiers(new Object[]{"User", "State"});
    userTable.setModel(model);
    userTable.getColumnModel().getColumn(0).setPreferredWidth(125);
    userTable.getColumnModel().getColumn(1).setPreferredWidth(25);
  }
    
  public final void setTable(String[] userList) {
    //Variable definition
    DefaultTableModel model = (DefaultTableModel) userTable.getModel();
    
    while(model.getRowCount() > 0) {
      model.removeRow(0);
    }
    if(userList != null) {
      for(int i = 0; i < userList.length; i++) {
        if(!userList[i].equals(StaticData.userName)) {
          model.addRow(new Object[]{userList[i], new ImageIcon(this.getClass().getResource("img/available.png"))});
        }
      }
    }
  }
  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        profilePhoto = new javax.swing.JLabel();
        userName = new javax.swing.JScrollPane();
        userPane = new javax.swing.JTextPane();
        userState = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("iChat 1.0");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        profilePhoto.setBackground(new java.awt.Color(0, 153, 255));
        profilePhoto.setOpaque(true);
        profilePhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePhotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(profilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(profilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        userPane.setFont(new java.awt.Font("Tempus Sans ITC", 1, 11)); // NOI18N
        userPane.setForeground(new java.awt.Color(0, 153, 255));
        userPane.setEnabled(false);
        userName.setViewportView(userPane);

        userState.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        userState.setForeground(new java.awt.Color(0, 153, 255));
        userState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Away", "Unavailable" }));
        userState.setOpaque(false);
        userState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                userStateItemStateChanged(evt);
            }
        });

        userTable.setBackground(java.awt.SystemColor.controlHighlight);
        userTable.setFont(new java.awt.Font("Tempus Sans ITC", 1, 10)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Connected"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setOpaque(false);
        jScrollPane1.setViewportView(userTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userName)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(userState, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 77, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(userState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void userStateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_userStateItemStateChanged
    //Variable definition
    /*PrintWriter sender;
    
    try {
      sender = new PrintWriter(s.getOutputStream());
      
      sender.println("CHANGE;" + user + ";" + userState.getSelectedItem());
      sender.flush();
    } catch(IOException ex) {
      System.out.println("Error attepting to login\n" + ex);
    } */
  }//GEN-LAST:event_userStateItemStateChanged

    private void profilePhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePhotoMouseClicked
        //Variable definition
        JFileChooser chooser = new JFileChooser();
        File image;
        int returnVal;
        ImageIcon img;

        returnVal = chooser.showOpenDialog(this);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            Thread t;
            SendProfileImageThread sender;
            image = chooser.getSelectedFile();
            
            sender = new SendProfileImageThread(image, StaticData.userName);
            t = new Thread(sender);
            t.start();
        }
    }//GEN-LAST:event_profilePhotoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel profilePhoto;
    private javax.swing.JScrollPane userName;
    private javax.swing.JTextPane userPane;
    private javax.swing.JComboBox userState;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
