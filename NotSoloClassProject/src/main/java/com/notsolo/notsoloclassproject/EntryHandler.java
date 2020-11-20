/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Bonkahe
 */
public class EntryHandler {
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        currentInstance.curDirectoryPath = (s + "\\database.data");
        currentInstance.CheckDatabaseIntegrity();
        
        boolean loop = true;
        while(loop)
        {            
            System.out.println("Would you like to check into a room(1) or login to admin control panel(2), or to exit the program(3)?");
            int choice = 0;
            boolean inputcheck = true;
            while(inputcheck)
            {
                if (scanner.hasNextInt())
                {
                    choice = scanner.nextInt();
                    inputcheck = false;
                }
                else
                {
                    scanner.nextLine();
                    System.out.println("Please enter an integer.");
                }
            }
            
            if (choice > 0 && choice < 4)
            {
                switch (choice) {
                    case 1:
                        CustomerCheckin();
                        break;
                    case 2:
                        EmployeeLogin();
                        break;
                    default:
                        System.out.println("Thank you for using our program.");
                        loop = false;
                        break;
                }
            }
        }
        scanner.close();
    }
    
    /**
     * Using the database manager singleton it will search to see if the room exists, if it does then it enters it.
     * If it does not exist it allows the user to create a new one, or back out to main menu.
     */
    private static void CustomerCheckin()
    {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        System.out.println("Please input your reservation name:");
        String input = scanner.nextLine();
        
        if (input.trim().isEmpty())
        {
            System.out.println("Please enter a non whitespace name.");
            return;
        }
        
        input = input.trim();
        
        String reservationname = input;
        
        int roomId = currentInstance.GetReservedRoom(input);
        
        if(roomId == -1)
        {
            System.out.println("No reservation, would you like to enter an empty room?");
            boolean loop = true;
            while (loop){
                System.out.println("Please enter yes or no.");
                input = scanner.nextLine();
                if ("yes".equals(input.toLowerCase()) || "y".equals(input.toLowerCase()) || "no".equals(input.toLowerCase()) || "n".equals(input.toLowerCase()) )
                {
                    loop = false;
                }
            } 
            
            if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y"))
            {
                CreateNewReservation(reservationname);
            }
        }
        else
        {
            CustomerMenuHandler returnCustomer = new CustomerMenuHandler(roomId);
            returnCustomer.InRoute();
        }
    }
    
    /**
     * Cycles through the rooms to find an empty one and place the user in it.
     * @param reservationName The name the individual entered earlier, used to denote this room reservation.
     */
    private static void CreateNewReservation(String reservationName)
    {
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        Room[] rooms = currentInstance.GetRooms();
        for (int i = 0; i < rooms.length; i++)
        {
            System.out.println(i + " - " + rooms[i].occupied);
            if (!rooms[i].occupied)
            {
                CustomerMenuHandler returnCustomer = new CustomerMenuHandler(i);
                currentInstance.FillRoom(i, reservationName);
                returnCustomer.InRoute();
                return;
            }
        }
    }
    
    /**
     * Takes username and password to allow entry to employee admin menu.
     */
    private static void EmployeeLogin()
    {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        System.out.println("Please input your admin password.");
        String password = scanner.nextLine();
        if (currentInstance.GetCredentials(password))
        {
            EmployeeMenuHandler newAdminPanel = new EmployeeMenuHandler();
            newAdminPanel.InRoute();
        }
        else
        {
            System.out.println("Incorrect admin password entered.");
        }
    }
}
