import java.util.ArrayList;
import java.util.List;

public class Class {
    public String className;
    public int capacity;
    public List<Course> courses;

    public Class(String className, int capacity) {
        this.className = className;
        this.capacity = capacity;
        this.courses = new ArrayList<>();
    }

    public Class(String className, int capacity, List<Course> courses) {
        this.className = className;
        this.capacity = capacity;
        this.courses = courses;
    }

    public void setClassName(String className) { this.className = className; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
    public String getClassName() { return className; }
    public int getCapacity() { return capacity; }
    public List<Course> getCourses() { return courses; }

    public void addCourse(Course c) {
        this.courses.add(c);
    }
}
