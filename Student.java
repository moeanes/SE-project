public class Student {
    public String name;
    public int studentNo;

    public Student(String name, int studentNo) {
        this.name = name;
        this.studentNo = studentNo;
    }
    public String getName() { return name; }
    public int getStudentNo() { return studentNo; }
    public void setName(String name) { this.name = name; }
    public void setStudentNo(int studentNo) { this.studentNo = studentNo; }
}
