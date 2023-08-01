/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;


import entity.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author HpEnvy
 */
public class Manager {
    
    ArrayList <Product> products = new ArrayList<>();
    Validation validate = new Validation();
    
    public int checkProductName(String productName) {
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getProductName().equalsIgnoreCase(productName)) {
                return i;
            }   
        }        
       return -1; 
    }
    
    public int checkProductID(String productID) {
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getProductID().equals(productID)){
            return i;
            }
        
    }
        return -1;
    }
    public void print() {
        if (products.isEmpty()) {
            System.out.println("The list empty!");
        }else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println(products.get(i));
            }
        }
    }
    public void addProduct() {
        String productName, productID, status;
        float unitPrice;
        int quanity;        
        productID = validate.inputStr("Enter productID: ");
        do {
          if (checkProductID(productID) >= 0) {
            System.out.println("Please input productID. Data is duplicated!");
            productID = validate.inputStr("Enter productID: ");
        }else {
            break;
          }
        }while(true);
        
        productName = validate.inputRegrex("Enter productName: ");
        do {
            if (checkProductName(productName) >= 0) {
            System.out.println("Please input productName. Data is duplicated!");
            productName = validate.inputRegrex("Enter productName: ");
            }
            else {
                break;
            }
        }while(true);
        
        unitPrice = validate.inputFloat("Enter unitPrice:", 0, 10000);
        quanity = validate.inputInt("Enter quanity:", 0, 1000);
        status = validate.checkInput("Enter status: ");
        products.add(new Product(productID, productName, unitPrice, quanity, status));
    }
    public void checkProductExist() {
       String name = validate.inputStr("Enter product name (you need check it): ");
       if (products.isEmpty()) {
           System.out.println("The list empty!");
       } 
       if (checkProductName(name) >= 0) {
            System.out.println("Exist Product!");                        
        } else {
            System.out.println("No Product Found");
        }
    }
    
    public void searchProduct() {
        String name = validate.inputStr("Enter productName (just need input a part name): ");
        if (products.isEmpty()) {
            System.out.println("Have no any Product!");
        }
        else {
            int[] pos = checkProductNames(name.toUpperCase());
            if (pos.length == 0) {
                System.out.println("Not found!");
            } else {
                for (int i = 0; i < pos.length; i++) {
                    System.out.println(products.get(pos[i]));
                }
            }
        }
    }
    public int[] checkProductNames(String name) {
        ArrayList<Integer> tmp = new ArrayList();
        int[] index;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductName().contains(name)) {
                tmp.add(i);
            }
        }
        index = new int[tmp.size()];
        for (int i = 0; i < index.length; i++) {
            index[i] = tmp.get(i);
        }
        return index;
    }
    public void deleteProduct() {
        if (products.isEmpty()) {
            System.out.println("The list empty. No delete can be performed!");
        } else {
            String ID = validate.inputStr("Enter productID: ");
            for (int i = 0; i < products.size(); i++) {
               if (products.get(i).getProductID().equals(ID)) {
                   products.remove(i);
                   System.out.println("Remove Successfully!");
                   return;
               }
            } System.out.println("ProductName does not exist!");
                    
        }
    }
    public void updateProduct(int index) {
        boolean ask;
        if(products.isEmpty()) {
            System.out.println("The list empty. No delete can be performed!");
        }
            ask = validate.checkInputQ("Do you want to update ID? (Y/N):");
            if (ask) {
                String newID = validate.inputStr("Enter productID: ");
                products.get(index).setProductID(newID);
            }

            ask = validate.checkInputQ("Do you want to update productName? (Y/N):");
            if (ask) {
                do {
                    String newName = validate.inputRegrex("Enter new productName: ");
                    if (checkProductName(newName) == -1 || checkProductName(newName) == index) {
                        products.get(index).setProductName(newName);
                        break;
                    } else {
                        System.out.println("Product name is exist!");
                    }
                } while (true);
            }

            ask = validate.checkInputQ("Do you want to update new unitprice? (Y/N):");
            if (ask) {
                float newUnitPrice = validate.inputFloat("Enter unitPrice:", 0, 10000);
                products.get(index).setUnitPrice(newUnitPrice);

            }

            ask = validate.checkInputQ("Do you want to update new quanity? (Y/N):");
            if (ask) {
                int newQuanity = validate.inputInt("Enter quanity", 0, 1000);
                products.get(index).setQuantity(newQuanity);
            }

            ask = validate.checkInputQ("Do you want to update new status? (Y/N):");
            if (ask) {
                String newStatus = validate.checkInput("Enter status: ");
                products.get(index).setStatus(newStatus);
            }
        System.out.println("Update successfully!");
    }
        
       
    public void updateInfor() {
        String checkID = validate.inputStr("Enter product ID: ");
        int index = checkProductID(checkID);
        if (checkProductID(checkID) == -1 || products.isEmpty()) {
            System.out.println("Product does not exist!");
        } else { 
            System.out.println("1. Update product information");
            System.out.println("2. Delete product information");
            int choice = validate.inputInt("You choose 1 or 2: ", 1, 2);
            if (choice == 1) {
                updateProduct(index);
            }
            if (choice == 2) {
                deleteProduct();
            }
        }
    }
    
    public boolean saveToFile() throws IOException {
        File f;
        PrintWriter pw = null;
        FileWriter fw = null;

        if (products.isEmpty()) {
            System.out.println("The list empty!");
            return false;
        }
        try {
            f = new File("D:\\PRO192\\Session_Exercise\\Exercise01\\ProductProject\\Product.txt");
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
            for (int i = 0; i < products.size(); i++) {
                pw.println(products.get(i).getProductID() + ", " + 
                products.get(i).getProductName() + ", " + products.get(i).getUnitPrice() +
                ", " + products.get(i).getQuantity() + ", " + products.get(i).getStatus());        
            } System.out.println("Save done!");
        }catch (IOException e) {
            System.out.println(e);
            System.out.println("Save error!");
        }finally {
            pw.close();
            fw.close();
        }return true;
        }
        
    public boolean loadFromFile() throws IOException {
        File f;
        BufferedReader br = null;
        FileReader fr = null;
        try {
            f = new File("D:\\PRO192\\Session_Exercise\\Exercise01\\ProductProject\\Product.txt");
            if (!f.exists()) {
                System.out.println("File does not exist!");
                return false;
            }
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ", ");
                String loadID = stk.nextToken();
                String loadName = stk.nextToken();
                float loadPrice = Float.parseFloat(stk.nextToken());
                int loadQuantity = Integer.parseInt(stk.nextToken());
                String loadStatus = stk.nextToken();
                Product p = new Product(loadID, loadName, loadPrice, loadQuantity, loadStatus);
                products.add(p);
            }                        
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return false; 
        }finally{
            br.close();
            fr.close();
        }
        return true;
    }
    public void printAndSort() {
        if (products.isEmpty()) {
            System.out.println("The list empty. No print information!");
        }else {
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    if (p1.getQuantity() != p2.getQuantity()) {
                        return p2.getQuantity() - p1.getQuantity();
                    }else {
                        return (int)(p1.getUnitPrice() - p2.getUnitPrice());
                    }
                }
            }); this.print();}
        }
    }
