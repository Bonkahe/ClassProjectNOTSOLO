/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.nio.file.Path;
import java.nio.file.Paths;
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
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a valid option (1 or 2)");
            }
            int choice = scanner.nextInt();
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
    }
    
    private static void CustomerCheckin()
    {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        System.out.println("Please input your reservation name:");
        String input = scanner.nextLine();
        String reservationName = input;
        
        int roomId = currentInstance.GetReservedRoom(input);
        
        if(roomId == -1)
        {
            System.out.println("No reservation, would you like to enter an empty room?");
            do {
                input = scanner.nextLine();
            } while(!"yes".equals(input.toLowerCase()) || !"y".equals(input.toLowerCase()) || !"no".equals(input.toLowerCase()) || !"n".equals(input.toLowerCase()) );
            
            if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y"))
            {
                CreateNewReservation(reservationName);
            }
        }
        else
        {
            CustomerMenuHandler returnCustomer = new CustomerMenuHandler(roomId);
            returnCustomer.InRoute();
        }
    }
    
    private static void CreateNewReservation(String reservationName)
    {
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        Room[] rooms = currentInstance.GetRooms();
        for (int i = 0; i < rooms.length; i++)
        {
            if (!rooms[i].occupied)
            {
                CustomerMenuHandler returnCustomer = new CustomerMenuHandler(i);
                currentInstance.FillRoom(i, reservationName);
                returnCustomer.InRoute();
            }
        }
    }
    
    private static void EmployeeLogin()
    {
        
    }
}
