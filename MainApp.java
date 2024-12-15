
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class MainApp {
    public static void main(String args[]) throws Exception {
        List<String> readClassFile = readCSVFile("Data/CLassroomCapacity.csv");
        List<Class> classes = createClass(readClassFile);


        List<String> readCoursesFile = readCSVFile("Data/Courses.csv");
        List<Course> courses = createCourse(readCoursesFile, 45, 10);

        List<Student> studentList = createStudents(courses);
        setCoursetoClass(classes,courses);


        printClass(classes);
        printCourses(courses);
        printStudents(studentList);


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

    private static void setCoursetoClass(List<Class> classes, List<Course> courses) {
        for(Course course : courses) {
            for(Class clss : classes) {
                if(course.getStudents().size() > clss.getCapacity()) {
                    System.out.println("Capacity full " + course.getCourseName() + " : " + clss.getClassName());
                } else {
                    Boolean check = true;
                    for(Course schCourse : clss.getCourses()) {
                        if(course.getDay().equals(schCourse.getDay())) {
                            if(course.getTimeStart() >= schCourse.getTimeStart() && course.getTimeStart() <= schCourse.getTimeEnd()) {
                                check = false;
                                System.out.println("Between Start " + course.getCourseName() + " : " + clss.getClassName());
                            } else if(course.getTimeEnd() >= schCourse.getTimeStart() && course.getTimeEnd() <= schCourse.getTimeEnd()) {
                                check = false;
                                System.out.println("Between End " + course.getCourseName() + " : " + clss.getClassName());
                            }
                        } else {
                            System.out.println("Different Day " + course.getCourseName() + " : " + clss.getClassName());
                        }
                    }
                    if(check) {
                        clss.addCourse(course);
                        course.setLessonClass(clss);
                        System.out.println("Passed"  + course.getCourseName() + " : " + clss.getClassName());
                        break;
                    }
                }
            }
        }
    }

    private static void createGUI(List<Class> classList, List<Course> courseList,List<Student> studentList) {
        GrapInter ui = new GrapInter(classList, courseList, studentList);
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        ImageIcon img = new ImageIcon("logo2.png");
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

    private static List<String> readCSVFile(String filename) throws Exception {
        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                records.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return records;
    }

    private static List<Class> createClass(List<String> records){
        List<Class> classes = new ArrayList<>();
        int countError = 0;
        for(String record : records) {
            String[] values = record.split(";");
            if(values.length == 2) {
                if(!values[0].equals("Classroom") || !values[1].equals("Capacity")) {
                    Class newClass = new Class(values[0], Integer.parseInt(values[1]));
                    classes.add(newClass);
                }
            } else {
                System.out.println("File format wrong change it. Line: " + countError);
            }
        }
        return classes;
    }

    private static List<Course> createCourse(List<String> records,int courseDuration, int breakTime){
        List<Course> courses = new ArrayList<>();
        int countError = 0;
        for(String record : records) {
            countError++;
            String[] values = record.split(";");
            if(!values[0].equals("Course")) {
                String[] dates = values[1].split(" ");
                if(dates.length == 2) {
                    String[] times = dates[1].split(":");
                    if(times.length == 2) {
                        int hour = Integer.parseInt(times[0]);
                        int minute = Integer.parseInt(times[1]);
                        int startTime = hour*60*60 + minute*60;
                        int duration = Integer.parseInt(values[2]);
                        int endTime = startTime + duration*courseDuration*60 + duration*breakTime*60;
                        List<String> students = new ArrayList<>();
                        for(int i = 4; i < values.length; i++) {
                            students.add(values[i]);
                        }
                        Course newCourse = new Course(values[0], dates[0], startTime, endTime, values[3], students);
                        courses.add(newCourse);
                    } else {
                        System.out.println("File format wrong change it. Line: " + countError);
                    }
                }else {
                    System.out.println("File format wrong change it. Line: " + countError);
                }
            }
        }
        return courses;
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
