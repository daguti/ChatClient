/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.GUI;

import chat.remote.interfaces.ClientRemoteItfz;
import chat.remote.interfaces.ServerRemoteItfz;
import chatclient.StaticData;
import chatclient.remote.ClientRemote;
import java.awt.Color;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ESa10969
 */
public class Login extends javax.swing.JFrame {
  private static final long serialVersionUID = 1L;
  private String[] userList;
  private ClientRemoteItfz cli;
  /**
   * Creates new form ClientGUI
   */
  public Login() {
    initComponents();
    setTitle("iChat V1.0");
    this.setLocationRelativeTo(null);
      try {
          //Registry registry = LocateRegistry.getRegistry("10.100.167.254");
          //StaticData.server = (ServerRemoteItfz) registry.lookup("ChatServer");
          StaticData.server = (ServerRemoteItfz) Naming.lookup("rmi://localhost:1099/ChatServer");
      } catch (NotBoundException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      } catch (RemoteException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      } catch (MalformedURLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passTxt = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        singnIn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("iChat 1.0");

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario:");

        userTxt.setFont(new java.awt.Font("Pristina", 0, 14)); // NOI18N
        userTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTxtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña:");

        login.setFont(new java.awt.Font("Tempus Sans ITC", 1, 11)); // NOI18N
        login.setText("Login");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });

        singnIn.setFont(new java.awt.Font("Tempus Sans ITC", 1, 11)); // NOI18N
        singnIn.setText("SingnIn");
        singnIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                singnInMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(40, 40, 40)
                                .addComponent(userTxt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(passTxt)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(50, 50, 50))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(login)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(singnIn)
                                .addGap(39, 39, 39))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login)
                    .addComponent(singnIn))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void userTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTxtActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_userTxtActionPerformed

  private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
    //Variable definition
    String user;
    String pass = "";
    
    try {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        user = userTxt.getText();
        if(user == null && user.equals("")) {
          JOptionPane.showMessageDialog(this, "User can't be empty", "An error message",
                                      JOptionPane.ERROR_MESSAGE);
          userTxt.setBackground(Color.red);
          return;
        }
        for(char c : passTxt.getPassword()) {
          pass += c;
        }
        if(pass.equals("")) {
          JOptionPane.showMessageDialog(this, "Password can't be empty", "An error message",
                                      JOptionPane.ERROR_MESSAGE);
          passTxt.setBackground(Color.red);
          return;
        }
        cli = new ClientRemote(user);

        if(StaticData.server.loginUser(user, pass, cli)) {
          int i = 0;
          StaticData.cliRem = cli;
          StaticData.usersMap = StaticData.server.getConnectedUsers();
          userList = new String[StaticData.usersMap.size()];

          for(String userName : StaticData.usersMap.keySet()) {
              userList[i] = userName;
              i++;
          }
          StaticData.userName = user;
          StaticData.guiList = new UserList(userList, user);
          java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
              StaticData.guiList.setVisible(true);
            }
          });
          this.dispose();
        } else {
          userTxt.setBackground(Color.red);
          passTxt.setBackground(Color.red);
          setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
          JOptionPane.showMessageDialog(this, "Incorrect UserName or Password", "An error message",
                                      JOptionPane.ERROR_MESSAGE);
        }
    } catch(RemoteException ex) {
        System.out.println("Error durante el login. \n" + ex);
    }
  }//GEN-LAST:event_loginMouseClicked

  private void singnInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_singnInMouseClicked
    java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
          new RegisterForm().setVisible(true);
        }
      });
      this.dispose();
  }//GEN-LAST:event_singnInMouseClicked

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Login().setVisible(true);
      }
    });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField passTxt;
    private javax.swing.JButton singnIn;
    private javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}
