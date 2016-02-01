/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.emoticons;

import chatclient.StaticData;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ESa10969
 */
public class EmojiIcons {
    private JTextPane emojisPane;
    private JTextPane messages;
    private String    path;
    private int       numEmojis;
    
    public EmojiIcons(JTextPane emojisPane, JTextPane messages, String path, int numEmojis) {
        this.emojisPane = emojisPane;
        this.messages   = messages;
        this.path       = path;
        this.numEmojis  = numEmojis;
    }
    
    public void addEmojisToPanel(String ext) {
        //Variable definition;
        JLabel emojiLab;
        InputStream in;
        Image origIcon = null;
        StyledDocument styleDocument = emojisPane.getStyledDocument();
        StyleContext context         = new StyleContext();
        Style labelStyle             = context.getStyle(StyleContext.DEFAULT_STYLE);
        Style secondaryStyle         = styleDocument.addStyle("Secondary", null);
        String resourceName;
        int panelWidth = emojisPane.getWidth();
        int ocup = 24;
        
        for(int i = 1; i <= numEmojis; i++) {
            emojiLab = new JLabel();
            resourceName = path + i + "." + ext;
            in = this.getClass().getResourceAsStream(resourceName);
            try {
                origIcon = ImageIO.read(in);
                emojiLab.setName(resourceName);
                ImageIcon orig = new ImageIcon(origIcon.getScaledInstance(24, 24, 0));
                orig.setDescription(resourceName);
                emojiLab.setIcon(orig);
                if(ext.equals("png")) {
                    addEventToEmoji(emojiLab, origIcon, ext, null);
                } else {
                    File file = new File(this.getClass().getClassLoader().getResource("chatclient/emoticons/" + path + i + "." + ext).getFile());
                    ImageIcon img = new ImageIcon(GifImageSolver.readImgFromFile(file, messages));
                    img.setDescription(file.getName());
                    addEventToEmoji(emojiLab, origIcon, ext, img);
                }

                StyleConstants.setComponent(labelStyle, emojiLab);
                StyleConstants.setFontSize(secondaryStyle, 16);
                StyleConstants.setUnderline(secondaryStyle, false);
                StyleConstants.setForeground(secondaryStyle, Color.black);
                
                styleDocument.insertString(styleDocument.getLength(), resourceName, labelStyle);
                ocup += 24;
                if(ocup > panelWidth) {
                    styleDocument.insertString(styleDocument.getLength(), "\n", secondaryStyle);
                    ocup = 24;
                }
            } catch (IOException ex) {
                Logger.getLogger(EmojiIcons.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(EmojiIcons.class.getName()).log(Level.SEVERE, null, ex);
            }
            emojisPane.add(emojiLab);
        }
    }
    
    private void addEventToEmoji(JLabel emojiLab, Image origIcon, String ext, ImageIcon anim) {
        emojiLab.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e) {
              if(ext.equals("png")) {
                Image img = origIcon.getScaledInstance(24, 24, 0);
                emojiLab.setIcon(new ImageIcon(img));
                messages.insertIcon(emojiLab.getIcon());  
              } else {
                  messages.insertIcon(anim);
              }
                          
          }
          @Override
          public void mouseEntered(MouseEvent e) {
              emojiLab.setCursor(new Cursor(Cursor.HAND_CURSOR));
              if(ext.equals("png")) {
                Image img = origIcon.getScaledInstance(36, 36, 0);
                emojiLab.setIcon(new ImageIcon(img));
              } else {
                  emojiLab.setIcon(anim);
              }
          }
          @Override
          public void mouseExited(MouseEvent e) {
            Image img = origIcon.getScaledInstance(24, 24, 0);
            emojiLab.setIcon(new ImageIcon(img));
          }
        });
    }
}
