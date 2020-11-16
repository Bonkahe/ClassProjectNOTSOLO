
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.util.Scanner;

/**
 *
 * @author Bonkahe
 */
public class EmployeeMenuHandler {
    
    /*
    public void RunTest()
    {
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        System.out.println(currentInstance.teststring);
    }
    */
    DatabaseManager currentInstance;
    public void InRoute()
    {
        currentInstance = DatabaseManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop)
        {            
            System.out.println("Please select 1 to run a test");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a valid option.");
            }
            int choice = scanner.nextInt();
            if (choice > 0 && choice < 3)
            {
                switch (choice) {
                    case 1:
                        Test();
                        break;
                    default:
                        System.out.println("Returning to main menu...");
                        loop = false;
                        break;
                }
            }
            else
            {
                System.out.println("Input out of range of options.");
            }
        }
    }
    
    private void Test()
    {
        Item[] test = currentInstance.GetMenu();
        
        for (Item test1 : test) {
            System.out.println(test1.toString());
        }
    }    
}
