package gui.panel;

import gui.window.SettingsWindow;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logic.FileLoader;
import logic.IniFileManager;
import logic.Observer;
import logic.Scanner;
import logic.Subject;

/**
 *  Class ButtonPanel
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.1
 *@created    12/2013
 */
public class ButtonPanel extends JPanel implements Subject{
    
    private SettingsWindow settingsWindow;
    private FileLoader fileLoader;    
    private ArrayList<Observer> observers = new ArrayList<>();
    private Thread thread;
    private boolean threadStatus;
            
    /**
    * Constructor.
    */
    public ButtonPanel(){
        fileLoader = new FileLoader();
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));                        
        add(makeButton("Start","image","Start.png","Start_Change.png",new ListenerStart()));
        add(makeButton("Stop","image","Stop.png","Stop_Change.png", new ListenerStop()));
        add(makeButton("Settings","image","Settings.png","Settings_Change.png", new ListenerSettings())); 
    }
    
    /**
    * Setting of buttons
    * @param name 
    * @param folder 
    * @param file 
    * @param nameChange 
    * @param listener 
    * @return button
    */
    public JButton makeButton (String name, String folder, String file, String nameChange, ActionListener listener){
        JButton button = new JButton();
        button.setIcon(fileLoader.iconLoader(folder, file));
        button.setRolloverIcon(fileLoader.iconLoader(folder, nameChange));
        button.setPressedIcon(fileLoader.iconLoader(folder, nameChange));
        button.addActionListener(listener); 
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);  
        button.setMaximumSize(new Dimension (50,45)); 
        button.setToolTipText(name);
        return button;
    }    
    
    /**
    * Private class ListenerStart.
    */
    private class ListenerStart implements ActionListener{
        @Override
	public void actionPerformed(ActionEvent event) { 
            IniFileManager settings = new IniFileManager("settings", "settings.ini");
            String arpScanDir;
            if(settings.read("ArpScan") != null){
               arpScanDir = settings.read("ArpScan"); 
            } else {
                arpScanDir = "/usr/bin/arp-scan";
            }
            File arpScanner = new File(arpScanDir);
            if (!arpScanner.exists() || !arpScanner.canExecute()) {
                JOptionPane.showMessageDialog(null,"Please install Arp-Scan or set the correct location of Arp-Scan",
                "Error",JOptionPane.ERROR_MESSAGE);
            }
            else {            
                thread = new Thread("ThreadScanner"){
                    @Override
                    public void run() { 
                        threadStatus = true;
                        notifyObserversProgressBar(true);
                        Scanner scanner = new Scanner();
                        notifyObserversTable(); 
                        notifyObserversProgressBar(false);
                    }
                };
                thread.start();
            }
        }
    }
    
    /**
    * Private class ListenerStop.
    */
    private class ListenerStop implements ActionListener{
        @Override
	public void actionPerformed(ActionEvent event) {
            if (threadStatus){
                thread.stop();
                notifyObserversProgressBar(false);
            }            
        }
    }
    
    /**
    * Private class ListenerSettings.
    */
    private class ListenerSettings implements ActionListener{
        @Override
	public void actionPerformed(ActionEvent event) { 
            settingsWindow = new SettingsWindow();
            settingsWindow.setVisible(true);
        }
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserversTable() {
        for (Observer observer : observers) {
            observer.updateTable();
        }
    }
    
    @Override
    public void notifyObserversProgressBar(boolean status) {
        for (Observer observer : observers) {
            observer.updateProgressBar(status);
        }
    }
}
