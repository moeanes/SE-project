public class Course {
    public int timeStart;
    public int timeEnd;
    public String lecturerName;
    public Student[] students;

    public Course(int timeStart, int timeEnd, String lecturerName, Student[] students) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.lecturerName = lecturerName;
        this.students = students;
    }

    public int getTimeStart() { return timeStart; }
    public int getTimeEnd() { return timeEnd; }
    public String getLecturerName() { return lecturerName; }
    public Student[] getStudents() { return students; }
    public void setTimeStart(int timeStart) { this.timeStart = timeStart; }
    public void setTimeEnd(int timeEnd) { this.timeEnd = timeEnd; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
    public void setStudents(Student[] students) { this.students = students; }
}
