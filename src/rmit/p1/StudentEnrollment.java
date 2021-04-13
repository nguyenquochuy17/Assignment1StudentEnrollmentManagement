package rmit.p1;

public class StudentEnrollment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;

    }
    public StudentEnrollment(){}

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String
    toString() {
        return  "student = " + student.getId() +
                " "+ student.getName()+
                " "+ student.getBirthday()+
                " || course = " + course.getId() + " " + course.getName()+" "+ course.getNumOfCredit()+
                " || semester = " + semester
                ;
    }
}
