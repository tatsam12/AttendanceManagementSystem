package org.exmaple;

import org.exmaple.models.ModelAttendance;
import org.exmaple.models.ModelClass;
import org.exmaple.models.ModelUsers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) throws SQLException {
            Connection conn = DbHandler.connect();
            Scanner scanner = new Scanner(System.in);
            List<ModelUsers> users_lists = DbHandler.display_user(conn);
            List<ModelClass> classes_listz = DbHandler.display_class(conn);


            int choose;
            do {
                boolean end = false;
                System.out.println("\n\n\n\n\n 1. Add USers");
                System.out.println("2. Add Class");
                System.out.println("3. Add Students Attendance");
                System.out.println("4. Display users and class\n\n\n");
                choose = scanner.nextInt();

            } while (false);

//        //    boolean end = false;
//            boolean end = false;
//            while(!end){
//               System.out.println("\n\n1. Insert User");
//              System.out.println("2. Insert Classes");
//                System.out.println("3. Insert Attendance");
//                System.out.println("4. Display All Users");
//                System.out.println("5. Display All Classes");
//                System.out.println("6. End\n\n");
//                int choice = sc.nextInt();

                switch(choose) {
                case 1:
                    System.out.println("Enter your username:");
                    String username = scanner.next();
                    System.out.println("Enter your password:");
                    String password = scanner.next();
                    ModelUsers User = new ModelUsers(0, username, password);
                    DbHandler.insert_user(conn, User);

                    break;
                case 2:
                    System.out.println("Enter your classname:");
                    String classname = scanner.next();
                    ModelClass classez = new ModelClass(0, classname);
                    DbHandler.insert_class(conn, classez);
                    break;
                case 3:
                    System.out.println("Enter your username:");
                    username = scanner.next();
                    System.out.println("Enter your password:");
                    password = scanner.next();
                    System.out.println("Enter your classname:");
                    classname = scanner.next();
                    User = new ModelUsers(0, "", "");
                    classez = new ModelClass(0, "");
                    DbHandler.insert_Attendance(conn, User, classez);

                    break;
                case 4:
                    for (ModelUsers us : users_lists) {
                        System.out.println(us.getUser_id() + "." + " Name: " + us.getUsername());
                    }
                    break;
                case 5:
                    for (ModelClass cs : classes_listz) {
                        System.out.println(cs.getClass_id() + "." + " Name: " + cs.getClass_name());
                    }
                    break;
                case 6:
                    boolean end = true;
                    break;

                default:
                    System.out.println("Try in again.");
                    break;
            }
        }
        }


