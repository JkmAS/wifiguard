package gui.window;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logic.Convert;
import logic.FileLoader;
import logic.IniFileManager;

/**
 *  Class SettingsWindow
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    12/2013
 */
public class SettingsWindow {
    
    private JDialog dialog;
    private JPanel panelSettings;
    private JPanel panelText;
    private JPanel panelSet;
    private JPanel panelButton;
    private JComboBox comboBox; 
    
    private File databaseFile;
    private File deviceDatabaseFile;
    private File settingsFile;
    private FileLoader  fileLoader;
    private ArrayList<String> networkdevices = new ArrayList<>();
    private IniFileManager settings;

    /**
    * Constructor.
    */
    public SettingsWindow() {        
       fileLoader =  new FileLoader(); 
       settings = new IniFileManager("settings", "settings.ini");
        
       dialog = new JDialog();
       dialog.setLayout(new BorderLayout());
       dialog.setTitle("Settings");
       dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  
       dialog.setLocation(400, 200);
       dialog.setSize(new Dimension(300,260)); 
       dialog.setResizable(false);
       dialog.setVisible(false);       
       dialog.add(makePanelSettings(), BorderLayout.CENTER);
       dialog.add(makePanelButton(), BorderLayout.SOUTH);
       
       databaseFile = new File(fileLoader.fileLoader("data/database","database.xml"));
       deviceDatabaseFile = new File(fileLoader.fileLoader("data/database","devicedatabase.ini"));
       settingsFile = new File(fileLoader.fileLoader("settings", "settings.ini"));
    }   
    
    /**
    * Setting of visibility
    * @param b 
    */
    public void setVisible(boolean b) {
       dialog.setVisible(b);
    } 
    
    /**
    * Make a panell with settings
    * @return panelSettings 
    */
    public JPanel makePanelSettings() {
       panelSettings = new JPanel (new FlowLayout(FlowLayout.LEFT));
       panelSettings.setBorder(BorderFactory.createTitledBorder("Settings:"));
       
       panelText = new JPanel (new GridLayout(5, 1, 10, 15));
       panelSet = new JPanel (new GridLayout(5, 1, 10, 2));
       
       JLabel historyShowLabel = new JLabel("Show history:");    
       JButton showHistoryButton = new JButton(" Show ");
       showHistoryButton.addActionListener(new Show());
       showHistoryButton.setIcon(fileLoader.iconLoader("image","History.png"));
       showHistoryButton.setFocusPainted(false);
       
       JLabel historyDeleteLabel = new JLabel("Delete history:");    
       JButton historyButton = new JButton("Delete");
       historyButton.addActionListener(new Delete());
       historyButton.setIcon(fileLoader.iconLoader("image","Delete.png"));
       historyButton.setFocusPainted(false);
       
       JLabel userSettingsLabel = new JLabel("User settings:");   
       JButton userSettingsButton = new JButton("Delete");
       userSettingsButton.addActionListener(new DeleteUserSettings());
       userSettingsButton.setIcon(fileLoader.iconLoader("image","Delete.png"));
       userSettingsButton.setFocusPainted(false); 
       
       JLabel networkDeviceLabel = new JLabel("Network device:");   
       comboBox = new JComboBox();
       findNetworkDevices();
       if(networkdevices.isEmpty()){
           comboBox.setEnabled(false);
       } else {
            for (int i = 0; i < networkdevices.size(); i++){
               comboBox.addItem(networkdevices.get(i));
            }
            if(settings.read("NetworkDevice") != null){
               comboBox.setSelectedItem(settings.read("NetworkDevice"));
            } 
            comboBox.addActionListener(new SelectionNetworkDevice());
       }  
       
       JLabel arpScanLabel = new JLabel("Arp-Scan location:");    
       JButton arpScanButton = new JButton("Search");
       arpScanButton.addActionListener(new SetArpScan());
       arpScanButton.setIcon(fileLoader.iconLoader("image","Search.png"));
       arpScanButton.setFocusPainted(false); 
       
       panelText.add(historyShowLabel); 
       panelText.add(historyDeleteLabel);
       panelText.add(userSettingsLabel);
       panelText.add(networkDeviceLabel);
       panelText.add(arpScanLabel);
       panelSet.add(showHistoryButton);
       panelSet.add(historyButton);
       panelSet.add(userSettingsButton);
       panelSet.add(comboBox);
       panelSet.add(arpScanButton);
       panelSettings.add(panelText);
       panelSettings.add(panelSet);
        
       return panelSettings;
    }
    
