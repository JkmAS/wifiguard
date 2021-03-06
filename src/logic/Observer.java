package logic;

/**
 *  Interface Observer
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.1
 *@created    01/2014
 */
public interface Observer {
    public void updateTable(); 
    public void updateProgressBar(boolean status);
}
