package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ObjectView;

/**
 * Created by MaciekBihun on 2016-05-08.
 */
public class Table {

    private DefaultTableModel tableModel;
    private JTable table;
    public Table(JTable table){
        this.table = table;
        tableModel = (DefaultTableModel) table.getModel();
        System.out.println("Row count: " + tableModel.getRowCount());
    }

    public DefaultTableModel getTableModel(){
        return tableModel;
    }

    public  void addColumns(int index){
        for (int i = 0; i < index; i++) {
            tableModel.addColumn("x" + index);
        }
    }

    public void addRow(Object [] zmienne){
        tableModel.addRow(zmienne);
    }

    public void removeRow(int whichRow) {
        tableModel.removeRow(whichRow);
    }


}
