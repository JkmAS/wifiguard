package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *  Class IniFileManager
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    11/2014
 */
public class IniFileManager {
    
    private FileLoader fileLoader; 
    private String path;
    private File propertiesFile ;
    private Properties properties;
    
    /**
    * Constructor.
     * @param dir
     * @param file
    */
    public IniFileManager(String dir, String file){
        fileLoader =  new FileLoader();
        path = fileLoader.fileLoader(dir, file);
        propertiesFile = new File(path);
        properties = new Properties();
    }
    /**
    * Read the INI file
    * @param name
    * @return value
    */
    public String read(String name){
        if(propertiesFile.exists()){
           try{
               InputStream propertiesInput = new FileInputStream(path);
               properties.load(propertiesInput);
               String value = properties.getProperty(name);
               propertiesInput.close();
               return value;
           } catch (IOException ex){
               JOptionPane.showMessageDialog(null,"Failed to read ini file",
               "Error",JOptionPane.ERROR_MESSAGE);
           }
        } 
        return null;
    }
    
    /**
    * Write to the INI file
    * @param name
    * @param value
    */
    public void write(String name, String value){
        if(!propertiesFile.exists()){
            try {                                            
                propertiesFile.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Failed to create ini file",
                "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        try{
            InputStream propertiesInput = new FileInputStream(path);
            properties.load(propertiesInput);
            properties.put(name, value);
            propertiesInput.close();
            FileOutputStream out = new FileOutputStream(path);
            properties.store(out,null);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null,"Saving of the name failed",
            "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
