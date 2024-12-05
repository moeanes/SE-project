public class Class {
    public String className;
    public int capacity;
    public Course[] courses;

    public Class(String className, int capacity, Course[] courses) {
        this.className = className;
        this.capacity = capacity;
        this.courses = courses;
    }

    public void setClassName(String className) { this.className = className; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setCourses(Course[] courses) { this.courses = courses; }
    public String getClassName() { return className; }
    public int getCapacity() { return capacity; }
    public Course[] getCourses() { return courses; }
}
