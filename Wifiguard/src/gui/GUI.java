package gui;

import gui.panel.ButtonPanel;
import gui.panel.InfoPanel;
import gui.panel.MenuPanel;
import gui.panel.TablePanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *  Class GUI
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    12/2013
 */
public class GUI {
    
    private JFrame frame;
    private MenuPanel jMenuPanel;
    private ButtonPanel buttonPanel;
    private TablePanel tablePanel;
    private InfoPanel infoPanel;
    
    /**
    * Constructor.
    */
    public GUI(){
        initMenu();  
        init();
    }   
    
    /**
    * Creating of GUI app
    */
    private void init() {
        frame = new JFrame("Wifi Guard");
        frame.setJMenuBar(jMenuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        tablePanel = new TablePanel();
        buttonPanel = new ButtonPanel();
        buttonPanel.registerObserver(tablePanel);
        infoPanel = new InfoPanel(); 
        buttonPanel.registerObserver(infoPanel);
        
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(infoPanel, BorderLayout.SOUTH);
         
        frame.pack();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Wifi_Guard.png")));
        frame.setSize(600,265);
        frame.setLocation(150, 200);
        frame.setResizable(false);
    }

    /**
    * Creating of menu
    */
    private void initMenu() {
        jMenuPanel = new MenuPanel();
    }
    
    /**
    * Setting of visibility
    * @param b 
    */
    public void setVisible(boolean b) {
        frame.setVisible(true);
    } 
}
