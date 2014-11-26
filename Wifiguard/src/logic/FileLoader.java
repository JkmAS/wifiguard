package logic;

import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *  Class FileLoader
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    12/2013
 */
public class FileLoader {
    
    /**
    * Constructor.
    */
    public FileLoader(){  
    }
    
    /**
    * Load a icon
    * @param folder
    * @param file
    * @return icon
    */
    public ImageIcon iconLoader(String folder, String file){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(folder + File.separator + file));   
        return icon;
    }
    
    /**
    * Load a file
    * [only for the development]
    * @param folder
    * @param file
    * @return path
    */
    public String fileLoader(String folder, String file){
        String path = "";
        try {       
            String canonicalPath = new File(".").getCanonicalPath();  
            path = canonicalPath + File.separator + "src" + File.separator + folder + File.separator + file;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Can not read path to folder",
            "Error reading path" ,JOptionPane.ERROR_MESSAGE);
        }
        return path;
    }  
    /**
    * Load a file
    * [only for users]
     * @param folder
     * @param file
     * @return path
    */
//    public String fileLoader(String folder, String file){
//        String path = System.getProperty("user.home") + File.separator + "wifiguard" + File.separator + folder + File.separator + file;
//        return path;
//    }  
}
