import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class deleteCourse {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton addButton;
    private JButton cancelButton;

    public deleteCourse(JFrame frame, JTable table) {
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
            readCourseFile = FO.readCSVFile("Courses.csv");
        } catch (Exception e ) {
            System.err.println(e);
        }
        List<Course> courses = FO.createCourse(readCourseFile, 45,10);

        createComboBox(courses);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Course> newCourses = new ArrayList<>();
                for(Course c: courses) {
                    if(c != courses.get(comboBox1.getSelectedIndex())) {
                        newCourses.add(c);
                    }
                }
                FO.setCoursetoClass(classes, courses);
                FO.writeCSVFileCourse(newCourses);
                refreshTable(newCourses, table);
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

    private void createComboBox(List<Course> courses) {
        String[] stringCourses = new String[courses.size()];
        int count = 0;
        for(Course c: courses) {
            int startDuration = c.getTimeStart();
            int hourStart = startDuration/(60*60);
            startDuration = startDuration%(60*60);
            int minStart = startDuration/60;

            stringCourses[count] = c.getCourseName() + "-" + c.getDay() + "-" + hourStart + ":" + minStart + "-" + c.getLecturerName() + "-" + c.getStudents().size();
         ;   count++;
        }
        comboBox1.setModel(new DefaultComboBoxModel(stringCourses));
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
