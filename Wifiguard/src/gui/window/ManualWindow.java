package gui.window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import logic.FileLoader;

/**
 *  Class ManualWindow
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.0.0
 *@created    12/2013
 */
public class ManualWindow{    
    
    private JDialog dialog;
    private JEditorPane editorPane;
    private JPanel buttonPanel;       
    private JButton button;
    private URL file;
    
    /**
    * Constructor.
    */
    public ManualWindow(){
        FileLoader fileLoader = new FileLoader();
        
        dialog = new JDialog();
        Font font = new Font("default", Font.BOLD, 15);
        dialog.setLayout(new BorderLayout());
        dialog.setTitle("Manual");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setFont(font);
        try {
             file = ManualWindow.class.getResource("/documentation/manual.html");
             editorPane.setPage(file);              
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Missing manual",
            "Error in loading",JOptionPane.ERROR_MESSAGE);
        }        
        
        buttonPanel = new JPanel();
        button = new JButton();
        button.setText("Close");
        button.setFocusPainted(false);         
        button.setIcon(fileLoader.iconLoader("image","Return.png"));
        button.addActionListener(new ListenerClose()); 
        buttonPanel.add(button);
        
        dialog.add(new JScrollPane(editorPane));
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocation(400, 200);
        dialog.setSize(500,350); 
        dialog.setResizable(false);
        dialog.setVisible(false);
    }    
    
    /**
    * Setting of visibility
    * @param b 
    */
    public void setVisible(boolean b) {
        dialog.setVisible(b);
    }
    
    /**
    * Private class ListenerClose.
    */
    private class ListenerClose implements ActionListener{
    @Override
	public void actionPerformed(ActionEvent event) {   
            dialog.setVisible(false);
        }
    }
}