package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *  Class Scanner
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.1
 *@created    12/2013
 */
public class Scanner{
    
    private Database database;
    private IniFileManager nameDatabase;
    private IniFileManager settingsFile;
    private ArrayList<Device> devicesList;
    
    /**
    * Constructor.
    */
    public Scanner(){        
        database = new Database();
        nameDatabase = new IniFileManager("data/database", "devicedatabase.ini");
        settingsFile = new IniFileManager("settings", "settings.ini");
        devicesList = new ArrayList<>();        
        try {
            communicationCL();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error in communicating with the command line",
                        "Error Command Line",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    * Communicates with a command line
    * @throws java.io.IOException
    */
    public void communicationCL() throws IOException{   
        String interfaceNetDev = settingsFile.read("NetworkDevice");
        if(interfaceNetDev == null){
            interfaceNetDev = "wlan0";
        }
        
        Process process = Runtime.getRuntime().exec("gksudo -- arp-scan --interface="+interfaceNetDev+" --localnet");                      
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        int lineCounter = 0;
        int lineNumber = 0;
        boolean dataExist = false;
        String readline;
        while ((readline = reader.readLine()) != null) {
            lineCounter++; 
            Matcher matcher = Pattern.compile("(?:\\d{1,3}\\.){3}\\d{1,3}").matcher(readline);
            if (matcher.find()) { 
                dataExist = true;
                lineNumber++;
                String[] splits = readline.replace("\t", ";").split(";"); 
                Device device = new Device(lineNumber, splits[0], splits[1], nameDatabase.read(splits[1]), splits[2]);
                devicesList.add(device);
            }
        }
        if (dataExist == true){
            database.makeDatabase(devicesList);
        }    
        else{
            JOptionPane.showMessageDialog(null,"Arp-Scan did not find data or you cancel the scanning.",
            "Information",JOptionPane.INFORMATION_MESSAGE);
        }
    } 
}
