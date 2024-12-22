import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public GrapInter(List<Class> classList, List<Course> courseList, List<Student> studentList) {
        createTable1(classList);
        createTable2(courseList);

        studentListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == studentListButton) {
                    StudentList studentWindow = new StudentList(studentList);
                    JPanel root = studentWindow.getRootPanel();
                    JFrame frame = new JFrame();
                    ImageIcon img = new ImageIcon("logo3.png");
                    frame.setIconImage(img.getImage());
                    frame.setTitle("Student List");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setContentPane(root);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addButton) {
                    JFrame frame = new JFrame();
                    addClassroom addClassroomButton = new addClassroom(frame, table1);
                    JPanel root = addClassroomButton.getRootPanel();
                    ImageIcon img = new ImageIcon("logo3.png");
                    frame.setIconImage(img.getImage());
                    frame.setTitle("Add Classroom");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setContentPane(root);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == deleteButton) {
                    JFrame frame = new JFrame();
                    deleteClassroom deleteClassroomButton = new deleteClassroom(frame,table1);
                    JPanel root = deleteClassroomButton.getRootPanel();
                    ImageIcon img = new ImageIcon("logo3.png");
                    frame.setIconImage(img.getImage());
                    frame.setTitle("Delete Classroom");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setContentPane(root);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addButton1) {
                    JFrame frame = new JFrame();
                    addCourse addCourseButton = new addCourse(frame, table2);
                    JPanel root = addCourseButton.getRootPanel();
                    ImageIcon img = new ImageIcon("logo3.png");
                    frame.setIconImage(img.getImage());
                    frame.setTitle("Add Course");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setContentPane(root);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == deleteButton1) {
                    JFrame frame = new JFrame();
                    deleteCourse deleteCourseButton = new deleteCourse(frame, table2);
                    JPanel root = deleteCourseButton.getRootPanel();
                    ImageIcon img = new ImageIcon("logo3.png");
                    frame.setIconImage(img.getImage());
                    frame.setTitle("Delete Course");
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

    private void createTable1(List<Class> classList) {
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
        table1.setEnabled(false);

    }

    private void createTable2(List<Course> courseList) {
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


        table2.setModel(new DefaultTableModel(
                data2,
                new String[]{"Course Name", "Day", "Start Time", "Finis Time", "Instructor", "Student Count", "Class"}
        ));
        table2.setEnabled(false);
    }
}
