import java.util.List;

public class Student {
    public String name;
    public List<Course> lessons;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<Course> lessons) {
        this.name = name;
        this.lessons = lessons;
    }

    public String getName() { return name; }
    public List<Course> getLessons() { return lessons; }
    public void setName(String name) { this.name = name; }
    public void setLessons(List<Course> lessons) { this.lessons = lessons; }

    public void addLesson(Course c) {
        this.lessons.add(c);
    }
}
