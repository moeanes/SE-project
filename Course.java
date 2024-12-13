import java.util.List;

public class Course {
    public String courseName;
    public String day;
    public int timeStart;
    public int timeEnd;
    public String lecturerName;
    public List<String> students;
    public Class lessonClass;

    public Course(String courseName, String day, int timeStart, int timeEnd, String lecturerName, List<String> students) {
        this.courseName = courseName;
        this.day = day;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.lecturerName = lecturerName;
        this.students = students;
    }
    public String getCourseName() { return courseName; }
    public String getDay() { return day; }
    public int getTimeStart() { return timeStart; }
    public int getTimeEnd() { return timeEnd; }
    public String getLecturerName() { return lecturerName; }
    public List<String> getStudents() { return students; }
    public Class getLessonClass() { return lessonClass; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setDay(String day) { this.day = day; }
    public void setTimeStart(int timeStart) { this.timeStart = timeStart; }
    public void setTimeEnd(int timeEnd) { this.timeEnd = timeEnd; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
    public void setStudents(List<String> students) { this.students = students; }
    public void setLessonClass(Class lessonClass) { this.lessonClass = lessonClass; }
}
