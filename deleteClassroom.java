import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class deleteClassroom {
    private JPanel panel1;
    private JButton deleteButton;
    private JButton cancelButton;
    private JComboBox comboBox1;


    public deleteClassroom(JFrame frame, JTable table) {
        Utils FO = new Utils();
        List<String> readClassFile = new ArrayList<>();
        try {
            readClassFile = FO.readCSVFile("CLassroomCapacity.csv");

        } catch (Exception e) {
            System.err.println(e);
        }
        List<Class> classes = FO.createClass(readClassFile);
        createComboBox(classes);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Class> newClasses = new ArrayList<>();
                for(Class c: classes) {
                    if(c != classes.get(comboBox1.getSelectedIndex())) {
                        newClasses.add(c);
                    }
                }
                FO.writeCSVFileClass(newClasses);
                refreshTable(newClasses, table);
                // TODO
                // Refresh Course to Class Function
                frame.dispose();
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

    private void createComboBox(List<Class> classes) {
        String[] classNames = new String[classes.size()];
        int count = 0;
        for(Class c: classes) {
            classNames[count] = c.getClassName() + "-" + c.getCapacity();
            count++;
        }
        comboBox1.setModel(new DefaultComboBoxModel(classNames));
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

