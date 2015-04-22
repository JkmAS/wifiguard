package gui.panel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import logic.Observer;

/**
 *  Class InfoPanel
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.0
 *@created    12/2013
 */
public class InfoPanel extends JPanel implements Observer {
    
    private JPanel barPanel;
    private JLabel infoText;
    private JProgressBar progressBar;
    
    /**
    * Constructor.
    */
    public InfoPanel(){
        setLayout(new BorderLayout());
               
        infoText = new JLabel(" Wifi Guard 2013 - 2014 | JkmAS");        
        infoText.setSize(350, 20);
        
        barPanel = new JPanel();
        progressBar = new JProgressBar();
        barPanel.add(progressBar);
        
        add(infoText,BorderLayout.WEST);
        add(barPanel,BorderLayout.CENTER);
    }

    @Override
    public void updateProgressBar(boolean status) {
        progressBar.setIndeterminate(status);
    }

    @Override
    public void updateTable() {
    }
}
