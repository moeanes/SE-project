import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addStudent {
    private JPanel panel1;
    private JTextField textField1;
    private JButton addButton;
    private JButton cancelButton;

    public addStudent(JFrame frame, DefaultListModel listModel, JList list) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Add")) {
                    String name = textField1.getText();
                    listModel.addElement(name);
                    list.setModel(listModel);
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
        return  panel1;
    }
}
