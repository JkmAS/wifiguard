package main;

import gui.GUI;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *  Class Start
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    12/2013
 */
public class Start {
    /**
    * Launching the application
    * @param args
    */
     public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null,"Can not set GTK LookAndFeel",
            "Error GTK LookAndFeel",JOptionPane.ERROR_MESSAGE);
        }
        GUI gui = new GUI();
        gui.setVisible(true);         
    }    
}