    /**
    * Make panel with buttons
    * @return panelButton 
    */
    public JPanel makePanelButton() {
       panelButton = new JPanel (new FlowLayout(FlowLayout.RIGHT));
       
       JButton stornoButton = new JButton("Close");       
       stornoButton.addActionListener(new Storno());
       stornoButton.setIcon(fileLoader.iconLoader("image","Return.png"));
       stornoButton.setFocusPainted(false);       
       
       panelButton.add(stornoButton);
         
       return panelButton;
    }    
    
    /**
    * Find all network devices
    */
    public void findNetworkDevices() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!networkInterface.getDisplayName().equals("lo")){
                    networkdevices.add(networkInterface.getDisplayName());
                }
            }
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(null,"Scanning of NetworkDevices failed",
            "Error",JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    /**
    * Private class Storno.
    */
    private class Storno implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
             dialog.setVisible(false);        
        }
    }
    
    /**
    * Private class Delete.
    */
    private class Delete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(databaseFile.exists()){
                databaseFile.delete();  
                JOptionPane.showMessageDialog(null,"Database deleted",
                "Confirmation",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"Can not delete database, does not exist",
                "Information",JOptionPane.INFORMATION_MESSAGE);
            }    
        }
    }   
    
    /**
    * Private class Show.
    */
    private class Show implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(databaseFile.exists()){ 
                try {
                    File HTMLFile = new File(fileLoader.fileLoader("data/convert", "devices_list.html"));
                    if(HTMLFile.exists()){
                        Convert convert = new Convert();
                        convert.convertXMLtoHTML(databaseFile);
                        Desktop.getDesktop().open(HTMLFile);
                    } else{
                        Desktop.getDesktop().open(databaseFile);
                    }    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Database does not exist",
                    "Information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Database does not exist",
                "Information",JOptionPane.INFORMATION_MESSAGE);
            } 
        }     
    }
    
    /**
    * Private class DeleteUserSettings.
    */
    private class DeleteUserSettings implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int deleteQuestion = JOptionPane.showConfirmDialog
               (null, "Do you really want to delete all settings?", "Question: Delete user settings", 
                JOptionPane.YES_NO_OPTION);
            
            if (deleteQuestion == JOptionPane.YES_OPTION) { 
                boolean delDatabase = false;
                boolean delSettings = false;
                if (deviceDatabaseFile.exists()){
                    deviceDatabaseFile.delete();  
                    delDatabase = true;
                }
                if (settingsFile.exists()){
                    settingsFile.delete();
                    delSettings = true;
                }
                if(delDatabase == false || delSettings == false){
                    JOptionPane.showMessageDialog(null,"Can not delete setting, does not exist",
                    "Information",JOptionPane.INFORMATION_MESSAGE);
                }
                if(delDatabase == true || delDatabase == true){
                    JOptionPane.showMessageDialog(null,"Settings deleted",
                    "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                }
            }    
        }
    }
    
    /**
    * Private class SelectionNetworkDevice.
    */
    private class SelectionNetworkDevice implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {            
            comboBox = (JComboBox)event.getSource();
            String selected = (String)comboBox.getSelectedItem();
            settings.write("NetworkDevice", selected);
            JOptionPane.showMessageDialog(null,"Network device is set to "+selected,
            "Confirmation",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
    * Private class SetArpScan.
    */
    private class SetArpScan implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) { 
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                File selected = fileChooser.getSelectedFile();
                settings.write("ArpScan", selected.getAbsolutePath());
                JOptionPane.showMessageDialog(null,"Location of Arp-Scan is "+selected.getAbsolutePath(),
                "Confirmation",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}