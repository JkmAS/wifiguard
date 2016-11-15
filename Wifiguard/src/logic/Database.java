package logic;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 *  Class Database
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.1
 *@created    05/2014
 */
public class Database {
    
    private FileLoader fileLoader;
    private XMLInputFactory XMLInputFactory;
    private XMLOutputFactory XMLOutputFactory;
    
    /**
    * Constructor.
    */
    public Database() {  
    }  
    
    /**
    * Make a XML database
    * @param devices
    */
    public void makeDatabase(ArrayList<Device> devices){ 
        
        fileLoader = new FileLoader();      
        XMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter XMLSteamWriter = null;     
        try{ 
            XMLSteamWriter = new IndentingXMLStreamWriter(XMLOutputFactory.createXMLStreamWriter(new FileWriter(fileLoader.fileLoader("data/database","database.xml"))));
            XMLSteamWriter.writeStartDocument();
            XMLSteamWriter.writeStartElement("devices");
                XMLSteamWriter.writeAttribute("xmlns", "http://www.wifiguard.cz");
            
            XMLSteamWriter.writeStartElement("time");
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                XMLSteamWriter.writeCharacters(dateFormat.format(date));
            XMLSteamWriter.writeEndElement();
            
            for (Device device : devices){
                XMLSteamWriter.writeStartElement("device");
                    XMLSteamWriter.writeAttribute("no.", Integer.toString(device.getID()));
                        XMLSteamWriter.writeStartElement("ip_address");
                        XMLSteamWriter.writeCharacters(device.getIpAddress());
                    XMLSteamWriter.writeEndElement();
                        XMLSteamWriter.writeStartElement("mac_address");
                        XMLSteamWriter.writeCharacters(device.getMacAddress());
                    XMLSteamWriter.writeEndElement();
                        XMLSteamWriter.writeStartElement("device_name");
                        XMLSteamWriter.writeCharacters(device.getDeviceName());
                    XMLSteamWriter.writeEndElement();
                        XMLSteamWriter.writeStartElement("information");
                        XMLSteamWriter.writeCharacters(device.getInformation());
                    XMLSteamWriter.writeEndElement();
                XMLSteamWriter.writeEndElement();
            }
            XMLSteamWriter.writeEndElement();
            XMLSteamWriter.writeEndDocument();
            XMLSteamWriter.flush();
            
        } catch (IOException | XMLStreamException e){
            JOptionPane.showMessageDialog(null,"Can not write the obtained data",
            "Error writing to database",JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                if (XMLSteamWriter != null){
                    XMLSteamWriter.close();
                }
            } catch (XMLStreamException e){
                JOptionPane.showMessageDialog(null,"Can not close database file",
                "Error closing database file" ,JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
    * Read XML database
    * @return devices
    */
    public ArrayList<Device> readDatabase(){
             
        fileLoader = new FileLoader();
        XMLInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader XMLStreamReader = null;
        ArrayList<Device> devices = new ArrayList<>();
        try{
            XMLStreamReader = XMLInputFactory.createXMLStreamReader(new FileReader(fileLoader.fileLoader("data/database","database.xml")));
            int id = 0;
            String ipAddress = "";
            String macAddress = "";
            String deviceName = "";
            String information = "";
            String element = "";
            while(XMLStreamReader.hasNext()){
                if (XMLStreamReader.getEventType() == XMLStreamConstants.START_ELEMENT){
                    element = XMLStreamReader.getName().getLocalPart();
                    if (element.equals("device")){
                        id = Integer.parseInt(XMLStreamReader.getAttributeValue(0));
                    }
                }
                else if (XMLStreamReader.getEventType() == XMLStreamConstants.CHARACTERS){
                    switch (element){
                        case "ip_address":
                                ipAddress = XMLStreamReader.getText();
                                break;
                        case "mac_address":
                                macAddress = XMLStreamReader.getText();
                                break;
                        case "device_name":
                                deviceName = XMLStreamReader.getText();
                                break;  
                        case "information":
                                information = XMLStreamReader.getText();
                                break;      
                    }
                    element = "";
                }
                else if ((XMLStreamReader.getEventType() == XMLStreamConstants.END_ELEMENT)){
                    if ((XMLStreamReader.getName().getLocalPart().equals("device"))){
                            devices.add(new Device(id, ipAddress, macAddress, deviceName, information));
                    }
                }
                XMLStreamReader.next();
            }
        }
        catch (FileNotFoundException | NumberFormatException | XMLStreamException e){
            JOptionPane.showMessageDialog(null,"Can not read database",
            "Error reading database",JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                XMLStreamReader.close();
            } catch (XMLStreamException e){
                JOptionPane.showMessageDialog(null,"Can not close database file",
                "Error closing database file" ,JOptionPane.ERROR_MESSAGE);
            }
        }
        return devices;
    }
}
