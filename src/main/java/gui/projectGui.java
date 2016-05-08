package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by MaciekBihun on 2016-05-08.
 */
public class projectGui  {

    //Gui elements
    private JPanel main_panel;
    private JPanel input_panel;
    private JLabel amount_of_variable_label;
    private JTextField amount_of_variables_field;
    private JButton amount_approve_btt;
    private JTable variables_table;
    private JTable limit_table;
    private JButton addLimitBtt;
    private JButton removeLimitBtt;
    private JButton approve_function_btt;
    private JPanel properties_panel;
    private JLabel variablesTableLabel;
    private JPanel panel1;
    private JLabel limitListLabel;


    //Local Variables
    private Executor executor;
    private int number_of_variables;


    public projectGui(){

        amount_approve_btt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFunctionVariables();
            }
        });

        addLimitBtt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLimitToList();
            }
        });
    }

    private void addLimitToList(){
        Table limitTable = new Table(limit_table);
        //limitTable.addColumns();
    }


    private void setFunctionVariables(){
        Table functionTable = new Table(variables_table);
        if(functionTable.getTableModel().getColumnCount() > 0){
            System.out.println("Column counter : " + functionTable.getTableModel().getColumnCount());
            functionTable.getTableModel().setColumnCount(0);
        }

            Runnable r = () -> {
                String userInput = amount_of_variables_field.getText();
                if(Maths.isInteger(userInput)){
                    number_of_variables = Integer.parseInt(userInput);
                    System.out.println("String is a integer");
                    //Creates rows in JTable.
                    functionTable.addColumns(number_of_variables);
                    if(functionTable.getTableModel().getRowCount() > 0) {
                        functionTable.removeRow(0);
                    }
                    functionTable.addRow(new Object[]{});

                } else {
                    System.out.println("String is not integer");
                }

            };

            executor = Executors.newFixedThreadPool(4);
            executor.execute(r);
    }


    public void createUIComponents(){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new projectGui().main_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

class Maths
{
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
