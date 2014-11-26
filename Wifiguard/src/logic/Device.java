package logic;

/**
 *  Class Device
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    12/2013
 */
public class Device {

    private int id;
    private String ipAddress; 
    private String macAddress;
    private String deviceName;
    private String information; 

    /**
     * Constructor.
     * @param id
     * @param ipAddress
     * @param macAddress
     * @param deviceName
     * @param information
    */
    public Device(int id, String ipAddress, String macAddress, String deviceName, String information){
        this.id = id;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.deviceName = deviceName;
        this.information = information;
    }

    /*
    * Getters and setters 
    */
    
    public int getID() {
        return id;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public String getDeviceName() {
        return deviceName;
    }
    public String getInformation() {
        return information;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }   
}
