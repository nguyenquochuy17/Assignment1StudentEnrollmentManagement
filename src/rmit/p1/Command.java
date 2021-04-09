package rmit.p1;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Command implements StudentEnrollmentManager {
    HashSet<Student> studentList = new LinkedHashSet<>();
    HashSet<Course> courseList = new LinkedHashSet<>();
    HashSet<String> semList = new LinkedHashSet<>();


    public Command() throws FileNotFoundException {
        readCSVFile();
    }

    private void readCSVFile() throws FileNotFoundException {
        File fileCSV = new File("default.csv");
        Scanner input = new Scanner(fileCSV);
        while(input.hasNext()){
            String line = input.nextLine();
            line = line.replaceAll("[\\n\\t ]", "");
            String[] token = line.split(",");
            token[0] = token[0].replaceAll("[^a-zA-Z0-9]", "");
            Student stu = new Student(token[0],token[1],token[2]);
            if(!checkDuplicateStudent(stu)) {
                studentList.add(stu);
            }
            Course course = new Course(token[3],token[4],token[5]);
            if(!checkDuplicateCourse(course)) {
                courseList.add(course);
            }

            semList.add(token[6]);
            StudentEnrollment studentEnrollment = new StudentEnrollment(stu,course,token[6]);
            studentEnrollmentList.add(studentEnrollment);
        }
    }

    private boolean checkDuplicateCourse(Course newCourse){
        if(courseList == null){
            return false;
        }else{
            for(Course course : courseList){
                if(newCourse.getId().equals(course.getId())){
                    return true;
                }
            }
            return false;
        }

    }
    private boolean checkDuplicateStudent(Student newStu){
        if(studentList == null){
            return false;
        }else{
            for(Student stu : studentList){
                if((newStu.getId()).equals((stu.getId()))){
                    return true;
                }
            }
            return false;
        }

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

    private Course checkCourse(HashSet<Course> courseList){
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
        Course EnrolledCourse = checkCourse(courseList);
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


    @Override
    public void updateEnrollment() {
        //show all Student Enrollment
        showStudents();
        //Check student you want to update
        Student EnrolledStudent = checkStudent(studentList);
        String updateId = EnrolledStudent.getId();
        // show student enrollment of that update id
        showUpdateStudentEnrollment(updateId);
        int option = chooseOption();
        //Main program
        if (option ==1){
            Course EnrolledCourse = checkCourse(showAvailableCourse(updateId));
            showSem();
            String sem = checkSem();
            StudentEnrollment studentEnrollment = new StudentEnrollment(EnrolledStudent,EnrolledCourse,sem);
            // Check Duplicate
            if(checkDuplicateEnrollment(studentEnrollment)) {
                System.out.println("Enrollment already existed");
            }else{
                this.studentEnrollmentList.add(studentEnrollment);
                System.out.println("Sucessfully add enrollment");
            }
        }else if (option == 2){
            List<StudentEnrollment> deleteEnrollmentList= showUpdateStudentEnrollment(updateId);
            // Show Update and Input delete
            int deleteId = checkDeleteEnrollId(deleteEnrollmentList);
            //Get Enrollment that you want to delete
            StudentEnrollment DeletedEnroll = deleteEnrollmentList.get(deleteId-1);
            //Delete enrollment
            studentEnrollmentList.remove(DeletedEnroll);
        }

    }

    private int chooseOption(){
        System.out.println("What option you want to do");
        System.out.println("1. Add new course");
        System.out.println(("2. Delete course"));
        while(true) {
            System.out.println("Input your option");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            if(option != 1 && option != 2){
                System.out.println("Wrong choice");
            }else{
                return option;
            }
        }

    }
    private HashSet<Course> showAvailableCourse(String updateId){
        HashSet<Course> availableCourse= new HashSet<>();
        outer: for(Course course : courseList){
            for(StudentEnrollment studentEnrollment :studentEnrollmentList){
                // if 1 compare student id vs student enroll
                if(studentEnrollment.getStudent().getId().equals(updateId) && studentEnrollment.getCourse().getId().equals(course.getId())) {
                    // if 2 compare course with each course in course enrol
                    continue outer;
                }
            }
            availableCourse.add(course);
             System.out.println(course.getId()+" || "+course.getName());

        }
        return availableCourse;
    }

    private List<StudentEnrollment> showUpdateStudentEnrollment(String updateId){
        int count = 0;
        List<StudentEnrollment> deleteCourse = new ArrayList<>();
        for(StudentEnrollment studentEnrollment :studentEnrollmentList){
            if(studentEnrollment.getStudent().getId().equals(updateId)){
                count++;
                System.out.println(count+ ": "+studentEnrollment.getCourse().getId()+" "+ studentEnrollment.getCourse().getName()+ " || "+studentEnrollment.getSemester());
                deleteCourse.add(studentEnrollment);
            }
        }
        return deleteCourse;

    }

    @Override
    public void deleteEnrollment() {
        // show All Enrollment
        getAllEnrollment();
        // Check delete enroll ID
        int deleteId = checkDeleteEnrollId(studentEnrollmentList);
        //Remove enrollment
        studentEnrollmentList.remove(deleteId-1);
    }
    private int checkDeleteEnrollId(List<StudentEnrollment> studentEnrollmentList){
        while(true) {
            System.out.println("Input your ID that you want to delete");
            Scanner input = new Scanner(System.in);
            int deleteId = input.nextInt();
            // Check student id
            if(deleteId >=1 & deleteId <= studentEnrollmentList.size()) {
                return deleteId;
            }else{
                System.out.println("Not exist");
            }
        }
    }

    @Override
    public void getAllEnrollment() {
        int count = 0;
        System.out.println("-----Student Enrollment List --------");
        for (StudentEnrollment enrollment : studentEnrollmentList){
            count++;
            System.out.println(count+": "+enrollment.toString());
        }
        System.out.println("-------------------------------------");
    }
    private static void writeToFile(String fileName, String line, boolean append) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(fileName, append));
            output.println(line);
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        finally {
            if (output != null)
                output.close();
        }

    }
    private void askSaveReport(String fileName, String record){
        System.out.print("Do you want to save the report into CSV file (Y/N) ");
        while(true) {
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if(answer.equals("Y")){
                writeToFile(fileName,record,false);
                break;
            }else if(answer.equals("N")){
                break;
            }else{
                System.out.println("Wrong choice");
            }
        }

    }
    public void printAllCoursesFor1StudentFor1Sem(){
        String record="";
        for (String sem : semList){
            record+= "========"+sem+"==========\n";
            for(Student student : studentList){
                boolean foundCourse = false;
                ArrayList<StudentEnrollment> allCourse= new ArrayList<>();
                for( StudentEnrollment studentEnrollment : studentEnrollmentList){
                    if(studentEnrollment.getSemester().equals(sem) && studentEnrollment.getStudent().getId().equals(student.getId())){
                        allCourse.add(studentEnrollment);
                        foundCourse = true;
                    }
                }
                if(foundCourse == true ){
                    record+="Student: "+student.getId()+" | "+student.getName()+"\n";
                    for (StudentEnrollment  studentEnrollment : allCourse){
                        record+="    "+studentEnrollment.getCourse().getId()+" "+studentEnrollment.getCourse().getName()+"\n";
                    }
                }
            }
        }
        System.out.println(record);
        askSaveReport("Report1.csv",record);

    }
    public void printAllStudentsFor1CourseFor1Sem(){
        String record ="";
        for (String sem : semList){
            record+="========"+sem+"==========\n";
            for(Course course: courseList){
                boolean foundStudent = false;
                ArrayList<StudentEnrollment> allStudent= new ArrayList<>();
                for( StudentEnrollment studentEnrollment : studentEnrollmentList){
                    if(studentEnrollment.getSemester().equals(sem) && studentEnrollment.getCourse().getId().equals(course.getId())){
                        allStudent.add(studentEnrollment);
                        foundStudent = true;
                    }
                }
                if(foundStudent == true ){
                    record+="Course: "+course.getId()+" | "+course.getName()+"\n";
                    for (StudentEnrollment  studentEnrollment : allStudent){
                        record+="    "+studentEnrollment.getStudent().getId()+" "+studentEnrollment.getStudent().getName()+"\n";
                    }
                }
            }
        }
        System.out.println(record);
        askSaveReport("Report2.csv",record);

    }
    public void printAllCourseOfferdInSemester(){
        String record="";
        for(String sem: semList){
            record+="========"+sem+"==========\n";
            for(Course course: courseList){
                boolean check = false;
                for( StudentEnrollment studentEnrollment : studentEnrollmentList){
                    if(studentEnrollment.getSemester().equals(sem) && studentEnrollment.getCourse().getId().equals(course.getId())){
                        check = true;
                    }
                }
                if(check) {
                    record+=course.getId()+" "+course.getName()+"\n";
                }
            }
        }
        System.out.println(record);
        askSaveReport("Report3.csv",record);
    }


}
