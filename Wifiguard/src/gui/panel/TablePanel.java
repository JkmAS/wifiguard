package gui.panel;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logic.Database;
import logic.Device;
import logic.FileLoader;
import logic.IniFileManager;
import logic.Observer;

/**
 *  Class TablePanel
 * 
 *@author     JkmAS Mejstrik Jakub
 *@version    1.1.0
 *@created    12/2013
 */
public class TablePanel extends JPanel implements Observer { 
    
    private JTable table;
    private FileLoader fileLoader;
    private Database database;
     
    /**
    * Constructor.
    */
    public TablePanel(){ 
        fileLoader = new FileLoader();
        database = new Database();
        add(makeJTable());
    } 
    
    /**
    * Make a table
    * @return scrollPane
    */
    public JScrollPane makeJTable(){
        String[] columnName = {"No.","IP Address","MAC Address","Device","Information"};
        DefaultTableModel model = new DefaultTableModel(columnName, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                switch(column){ 
                    case 3: 
                        return true;  
                    default: return false;
                }
            }        
        };        
        File databaseFile = new File(fileLoader.fileLoader("data/database","database.xml"));        
        if(databaseFile.exists()){
            ArrayList<Device> devices = database.readDatabase();
            for (Device device : devices){
                model.addRow(new String[]{Integer.toString(device.getID()), device.getIpAddress(), device.getMacAddress(), device.getDeviceName(), device.getInformation()});
            }
        }
        
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new TableMouseListener());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(590, 135));
        return scrollPane;
    }
    
    /**
    * Private class TableMouseListener.
    */
    private class TableMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent arg0) {
        }
        @Override
        public void mousePressed(MouseEvent arg0) {
        }
        @Override
        public void mouseReleased(MouseEvent arg0) {
            switch(table.getSelectedColumn()){ 
                case 3:
                    File databaseFile = new File(fileLoader.fileLoader("data/database","database.xml"));  
                    //For Database with all data
                    if(databaseFile.exists()){
                        String value = JOptionPane.showInputDialog(null,"Enter Device Name:");  
                        if (value != null){  
                            table.setValueAt(value, table.getSelectedRow(), table.getSelectedColumn());
                            ArrayList<Device> devices = database.readDatabase();
                            for (Device device : devices){
                                int tableSelectedRow = table.getSelectedRow();
                                tableSelectedRow++;
                                if(tableSelectedRow == device.getID()){  
                                        device.setDeviceName(value);
                                        
                                        IniFileManager nameDatabase = new IniFileManager("data/database", "devicedatabase.ini");
                                        nameDatabase.write(device.getMacAddress(), value);
                                }
                            }
                            database.makeDatabase(devices);                                
                        } 
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Can not write Device name to database,"
                        + "\ndatabase has been deleted by user",
                        "Information",JOptionPane.INFORMATION_MESSAGE);
                    }
            }            
        }
        @Override
        public void mouseEntered(MouseEvent arg0) {
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
        }
    }

    @Override
    public void updateTable() {
        removeAll();
        add(makeJTable());
        revalidate();
        repaint();        
    }
    
    @Override
    public void updateProgressBar(boolean status) {
    }
}