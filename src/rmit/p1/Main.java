package rmit.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// Student List
//        Student stu1 = new Student("111","Huy","28/10/2000");
//        Student stu2 = new Student("222","Huu Huy","21/1/2000");
//        Student stu3 = new Student("333","Tuong","8/5/1996");
//        ArrayList<Student> studentList = new ArrayList<Student>();
//        studentList.add(stu1);
//        studentList.add(stu2);
//        studentList.add(stu3);
    // Course List
//        Course course1 = new Course("COSC111","UCD",12);
//        Course course2 = new Course("COSC222","Programming 1",12);
//        Course course3 = new Course("COSC333","Intro to IT",12);
//        ArrayList<Course> courseList = new ArrayList<Course>();
//        courseList.add(course1);
//        courseList.add(course2);
//        //Semester List


    //Add studentEnrollment
        Command test = new Command();

        test.printAllCoursesFor1StudentFor1Sem();
        test.printAllStudentsFor1CourseFor1Sem();
        test.printAllCourseOfferdInSemester();

//        test.addEnrollment();
//        test.addEnrollment();
//        test.updateEnrollment();
//        test.getAllEnrollment();
    // Print all Enrollment
//        File fileCSV = new File("default.csv");
//        Scanner input = new Scanner(fileCSV);
//        while(input.hasNext()){
//            String line = input.nextLine();
//            String[] token = line.split(",");
//            System.out.println(token[0].trim());
////            Student stu = new Student(token[0].trim(),token[1],token[2]);
//
//
//        }

    }

}
