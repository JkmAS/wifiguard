package logic;

/**
 *  Interface Observer
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    01/2014
 */
public interface Observer {
    public void updateTable(); 
    public void updateProgressBar(boolean status);
}
