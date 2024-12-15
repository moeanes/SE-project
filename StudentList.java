import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentList {

    private JPanel panel1;
    private JTable table1;
    private JButton showScheduleButton;
    private JList<String> list1;
    private JButton addStudentButton;
    private JButton deleteStudentButton;

    public StudentList(List<Student> studentList) {
        createList(studentList);
        showScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list1.getSelectedIndex() != -1) {
                    System.out.println("You Selected : " + list1.getSelectedValue());
                    System.out.println(studentList.get(list1.getSelectedIndex()).getName());
                    for(Course c : studentList.get(list1.getSelectedIndex()).getLessons()) {
                        System.out.print(c.getCourseName()+ " -");
                    }
                    System.out.println();
                    StudentSchedule scheduleWindow = new StudentSchedule(studentList.get(list1.getSelectedIndex()));
                    JPanel root = scheduleWindow.getRootPanel();
                    JFrame frame = new JFrame();
                    ImageIcon img = new ImageIcon("logo2.png");
                    frame.setIconImage(img.getImage());
                    frame.setTitle(studentList.get(list1.getSelectedIndex()).getName() + " Schedule");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setContentPane(root);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return panel1;
    }

    private void createList(List<Student> studentList) {
        DefaultListModel<String> listOfStudent = new DefaultListModel<>();
        for(Student s : studentList) {
            listOfStudent.addElement(s.getName());
        }
        list1.setModel(listOfStudent);
    }
}
