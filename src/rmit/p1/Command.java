package rmit.p1;

import java.io.*;
import java.util.*;

public class Command implements StudentEnrollmentManager {
    private List<StudentEnrollment> studentEnrollmentList = new ArrayList<>();
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    private HashSet<String> semList = new LinkedHashSet<>();



    public Command() throws FileNotFoundException {
        readCSVFile(InputFileName());
    }
    public Command(String fileName) throws FileNotFoundException {
        readCSVFile(fileName);
    }
    private String InputFileName(){
        while(true) {
            System.out.print("Do you want to add file (Y/N): ");
            Scanner input = new Scanner(System.in);
            String option = input.nextLine();
            // if Yes use default, if no then use file name they enter
            if (option.equals("Y") || option.equals("y")) {
                System.out.print("Enter your file name: ");
                String fileName = input.nextLine();
                return fileName;
            }else if (option.equals("N") || option.equals("n")) {
                return "default.csv";
            }else{
                System.out.println("Wrong input");
            }
        }
    }
    private void readCSVFile(String filename) throws FileNotFoundException {
        File fileCSV = new File(filename);
        Scanner input = new Scanner(fileCSV);
        while(input.hasNext()){
            // Read line Csv
            String line = input.nextLine();
            line = line.replaceAll("[\\n\\t ]", "");
            String[] token = line.split(",");
            token[0] = token[0].replaceAll("[^a-zA-Z0-9]", "");
            //Create student
            Student stu = new Student(token[0],token[1],token[2]);
            // Check Duplicate stu
            if(!checkDuplicateStudent(stu)) {
                studentList.add(stu);
            }
            // Create Course
            Course course = new Course(token[3],token[4],Integer.parseInt(token[5]));
            // Check Duplicate Course

            if(!checkDuplicateCourse(course)) {
                courseList.add(course);
            }
            // Create sem
            semList.add(token[6]);
            // Create enrollment
            StudentEnrollment studentEnrollment = new StudentEnrollment(stu,course,token[6]);
            // Add to enrollment List
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
        System.out.println("All Students: ");
        for (Student stu: studentList){
            System.out.println(stu.getId()+" || "+stu.getName()+" || "+stu.getBirthday());
        }
    }

    private Student inputStudent(Scanner input, ArrayList<Student> studentList){
        // Input Student Id and Check Student exist
        Student EnrolledStudent;
        while(true){
            System.out.print("Input Student Id: ");
//            Scanner input = new Scanner(System.in);
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
        System.out.println("All Course: ");
        for (Course course: courseList){
            System.out.println(course.getId()+" || "+course.getName()+" || "+course.getNumOfCredit());
        }
    }

    private Course inputCourse(Scanner input, ArrayList<Course> courseList){
        // Input Course Id and Check Course exist
        Course EnrolledCourse;
        while(true) {
            System.out.print("Input Course Id: ");
//            Scanner input = new Scanner(System.in);
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
        System.out.println("All semester: ");
        for (String sem : semList){
            System.out.println(sem);
        }
    }

    private String inputSem(Scanner input, HashSet<String> semList){
        // Input sem Id and Check sem exist
        while(true) {
            System.out.print("Input Sem: ");
//            Scanner input = new Scanner(System.in);
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

    public boolean checkDuplicateEnrollment(StudentEnrollment enroll) {
        for(StudentEnrollment stuEnroll: studentEnrollmentList){
            if(stuEnroll.getStudent().getId().equals(enroll.getStudent().getId())
                    && stuEnroll.getCourse().getId().equals(enroll.getCourse().getId())
                    && stuEnroll.getSemester().equals(enroll.getSemester())){
                return true;
            }
        }
        return false;

    }
    @Override
    public void addEnrollment() {
        System.out.println("-------Add Enrollment--------");
        Scanner input = new Scanner(System.in);
        // Show student list
        showStudents();
        // Check student
        Student EnrolledStudent = inputStudent(input, studentList);
        // Show course list
        showCourse();
        // Check courseinput
        Course EnrolledCourse = inputCourse(input, courseList);
        // Show semester
        showSem();
        //Check semester
        String sem = inputSem(input, semList);
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
        Scanner input = new Scanner(System.in);
        showStudents();
        //Check student you want to update
        Student EnrolledStudent = inputStudent(input,studentList);
        String updateId = EnrolledStudent.getId();
        // show Course of that update id
        showCourseOfStudent(updateId);
        String option = chooseUpdateOption(input);
        //Main program
        if (option.equals("1")){
            //Show Available Course and input Course and check course exist
            showCourse();

            Course EnrolledCourse = inputCourse(input,courseList);
            // Show sem and check sem id exist
            showSem();
            String sem = inputSem(input, semList);
            // Create Stu Enroll
            StudentEnrollment studentEnrollment = new StudentEnrollment(EnrolledStudent,EnrolledCourse,sem);
            //Check Duplicate
            if(checkDuplicateEnrollment(studentEnrollment)) {
                System.out.println("Enrollment already existed");
            }else{
                this.studentEnrollmentList.add(studentEnrollment);
                System.out.println("Sucessfully add enrollment");
            }

        }else if (option.equals("2")){
            // Show course of student
            List<StudentEnrollment> deleteEnrollmentList= showCourseOfStudent(updateId);
            //Check empty delete
            if(deleteEnrollmentList.isEmpty()){
                System.out.println("There is nothing to delete");
                return;
            }
            // Show Update and Input delete
            int deleteId = checkDeleteEnrollId(input, deleteEnrollmentList);
            //Get Enrollment that you want to delete
            StudentEnrollment DeletedEnroll = deleteEnrollmentList.get(deleteId-1);
            //Delete enrollment
            studentEnrollmentList.remove(DeletedEnroll);
            System.out.println("Successfully delete");
        }

    }

    private String chooseUpdateOption(Scanner input){
        System.out.println("What option you want to do");
        System.out.println("1. Add new course");
        System.out.println(("2. Delete course"));
        while(true) {
            System.out.print("Input your option: ");
            String option = input.nextLine();
            if(!option.equals("1")  && !option.equals("2")){
                System.out.println("Wrong choice");
            }else{
                return option;
            }
        }

    }


    private List<StudentEnrollment> showCourseOfStudent(String updateId){
        // Show Course of Student
        System.out.println("Enrolled Courses: ");
        int count = 0;
        List<StudentEnrollment> CourseOfStudent = new ArrayList<>();
        for(StudentEnrollment studentEnrollment :studentEnrollmentList){
            if(studentEnrollment.getStudent().getId().equals(updateId)){
                count++;
                System.out.println(count+ ": "+studentEnrollment.getCourse().getId()+" "+ studentEnrollment.getCourse().getName()+ " || "+studentEnrollment.getSemester());
                CourseOfStudent.add(studentEnrollment);
            }
        }
        return CourseOfStudent;

    }

    @Override
    public void deleteEnrollment() {
        Scanner input = new Scanner(System.in);
        // show All Enrollment
        getAllEnrollment();
        // Input and Check delete enroll ID
        int deleteId = checkDeleteEnrollId(input, studentEnrollmentList);
        //Remove enrollment
        studentEnrollmentList.remove(deleteId-1);
        System.out.println("Successfully delete");
    }
    private int checkDeleteEnrollId(Scanner input, List<StudentEnrollment> studentEnrollmentList){
        // Input delete Id and check it
        while(true) {
            System.out.print("Input the number that you want to delete: ");
            String s = input.nextLine();
            int deleteId = 0;
            try {
                deleteId = Integer.parseInt(s);
                if(deleteId >=1 & deleteId <= studentEnrollmentList.size()) {
                    return deleteId;
                }else{
                    System.out.println("Not exsit");
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong input");
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

    @Override
    public void getOneEnrollment() {
        // Show Student and Get sid
        Scanner input = new Scanner(System.in);
        showStudents();
        String sId = inputStudent(input, studentList).getId();
        // Show available Course and Get cId
        ArrayList<Course> availableCourseList =showAvailableCourse(sId);
        String cId = inputCourse(input, availableCourseList).getId();
        //Show availabe Sem and Get sem;
        HashSet<String> availableSemList= showAvailableSem(sId,cId);
        String sem = inputSem(input, availableSemList);
        // Main program
        for(StudentEnrollment studentEnrollment : studentEnrollmentList){
            if(studentEnrollment.getStudent().getId().equals(sId)&& studentEnrollment.getCourse().getId().equals(cId) && studentEnrollment.getSemester().equals(sem)){
                System.out.println(studentEnrollment.toString());
            }
        }
    }
    private ArrayList<Course> showAvailableCourse(String sId){
        System.out.println("Available Course: ");
        ArrayList<Course> availableCourse = new ArrayList<>();
        for(StudentEnrollment studentEnrollment : studentEnrollmentList){
            if(studentEnrollment.getStudent().getId().equals(sId)){
                System.out.println(studentEnrollment.getCourse().getId()+ " || "+studentEnrollment.getCourse().getName());
                availableCourse.add(studentEnrollment.getCourse());
            }
        }
        return availableCourse;
    }
    private HashSet<String> showAvailableSem(String sId, String cId){
        System.out.println("Available Semester: ");
        HashSet<String> availableSem = new HashSet<>();
        for(StudentEnrollment studentEnrollment : studentEnrollmentList){
            if(studentEnrollment.getStudent().getId().equals(sId) && studentEnrollment.getCourse().getId().equals(cId)){
                System.out.println(studentEnrollment.getSemester());
                availableSem.add(studentEnrollment.getSemester());
            }
        }
        return availableSem;
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
            if(answer.equals("Y")|| answer.equals("y")){
                writeToFile(fileName,record,false);
                break;
            }else if(answer.equals("N") || answer.equals("n")){
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
    public int getNumOfEnrollment(){
        return studentEnrollmentList.size();
    }


}
