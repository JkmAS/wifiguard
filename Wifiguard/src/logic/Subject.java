package logic;

/**
 *  Interface Subject
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.0
 *@created    12/2013
 */
public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserversTable();
    public void notifyObserversProgressBar(boolean status);
}
