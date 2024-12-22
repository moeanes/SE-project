import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class addCourse {
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton addButton;
    private JButton cancelButton;
    private JButton addStudentButton;
    private JList list1;
    private JComboBox comboBox2;

    public addCourse(JFrame frame, JTable table) {
        Utils FO = new Utils();

        List<String> readClassFile = new ArrayList<>();
        try {
            readClassFile = FO.readCSVFile("CLassroomCapacity.csv");

        } catch (Exception e) {
            System.err.println(e);
        }
        List<Class> classes = FO.createClass(readClassFile);

        List<String> readCourseFile = new ArrayList<>();
        try {
            readCourseFile = FO.readCSVFile("Data/Courses.csv");
        } catch (Exception e ) {
            System.err.println(e);
        }
        List<Course> courses = FO.createCourse(readCourseFile, 45,10);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        comboBox1.setModel(new DefaultComboBoxModel(days));

        String[] times = {"8:30", "9:25", "10:20", "11:15", "12:10", "13:5", "14:0", "14:55", "15:50", "16:45", "17:40", "18:35", "19:30", "20:25", "21:20", "22:15", "23:10" };
        comboBox2.setModel(new DefaultComboBoxModel(times));

        DefaultListModel listModel = new DefaultListModel();

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                addStudent addStudentButton = new addStudent(frame, listModel, list1);
                JPanel root = addStudentButton.getRootPanel();
                ImageIcon img = new ImageIcon("logo3.png");
                frame.setIconImage(img.getImage());
                frame.setTitle("Add Student");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(root);
                frame.pack();
                frame.setVisible(true);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Add")) {
                    String name = textField1.getText();
                    String date = comboBox1.getSelectedItem().toString();
                    String time = comboBox2.getSelectedItem().toString();
                    String duration = textField2.getText();
                    String lecturer = textField3.getText();

                    int courseDuration = 45;
                    int breakTime = 10;

                    int size = list1.getModel().getSize();
                    List<String> getList = new ArrayList<>(size);
                    for(int i = 0; i < size; i++) {
                        getList.add(list1.getModel().getElementAt(i).toString());
                    }
                    try {
                        String[] times = time.split(":");
                        int hour = Integer.parseInt(times[0]);
                        int minute = Integer.parseInt(times[1]);
                        int startTime = hour*60*60 + minute*60;
                        int intDuration = Integer.parseInt(duration);
                        int endTime = startTime + intDuration*courseDuration*60 + intDuration*breakTime*60;
                        Course newCourse = new Course(name, date, startTime, endTime, lecturer, getList);
                        courses.add(newCourse);
                        FO.setCoursetoClass(classes, courses);
                        FO.writeCSVFileCourse(courses);
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Wrong input. Try again. (" + error.getMessage() + ")");
                    }
                    refreshTable(courses, table);
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

    private void refreshTable(List<Course> courseList, JTable table) {
        Object[][] data2 = new Object[courseList.size()][7];
        int counter2 = 0;
        for(Course c : courseList) {
            data2[counter2][0] = c.getCourseName();
            data2[counter2][1] = c.getDay();
            int secondStart = c.getTimeStart();
            int hourStart = secondStart/(60*60);
            secondStart = secondStart%(60*60);
            int minuteStart = secondStart/60;
            data2[counter2][2] = (hourStart + ":" + minuteStart);
            int secondEnd = c.getTimeEnd();
            int hourEnd = secondEnd/(60*60);
            secondEnd = secondEnd%(60*60);
            int minuteEnd = secondEnd/60;
            data2[counter2][3] = (hourEnd + ":" + minuteEnd);
            data2[counter2][4] = c.getLecturerName();
            data2[counter2][5] = c.getStudents().size();
            data2[counter2][6] = c.getLessonClass().getClassName();
            counter2++;
        }

        table.setModel(new DefaultTableModel(
                data2,
                new String[]{"Course Name", "Day", "Start Time", "Finis Time", "Instructor", "Student Count", "Class"}
        ));
        table.setEnabled(false);
    }
}
