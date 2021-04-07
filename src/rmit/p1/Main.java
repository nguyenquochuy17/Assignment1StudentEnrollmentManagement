package rmit.p1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// Student List
        Student stu1 = new Student("111","Huy","28/10/2000");
        Student stu2 = new Student("222","Huu Huy","21/1/2000");
        Student stu3 = new Student("333","Tuong","8/5/1996");
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(stu1);
        studentList.add(stu2);
        studentList.add(stu3);
    // Course List
        Course course1 = new Course("COSC111","UCD",12);
        Course course2 = new Course("COSC222","Programming 1",12);
        Course course3 = new Course("COSC333","Intro to IT",12);
        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(course1);
        courseList.add(course2);

        Command test = new Command();
        test.addStudentEnrollment(studentList,courseList);
        for (StudentEnrollment enrollment : test.getAllStudentEnrollment()){
            System.out.println(enrollment.toString());
        }

    }

    int i = 0;
}
