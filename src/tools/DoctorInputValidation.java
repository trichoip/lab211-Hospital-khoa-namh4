/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author dang khoa
 */
public class DoctorInputValidation {

    private static Scanner sc = new Scanner(System.in);

    public static boolean checkDoctorId(String valid) {
        String regex = "DOC\\d{3}";
        if (valid.matches(regex) == false) {
            System.out.println("Invalid input");
        }
        return valid.matches(regex);
    }

    public static boolean checkDoctorName(String valid) {
        String regex = "[a-zA-Z ]{1,25}";
        if (valid.matches(regex) == false) {
            System.out.println("Invalid input");
        }
        return valid.matches(regex);
    }

    public static boolean checkDoctorInputSex(String valid) {
        String regex = "True|False";
        if (valid.matches(regex) == false) {
            System.out.println("Invalid input");
        }
        return valid.matches(regex);
    }

    public static boolean checkAddress(String valid) {
        String regex = ".{0,50}";
        if (valid.matches(regex) == false) {
            System.out.println("Invalid input");
        }
        return valid.matches(regex);
    }

    public static String getDateCurrent() {
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(d);
    }

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            sc = new Scanner(System.in);
            System.out.println(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Invalid input");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        sc = new Scanner(System.in);
        System.out.println(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        } while (check || number > max || number < min);
        return number;
    }
}
