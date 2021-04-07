package rmit.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Command implements StudentEnrollmentManager {
//    private List<StudentEnrollment> studentEnrollmentList = new ArrayList<>();

    @Override
    public void addStudentEnrollment(ArrayList<Student> studentList, ArrayList<Course> courseList) {
        //Show student id list
        for (Student stu: studentList){
            System.out.println(stu.getId()+" ");
        }
        //Check student Id and add student
        boolean manage = true;
        Student EnrolledStudent = null ;

        while(manage) {
            System.out.println("Input Student Id");
            Scanner input = new Scanner(System.in);
             String stuId = input.nextLine();
            // Check student id
            for (Student stu : studentList) {
                if (stuId.equals(stu.getId())) {
                    EnrolledStudent = stu;
                    manage = false;
                    break;
                }
            }
            if(manage) {
                System.out.println("Not exsit student Id");
            }

        }
        //Show course Id list
        for (Course course: courseList){
            System.out.println(course.getId()+" ");
        }
        //Check input and add Course Id
        manage = true;
        Course EnrolledCourse = null;
        while(manage) {
            System.out.println("Input Course Id");
            Scanner input = new Scanner(System.in);
            String courseId = input.nextLine();
            // Check student id
            for (Course course : courseList) {
                if (courseId.equals(course.getId())) {
                    EnrolledCourse = course;
                    manage = false;
                    break;
                }
            }
            if(manage){
                System.out.println("Not exist Course");
            }
        }

        //Add semester
        System.out.println("Input Semester");
        Scanner input = new Scanner(System.in);
        String sem = input.nextLine();

        //Create StudentEnrollment
        StudentEnrollment studentEnrollment = new StudentEnrollment(EnrolledStudent,EnrolledCourse,sem);
        //Add into StudentEnrollment List
        this.studentEnrollmentList.add(studentEnrollment);

    }

    @Override
    public void updateStudentEnrollment(ArrayList<Student> studentList) {
        //Show student id list
        for (Student stu: studentList){
            System.out.println(stu.getId()+" ");
        }
        //Check student Id
        boolean manage = true;
        Student UpdatedStudent = null ;

        while(manage) {
            System.out.println("Input Student Id");
            Scanner input = new Scanner(System.in);
            String stuId = input.nextLine();
            // Check student id
            for (Student stu : studentList) {
                if (stuId.equals(stu.getId())) {
                    UpdatedStudent = stu;
                    manage = false;
                    break;
                }
            }
            if(manage) {
                System.out.println("Not exsit student Id");
            }
        }
        // Update Student Id



    }

    @Override
    public void deleteStudentEnrollment() {

    }


    @Override
    public List<StudentEnrollment> getAllStudentEnrollment() {
        return this.studentEnrollmentList;
    }


}
