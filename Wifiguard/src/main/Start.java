package main;

import gui.GUI;
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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Can not find system LookAndFell");
        }
        GUI gui = new GUI();
        gui.setVisible(true);         
    }    
}