package rmit.p1;

import java.util.List;
import java.util.ArrayList;

public interface StudentEnrollmentManager {
    void addStudentEnrollment(ArrayList<Student> studentList, ArrayList<Course> courseList);
    void updateStudentEnrollment(ArrayList<Student> studentList);
    void deleteStudentEnrollment();
    List<StudentEnrollment> getAllStudentEnrollment();
}
