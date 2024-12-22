
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class MainApp {
    public static void main(String args[]) throws Exception {
        Utils FO = new Utils();
        List<String> readClassFile = FO.readCSVFile("CLassroomCapacity.csv");
        List<Class> classes = FO.createClass(readClassFile);


        List<String> readCoursesFile = FO.readCSVFile("Courses.csv");
        List<Course> courses = FO.createCourse(readCoursesFile, 45, 10);

        List<Student> studentList = createStudents(courses);
        FO.setCoursetoClass(classes,courses);


        //printClass(classes);
        //printCourses(courses);
        //printStudents(studentList);


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI(classes, courses, studentList);
            }
        });
    }

    private static void createGUI(List<Class> classList, List<Course> courseList,List<Student> studentList) {
        GrapInter ui = new GrapInter(classList, courseList, studentList);
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        ImageIcon img = new ImageIcon("logo3.png");
        frame.setIconImage(img.getImage());
        frame.setTitle("SE-Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setVisible(true);
    }

    private static List<Student> createStudents(List<Course> courses) {
        List<Student> students = new ArrayList<>();
        for(Course c : courses) {
            boolean nameCheck = false;
            for(String name: c.getStudents()) {
                for(Student s : students) {
                    if(s.getName().equals(name)) {
                        nameCheck = true;
                        s.addLesson(c);
                    }
                }
                if(!nameCheck) {
                    Student newStudent = new Student(name, new ArrayList<>());
                    newStudent.addLesson(c);
                    students.add(newStudent);
                }
            }
        }
        return students;
    }

    private static void printClass(List<Class> classes) {
        for(Class c : classes) {
            System.out.print(c.getClassName() + " - " + c.getCapacity() + " - ");
            for(Course crs : c.getCourses()) {
                System.out.print("[" + crs.getCourseName() + "]");
            }
            System.out.println();
        }
    }

    private static void printCourses(List<Course> courses) {
        for(Course c : courses) {
            System.out.println(c.getCourseName() + " " + c.getDay() + " " + c.getTimeStart() + " " + c.getTimeEnd() + " " + c.getLecturerName()  );
            for(String s: c.getStudents()) {
                System.out.println("- " + s);
            }
            System.out.println("----------");
        }
    }

    private static void printStudents(List<Student> studentList) {
        for(Student s: studentList) {
            System.out.print(s.getName() + " => ");
            for(Course c: s.getLessons()) {
                int second = c.getTimeStart();
                int hour = second/(60*60);
                second = second%(60*60)  ;
                int minute = second/60;
                System.out.print("[" + c.getCourseName() + "-" + hour + ":" + minute + "], ");
            }
            System.out.println();
        }
    }
}
