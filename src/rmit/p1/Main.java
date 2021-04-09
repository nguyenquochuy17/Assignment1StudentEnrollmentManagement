package rmit.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Command run;

    static {
        try {
            run = new Command();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        mainMenu();

    }

    public static void mainMenu() throws FileNotFoundException {
        // Sub menu
        System.out.println("---------Student Enrollment System---------------");
        System.out.println("What do you want to do: ");
        System.out.println("1. CRUD enrollment");
        System.out.println("2. Print");
        System.out.println("3. Exit");
        System.out.println("------------------------------------------------");
        System.out.print("Choose number from 1 to 3: ");
        // CHeck input
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.println("Wrong input");
            System.out.print("Choose number from 1 to 3: ");
            choice = input.nextLine();
        }
        if (choice.equals("1")) {
            MenuCRUD();
        } else if (choice.equals("2")) {
            MenuPrint();
        } else if (choice.equals("3")) {
            return;
        }
    }

    public static void MenuCRUD() throws FileNotFoundException {
        //Sub Menu
        System.out.println("---------CRUD Enrollment Menu-------------------");
        System.out.println("What do you want to do: ");
        System.out.println("1. Add enrollment");
        System.out.println("2. Update enrollment");
        System.out.println("3. Delete enrollment");
        System.out.println("4. Get all enrollment");
        System.out.println("5. Back to Main Menu");
        System.out.println("6. Exit");
        System.out.println("------------------------------------------------");
        System.out.print("Choose number from 1 to 6: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6")) {
            System.out.println("Wrong input");
            System.out.print("Choose number from 1 to 6: ");
            choice = input.nextLine();
        }
        if (choice.equals("1")) {
            run.addEnrollment();
        } else if (choice.equals("2")) {
            run.updateEnrollment();
        } else if (choice.equals("3")){
            run.deleteEnrollment();
        } else if (choice.equals("4")) {
            run.getAllEnrollment();
        } else if (choice.equals("5")) {
            mainMenu();
            return;
        }else if (choice.equals("6")){
            return;
        }
        while (true) {
            System.out.print("Do you want to continue managing? (y/n): ");
            String accept = input.nextLine();
            if (accept.equals("y") || accept.equals("Y")) {
                MenuCRUD();
                return;
            } else if (accept.equals("n") || accept.equals("N")) {
                return;
            } else {
                System.out.println("Wrong input");
            }
        }
    }
    public static void MenuPrint() throws FileNotFoundException {
        //Sub Menu
        System.out.println("---------Menu Print-------------------");
        System.out.println("What do you want to do: ");
        System.out.println("1. Print all courses for 1 student in 1 semester");
        System.out.println("2. Print all students of 1 course in 1 semester");
        System.out.println("3. Prints all courses offered in 1 semester");
        System.out.println("4. Back to Main Menu");
        System.out.println("5. Exit");
        System.out.println("------------------------------------------------");
        System.out.print("Choose number from 1 to 5: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5")) {
            System.out.println("Wrong input");
            System.out.print("Choose number from 1 to 5: ");
            choice = input.nextLine();
        }
        if (choice.equals("1")) {
            run.printAllCoursesFor1StudentFor1Sem();
        } else if (choice.equals("2")) {
            run.printAllStudentsFor1CourseFor1Sem();
        } else if (choice.equals("3")) {
            run.printAllCourseOfferdInSemester();
        } else if (choice.equals("4")) {
            mainMenu();
            return;
        } else if (choice.equals("5")) {
            return;
        }
        while (true) {
            System.out.print("Do you want to continue managing? (y/n): ");
            String accept = input.nextLine();
            if (accept.equals("y") || accept.equals("Y")) {
                MenuPrint();
                return;
            } else if (accept.equals("n") || accept.equals("N")) {
                return;
            } else {
                System.out.println("Wrong input");
            }
        }

    }
}






