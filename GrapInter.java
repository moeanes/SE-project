import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.List;

public class GrapInter extends JFrame {

    private JPanel panel1;
    private JTable table1;
    private JButton studentListButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton addButton1;
    private JButton deleteButton1;
    private JTable table2;


    public GrapInter(List<Class> classList, List<Course> courseList) {
        createTable(classList, courseList);
    }

    public JPanel getRootPanel() {
        return panel1;
    }

    private void createTable(List<Class> classList, List<Course> courseList) {
        Object[][] data = new Object[classList.size()][2];
        int counter = 0;
        for(Class c : classList) {
            data[counter][0] = c.getClassName();
            data[counter][1] = c.getCapacity();
            counter++;
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Classroom", "Capacity"}
        ));

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
            data2[counter2][6] = c.getLessonClass();
            counter2++;
        }


        table2.setModel(new DefaultTableModel(
                data2,
                new String[]{"Course Name", "Day", "Start Time", "Finis Time", "Instructor", "Student Count", "Class"}
        ));


    }
}
