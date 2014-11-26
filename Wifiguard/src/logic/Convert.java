package logic;

import java.io.*;
import javax.swing.JOptionPane;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 *  Class Convert
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    05/2014
 */
public class Convert {
    
    /**
    * Constructor.
    */
    public Convert(){
    }
    
    /**
    * Converting XML file to HTML
    * @param file
    */
    public void convertXMLtoHTML(File file){
        try{
            FileLoader fileLoader = new FileLoader();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Source XSLDoc = new StreamSource(fileLoader.fileLoader("data/convert","devices_list.xsl"));
            Source XMLDoc = new StreamSource(file);

            String outputFileName = fileLoader.fileLoader("data/convert","devices_list.html");
            OutputStream HTMLFile = new FileOutputStream(outputFileName);

            Transformer transformer = transformerFactory.newTransformer(XSLDoc);
            transformer.transform(XMLDoc, new StreamResult(HTMLFile));
            
        } catch(FileNotFoundException | TransformerException e) {
            JOptionPane.showMessageDialog(null,"Can not convert XML to HTML",
            "Error during converting" ,JOptionPane.ERROR_MESSAGE);
        }
    }
}