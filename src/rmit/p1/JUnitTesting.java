package rmit.p1;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JUnitTesting {

     public Command run;

    {
        try {
            run = new Command("default.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetAllEnrollment(){
        assertEquals("Check size of student enrollment",15,run.getNumOfEnrollment());
    }
    @Test
    public void testUpdateChooseDelete(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S101312" + System.getProperty("line.separator") + "2"+ System.getProperty("line.separator") + "1";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        run.updateEnrollment();
        assertEquals("Checking size of List after deleting", 14, run.getNumOfEnrollment());
        System.setIn(sysInBackup);
    }

    @Test
    public void testUpdateChooseAdd(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S102192" + System.getProperty("line.separator") + "1"+ System.getProperty("line.separator") + "PHYS1230"+ System.getProperty("line.separator") + "2020C";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        run.updateEnrollment();
        assertEquals("Checking size of List after adding", 16, run.getNumOfEnrollment());
        System.setIn(sysInBackup);
    }



    @Test
    public void testAddEnrollment(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S103723"+  System.getProperty("line.separator")
                + "COSC4030" +  System.getProperty("line.separator")  + "2021A" ;


        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        run.addEnrollment();
        assertEquals("Checking size of List after adding", 16, run.getNumOfEnrollment());
        System.setIn(sysInBackup);
    }
    @Test
    public void testPrintAllCourseInOneSem() {
        ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
        System.setIn(in);
        run.printAllCourseOfferdInSemester();
    }
    @Test
    public void testPrintAllStudentOfOneCourseInOneSem() {
        ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
        System.setIn(in);
        run.printAllStudentsFor1CourseFor1Sem();
    }

    @Test
    public void testPrintAllCourseOneStudentOneSem() {
        ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
        System.setIn(in);
        run.printAllCoursesFor1StudentFor1Sem();
    }
    @Test
    public void testDeleteEnrollment(){
        ByteArrayInputStream in = new ByteArrayInputStream("15".getBytes());
        System.setIn(in);
        run.deleteEnrollment();
        assertEquals("Checking student enrollment list size after delete", 14,
                run.getNumOfEnrollment());
    }
    @Test
    public void testGetOne(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String simulatedUserInput = "S103723"+  System.getProperty("line.separator")
                + "BUS2232" +  System.getProperty("line.separator")  + "2020B" ;


        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        run.getOneEnrollment();
        System.setIn(sysInBackup);
    }
    // Test get and set for student and course calss
    Student student  = new Student("S103817","Thuy Thu Nguyen","3/4/2000");
    Course course = new Course("COSC3321","Artificial Intelligence",3);
    @Test
    public void	testGetStudentID(){
        assertEquals("S103817",student.getId());
    }

    @Test
    public void	testSetStudentID(){
        student.setId("S103817");
        assertEquals("S103817",student.getId());
    }

    @Test
    public void	testGetStudentName(){
        assertEquals("Thuy Thu Nguyen",student.getName());
    }

    @Test
    public void	testSetStudentName(){
        student.setName("Thuy Thu Nguyen");
        assertEquals("Thuy Thu Nguyen",student.getName());
    }

    @Test
    public void	testGetBirthdate(){
        assertEquals("3/4/2000",student.getBirthday());
    }

    @Test
    public void	testSetBirthdate(){
        student.setBirthday("3/4/2000");
        assertEquals("3/4/2000",student.getBirthday());
    }

    /**
     * Test class COURSE
     */
    // test getter setter of cID
    @Test
    public void	testGetCourseID(){
        assertEquals("COSC3321",course.getId());
    }

    @Test
    public void	testSetCourseID(){
        course.setId("BAX10101");
        assertEquals("BAX10101",course.getId());
    }
    @Test
    public void testToStringStudent(){
        assertEquals("Student{" +
                "id='" + "S103817" + '\'' +
                ", name='" + "Thuy Thu Nguyen"+ '\'' +
                ", birthday='" + "3/4/2000" + '\'' +
                '}', student.toString());
    }
    // test getter, setter of cName
    @Test
    public void	testGetCourseName(){
        assertEquals("Artificial Intelligence",course.getName());
    }

    @Test
    public void	testSetCourseName(){
        course.setName("Programming 1");
        assertEquals("Programming 1",course.getName());
    }

    // test getter, setter of numOfCredits
    @Test
    public void	testGetNumOfCredits(){
        assertEquals(3,course.getNumOfCredit());
    }

    @Test
    public void	testSetNumOfCredits(){
        course.setNumOfCredit(5);
        assertEquals(5,course.getNumOfCredit());
    }

    @Test
    public void testToStringCourse(){
        assertEquals("Course{" +
                        "id='" + "COSC3321" + '\'' +
                        ", name='" + "Artificial Intelligence" + '\'' +
                        ", numOfCredit=" + 3 +
                        '}', course.toString());
    }

    /**
     * Test Student Enrollment
     */
    private static Student student1 = new Student("S103912","Son Thanh Le","2/9/2001");
    private static Course course1 = new Course("PHYS1230","Introductory Human Physiology",4);
    private static StudentEnrollment enrolment1 = new StudentEnrollment(student1,course1,"2020C");

    // test getter and setter of students property
    @Test
    public void	testGetStudent(){
        assertEquals(student1.toString(),enrolment1.getStudent().toString());
    }
    @Test
    public void	testSetStudent(){
        Student student2 = new Student("S102732", "Mark Duong", "8/28/2001");
        StudentEnrollment se = new StudentEnrollment();
        se.setStudent(student2);
        assertEquals(student2.toString(),se.getStudent().toString());
    }

    // test getter and setter of course property in Student Enrollment
    @Test
    public void	testGetCourse(){
        assertEquals(course1.toString(),enrolment1.getCourse().toString());
    }
    @Test
    public void	testSetCourse(){
        Course course2 = new Course("IT0123", "Intro to IT", 12);
        StudentEnrollment se = new StudentEnrollment();
        se.setCourse(course2);
        assertEquals(course2.toString(),se.getCourse().toString());
    }

    // test getter and setter of course property in Student Enrollment
    @Test
    public void	testGetSem(){
        assertEquals("2020C",enrolment1.getSemester());
    }
    @Test
    public void	testSetSem(){
        StudentEnrollment se = new StudentEnrollment();
        se.setSemester("2021D");
        assertEquals("2021D",se.getSemester());
    }
    @Test
    public void testToStringStudentEnrollment(){
        assertEquals("student = " + "S103912"+
                " "+ "Son Thanh Le"+
                " "+ "2/9/2001"+
                " || course = " + "PHYS1230"+ " " + "Introductory Human Physiology"+" "+4+
                " || semester = " + "2020C", enrolment1.toString());
    }
}
