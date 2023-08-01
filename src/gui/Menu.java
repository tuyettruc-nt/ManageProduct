/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.io.IOException;
import java.util.Scanner;
import manager.Manager;
import manager.Validation;

/**
 *
 * @author HpEnvy
 */
public class Menu {
    
    Validation validate = new Validation();
    private static String[] ops = {
        "Print.",
        "Create a Product.",
        "Check exits Product",
        "Search product' information by Name.",
        "Update Product.",
        "Save Products to file.",
        "Print list Products from the file after sorting.",
        "Quit."};
    
    private int getChoice() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i + 1) + ", " + ops[i]);
        }
        return validate.inputInt("Input your choice: ", 1, 8);
    }
    
    
    
    public void display() throws IOException {
        Manager m = new Manager();
        m.loadFromFile();
        int choice;
        boolean yesOrNo = false;
        do{
            System.out.println("===============Product Project===============");
            choice = getChoice();
            switch(choice) {
                case 1:
                    m.print();
                    yesOrNo = validate.checkInputYN("Do you want to back menu (Y/N): ");
                    break;
                case 2:
                    m.addProduct();
                    yesOrNo = validate.checkInputYN("Do you want to back menu (Y/N): ");
                    break;
                case 3:
                    m.checkProductExist();
                    yesOrNo = validate.checkInputYN("Do you want to back menu (Y/N): ");
                    break;
                case 4:
                    m.searchProduct();
                    yesOrNo = validate.checkInputYN("Do you want to back menu (Y/N): ");
                    break;
                case 5:
                    m.updateInfor();
                    yesOrNo = validate.checkInputYN("Do you want to back menu (Y/N): ");
                    break;
                case 6:
                    m.saveToFile();
                    yesOrNo = validate.checkInputYN("Do you want to back menu (Y/N): ");
                    break;
                case 7:
                    m.printAndSort();
                    yesOrNo = validate.checkInputYN("Do you want to back menu (Y/N): ");
                    break;
                case 8:
                    System.out.println("Exit program.");
                    break;
                default:
                    System.out.println("Invalid, try input again!");
                    break;
            }
        }while (choice !=8 && yesOrNo == true);
        
            
        
    }    
           
}
