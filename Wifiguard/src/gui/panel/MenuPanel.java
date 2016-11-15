package gui.panel;

import gui.window.InfoWindow;
import gui.window.ManualWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import logic.FileLoader;

/**
 *  Class MenuPanel
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.1
 *@created    12/2013
 */
public class MenuPanel extends JMenuBar {
    private JMenu file;
    private JMenu help;
    private JMenuItem exit;
    private JMenuItem manual;
    private JMenuItem about;
    
    private ManualWindow manualWindow;
    private InfoWindow infoWindow;
    
    /**
    * Constcructor.
    */
    public MenuPanel(){
        
        FileLoader fileLoader = new FileLoader();
                
        file = new JMenu("File");  
        help = new JMenu("Help");
        
        exit = new JMenuItem();    
        exit.setText("Exit");
        exit.addActionListener(new Exit());
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setAccelerator (KeyStroke.getKeyStroke ("ctrl E"));
        exit.setIcon(fileLoader.iconLoader("image","Exit.png"));
        
        manual = new JMenuItem(); 
        manual.setText("Manual");
        manual.addActionListener(new Manual());
        manual.setMnemonic(KeyEvent.VK_M);
        manual.setAccelerator (KeyStroke.getKeyStroke ("ctrl M"));
        manual.setIcon(fileLoader.iconLoader("image","Manual.png"));
        
        about = new JMenuItem();  
        about.setText("About");
        about.addActionListener(new About());
        about.setMnemonic(KeyEvent.VK_A);
        about.setAccelerator (KeyStroke.getKeyStroke ("ctrl A"));
        about.setIcon(fileLoader.iconLoader("image","About.png"));
        
        file.add(exit);
        help.add(manual);
        help.add(about);
        
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);
        
        add(file);
        add(help);
    }
    
    /**
    * Private Class Exit.
    */
    private class Exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int exitQuestion = JOptionPane.showConfirmDialog
               (null, "Do you really want to exit? ", "Question: Exit application", 
                JOptionPane.YES_NO_OPTION);
            if (exitQuestion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
    
    /**
    * Private class Manual.
    */
    private class Manual implements ActionListener{
        @Override
	public void actionPerformed(ActionEvent event) {
            manualWindow = new ManualWindow();
            manualWindow.setVisible(true);
        }
    }
    
    /**
    * Private Class About.
    */
    private class About implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            infoWindow = new InfoWindow();
            infoWindow.setVisible(true);
        }
    }   
}    