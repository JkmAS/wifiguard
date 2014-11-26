package gui.window;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logic.FileLoader;

/**
 *  Class InfoWindow
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    12/2013
 */
public class InfoWindow{
    
    private JDialog dialog;
    private JPanel panel;
    private JLabel icon;
    private JLabel app;
    private JLabel version;
    private JLabel website;
    private JLabel author;
    private JLabel contact;
    private JButton button;
    
    /**
    * Constructor.
    */
    public InfoWindow() {
        FileLoader fileLoader = new FileLoader();
        
        dialog = new JDialog();   
        dialog.setTitle("About application");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);        
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.PAGE_AXIS));
        
        icon = new JLabel();
        icon.setIcon(fileLoader.iconLoader("image","Wifi_Guard.png"));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        app = new JLabel();
        app.setText("Wifi Guard");
        Font appText = new Font("default", Font.BOLD, 20);
        app.setFont(appText);
        app.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        version = new JLabel();
        version.setText("1.0.0");
        Font versionText = new Font("default", Font.BOLD, 17);
        version.setFont(versionText);
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font panelText = new Font ("default", Font.BOLD, 13);
        
        website = new JLabel();
        website.setText("<html> Website: <a href=\"\">www.WifiGuard.cz</a></html>");
        website.setFont(panelText);
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.setAlignmentX(Component.CENTER_ALIGNMENT);
                      
        author = new JLabel();
        author.setText("Author: James \"JkmAS\" Mejstrik");
        author.setFont(panelText);
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contact = new JLabel(); 
        contact.setText("<html> Contact: <a href=\"\">JkmASg@gmail.com</a></html>");
        contact.setFont(panelText);
        contact.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contact.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(website);
        panel.add(author);
        panel.add(contact);
        
        button = new JButton();
        button.setText("Close");
        button.setFocusPainted(false);         
        button.setIcon(fileLoader.iconLoader("image","Return.png"));
        button.addActionListener(new ListenerClose()); 
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        dialog.add(Box.createRigidArea(new Dimension(0,5)));
        dialog.add(icon);
        dialog.add(Box.createRigidArea(new Dimension(0,5)));
        dialog.add(app);
        dialog.add(Box.createRigidArea(new Dimension(0,5)));
        dialog.add(version);
        dialog.add(Box.createRigidArea(new Dimension(0,10)));
        dialog.add(panel);
        dialog.add(button); 
        dialog.add(Box.createRigidArea(new Dimension(0,5)));
        dialog.setLocation(400, 200);
        dialog.setSize(330,260); 
        dialog.setResizable(false);
        dialog.setVisible(false);
        sendMail(contact);
        goWebsite(website);
    }
    
    /**
    * Setting of visibility
    * @param b 
    */
    public void setVisible(boolean b) {
        dialog.setLocation(400, 200);
        dialog.setVisible(b);
    } 

    /**
    * Open the web browser
    */
    private void goWebsite(JLabel website) {
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {                
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://www.WifiGuard.cz"));
                    } catch (URISyntaxException ex) {     
                        JOptionPane.showMessageDialog(null,"URI Syntax Error",
                        "Error URI",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                }                
            }
        });
    }

    /**
    * Open the mail client
    */
    private void sendMail(JLabel contact) {
        contact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {                
                try {
                    try {
                        Desktop.getDesktop().mail(new URI("mailto:JkmASg@gmail.com"));
                    } catch (URISyntaxException ex) {
                        JOptionPane.showMessageDialog(null,"URI Syntax Error",
                        "Error URI",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                }                
            }
        });
    }
    
    /**
    * Private class ListenerClose.
    */
    private class ListenerClose implements ActionListener{
        @Override
	public void actionPerformed(ActionEvent event) {
            dialog.setVisible(false);
            dialog.setLocation(400, 200);
        }
    }
}