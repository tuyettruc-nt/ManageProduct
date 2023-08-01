/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.util.Scanner;

/**
 *
 * @author HpEnvy
 */
public class Validation {
    private final static Scanner in = new Scanner(System.in);
    
    private Scanner sc = new Scanner(System.in);
    
    public String inputRegrex(String msg) {
        System.out.println(msg);
        while(true){
        try {
            String input = sc.nextLine().trim().toUpperCase().replace(" ", "_");
            if (input != null && input.length() >= 5) {
                return input;
            }else {
                System.out.println("Please input again least 5 characters and no spaces: ");
            }
        }catch (Exception e) {
            System.out.println("Wrong format!!");
        }
    }
    }
    
    public String inputStr(String msg) {
        String data;
        do{
            System.out.println(msg);
            data = sc.nextLine().trim().toUpperCase().replace(" ", "_");
        }while (data.length() == 0);
        return data;
    }
    
    public String checkInput(String msg) {
        System.out.println(msg);
        while(true) {
            Scanner sc = new Scanner(System.in);
            String result = sc.nextLine();
        
            if (result.equalsIgnoreCase("Available")){
                return "Available";
            } 
            if (result.equalsIgnoreCase("Not_Available")){
                return "Not_Available";
            } 
            System.out.println("Please input Available or Not_Available");
            System.out.println("Input again:");
            
        } 
        
        
    }
    public int inputInt(String msg, int min, int max) {
        System.out.println(msg);
        while(true) {
            String input = sc.nextLine();
            try{
                int number = Integer.parseInt(input);
                if (number < min || number > max) {
                    System.out.println("Please input between " + min + ", " + max + ":");
                    continue;
                }
                return number;
            }catch (Exception e) {
                System.err.println("Please input integer number: ");
            }
        }
    }
    public float inputFloat(String msg, float min, float max) {
        System.out.println(msg);
        while(true) {
            String input = sc.nextLine();
            try{
                float number = Float.parseFloat(input);
                if (number < min || number > max) {
                    System.out.println("Please input between " + min + ", " + max + ":");
                    continue;
                }
                return number;
            }catch (Exception e) {
                System.err.println("Please input float number: ");
            }
                   
        }
    }
    public boolean checkInputYN(String msg) {
        System.out.println(msg);
        while (true) {
            String result = sc.nextLine();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                System.out.println("Exit program!");
                return false;
            }
            System.err.println("Please input y/Y or n/N");
            System.out.println("Input again: ");
        }
    }
    public boolean checkInputQ(String msg) {
        System.out.println(msg);
        while (true) {
            String result = sc.nextLine();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                System.out.println("Enter the next information!");
                return false;
            }
            System.err.println("Please input y/Y or n/N");
            System.out.println("Input again: ");
        }
    }
}
