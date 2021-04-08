package rmit.p1;

import java.util.*;

public class Command implements StudentEnrollmentManager {
    HashSet<Student> studentList = new LinkedHashSet<>();
    ArrayList<Course> courseList = new ArrayList<Course>();
    ArrayList<String> semList = new ArrayList<>();

    public Command(){
        // Student List Sample
        Student stu1 = new Student("111","Huy","28/10/2000");
        Student stu2 = new Student("222","Huu Huy","21/1/2000");
        Student stu3 = new Student("333","Tuong","8/5/1996");
        studentList.add(stu1);
        studentList.add(stu2);
        studentList.add(stu3);
        // Course List sample
        Course course1 = new Course("COSC111","UCD",12);
        Course course2 = new Course("COSC222","Programming 1",12);
        Course course3 = new Course("COSC333","Intro to IT",12);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        // Semester List sample
        semList.add("2021A");
        semList.add("2021B");
        semList.add("2021C");
    }

    private void showStudents(){
        for (Student stu: studentList){
            System.out.println(stu.getId()+" || "+stu.getName()+" || "+stu.getBirthday());
        }
    }

    private Student checkStudent(HashSet<Student> studentList){
        Student EnrolledStudent;
        while(true){
            System.out.println("Input Student Id");
            Scanner input = new Scanner(System.in);
            String sId = input.nextLine();
            for (Student stu : studentList) {
                if (sId.equals(stu.getId())) {
                    EnrolledStudent = stu;
                    return EnrolledStudent;
                }
            }
            System.out.println("Not exist student ");
        }
    }

    private void showCourse(){
        for (Course course: courseList){
            System.out.println(course.getId()+" || "+course.getName()+" ||"+course.getNumOfCredit());
        }
    }

    private Course checkCourse(){
        Course EnrolledCourse;
        while(true) {
            System.out.println("Input Course Id");
            Scanner input = new Scanner(System.in);
            String courseId = input.nextLine();
            // Check student id
            for (Course course : courseList) {
                if (courseId.equals(course.getId())) {
                    EnrolledCourse = course;
                    return EnrolledCourse;
                }
            }
            System.out.println("Not exist course");
        }
    }

    private void showSem(){
        for (String sem : semList){
            System.out.println(sem);
        }
    }

    private String checkSem(){
        while(true) {
            System.out.println("Input Sem");
            Scanner input = new Scanner(System.in);
            String inputSem = input.nextLine();
            // Check student id
            for (String sem : semList) {
                if (inputSem.equals(sem)) {

                    return inputSem;
                }
            }
            System.out.println("Not exist semester");

        }
    }

    private boolean checkDuplicateEnrollment(StudentEnrollment enroll) {
        for(StudentEnrollment stuEnroll: studentEnrollmentList){
            if(stuEnroll.getStudent().equals(enroll.getStudent())
                    && stuEnroll.getCourse().equals(enroll.getCourse())
                    && stuEnroll.getSemester().equals(enroll.getSemester())){
                return true;
            }
        }
        return false;

    }
    @Override
    public void addEnrollment() {
        System.out.println("-------Add Enrollment--------");
        // Show student list
        showStudents();
        // Check student
        Student EnrolledStudent = checkStudent(studentList);
        // Show course list
        showCourse();
        // Check course
        Course EnrolledCourse = checkCourse();
        // Show semester
        showSem();
        //Check semester
        String sem = checkSem();
        //Create StudentEnrollment
        StudentEnrollment studentEnrollment = new StudentEnrollment(EnrolledStudent,EnrolledCourse,sem);
        //Add StudentEnrollment into StudentEnrollment List
        if(checkDuplicateEnrollment(studentEnrollment)) {
            System.out.println("Enrollment already existed");
        }else{
            this.studentEnrollmentList.add(studentEnrollment);
            System.out.println("Sucessfully add enrollment");
        }
    }

    private void getAllEnrolledStudent(HashSet<Student> allStudentEnrollment){

        for (StudentEnrollment stuEnroll: studentEnrollmentList){
            allStudentEnrollment.add(stuEnroll.getStudent());
        }
        for (Student stu: allStudentEnrollment){
            System.out.println(stu.getId()+" || "+ stu.getName());
        }
    }


    @Override
    public void updateEnrollment() {
        HashSet<Student> allStudentEnrollment = new HashSet<Student>();
        //show all Student Enrollment
        getAllEnrolledStudent(allStudentEnrollment);
        //check student id if it match with all student enrollment list
        checkStudent(allStudentEnrollment);
        //show all semesters of that enrollment


        //Check student Id
//        boolean manage = true;
//        Student UpdatingStudent = null ;
//
//        while(manage) {
//            System.out.println("Input Student Id");
//            Scanner input = new Scanner(System.in);
//            String stuId = input.nextLine();
//            // Check student id
//            for (Student stu : studentList) {
//                if (stuId.equals(stu.getId())) {
//                    UpdatingStudent = stu;
//                    manage = false;
//                    break;
//                }
//            }
//            if(manage) {
//                System.out.println("Not exsit student Id");
//            }
//        }
        // Show semeters that the updating students are in

        // Update Student Id




    }

    @Override
    public void deleteEnrollment() {

    }


    @Override
    public void getAllEnrollment() {
        System.out.println("-----Student Enrollment List --------");
        for (StudentEnrollment enrollment : studentEnrollmentList){
            System.out.println(enrollment.toString());
        }
        System.out.println("-------------------------------------");
    }


}
