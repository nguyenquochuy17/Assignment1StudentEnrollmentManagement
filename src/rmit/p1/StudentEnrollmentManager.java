package rmit.p1;

import java.util.List;
import java.util.ArrayList;

public interface StudentEnrollmentManager {
    List<StudentEnrollment> studentEnrollmentList = new ArrayList<>();
    void addEnrollment();
    void updateEnrollment();
    void deleteEnrollment();
    void getAllEnrollment();
}
