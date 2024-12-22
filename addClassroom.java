import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class addClassroom {
    private JPanel panel1;
    private JButton addButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;


    public addClassroom(JFrame frame, JTable table) {
        Utils FO = new Utils();
        List<String> readClassFile = new ArrayList<>();
        try {
            readClassFile = FO.readCSVFile("CLassroomCapacity.csv");

        } catch (Exception e) {
            System.err.println(e);
        }
        List<Class> classes = FO.createClass(readClassFile);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Add")) {
                    String name = textField1.getText();
                    String capacity = textField2.getText();
                    int intCapacity = -1;
                    try {
                        intCapacity = Integer.parseInt(capacity);
                        Class newClass = new Class(name, intCapacity);
                        classes.add(newClass);
                        FO.writeCSVFileClass(classes);
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, "Wrong capacity. Try again. (" + error.getMessage() + ")");
                    }
                    refreshTable(classes, table);
                    // TODO
                    // Refresh Course to Class Function
                    frame.dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public JPanel getRootPanel() {
        return panel1;
    }

    private void refreshTable(List<Class> classList, JTable table) {
        Object[][] data = new Object[classList.size()][2];
        int counter = 0;
        for(Class c : classList) {
            data[counter][0] = c.getClassName();
            data[counter][1] = c.getCapacity();
            counter++;
        }
        table.setModel(new DefaultTableModel(
                data,
                new String[]{"Classroom", "Capacity"}
        ));
        table.setEnabled(false);
    }
}


