import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public List<String> readCSVFile(String filename) throws Exception {
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

    public void writeCSVFileClass(List<Class> classes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("CLassroomCapacity.csv", false))) {
            for(Class c : classes) {
                String s = c.getClassName() + ";" + c.getCapacity();
                bw.write(s);
                bw.newLine();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeCSVFileCourse(List<Course> courses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Courses.csv", false))) {
            for(Course c : courses) {
                int startDuration = c.getTimeStart();
                int endDuration = c.getTimeEnd();

                int duration = endDuration-startDuration;
                int count = duration/(55*60);

                int hourStart = startDuration/(60*60);
                startDuration = startDuration%(60*60);
                int minStart = startDuration/60;

                String studentList = "";
                for(String str: c.getStudents()) {
                    studentList += str + ";";
                }

                String s = c.getCourseName() + ";" + c.getDay() + " " + hourStart + ":" + minStart + ";" + count + ";" + c.getLecturerName() + ";" + studentList;
                bw.write(s);
                bw.newLine();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Class> createClass(List<String> records){
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

    public List<Course> createCourse(List<String> records,int courseDuration, int breakTime){
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

    public void setCoursetoClass(List<Class> classes, List<Course> courses) {
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
                        System.out.println("Passed: "  + course.getCourseName() + " : " + clss.getClassName());
                        break;
                    }
                }
            }
        }
    }

}
