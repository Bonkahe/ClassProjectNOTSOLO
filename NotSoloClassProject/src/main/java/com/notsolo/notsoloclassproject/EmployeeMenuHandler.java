
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.util.ArrayList;
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
        while(loop) {            
            System.out.println("Manage Customer Orders(1), Manage the Database(2), change Database Settings(3), Exit(0)");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice >= 0 && choice < 4){
                switch(choice){
                    case 0:
                        System.out.println("Returning to main menu...");
                        return;
                    case 1:
                        OrderManager();
                        break;
                    case 2:
                        Test();
                        break;
                    case 3:
                        Test();
                        break;
                    default:                        
                        break;
                }
            }
            else                
            {
                System.out.println("Incorrect selection.");
            }
        }
    }
    
    private void DatabaseManager()
    {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {            
            System.out.println("Change admin password(1), Delete databasefile(2), Return to Admin Menu(0)");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice >= 0 && choice < 3){
                switch(choice){
                    case 0:
                        System.out.println("Returning to admin menu...");
                        return;
                    case 1:
                        DisplayOrders();
                        break;
                    case 2:
                        FullfillOrder();
                        break;
                    default:
                        break;
                }
            }
            else                
            {
                System.out.println("Incorrect selection.");
            }
        }
    }
    
    private void ChangePassword()
    {
        
    }
    
    private void DeleteDatabase()
    {
        
    }
    
    private void OrderManager()
    {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {            
            System.out.println("Display Orders(1), Fullfill Order(2), Display Rooms(3), Remove Room Reservation(4), Return to Admin Menu(0)");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice >= 0 && choice < 5){
                switch(choice){
                    case 0:
                        System.out.println("Returning to admin menu...");
                        return;
                    case 1:
                        DisplayOrders();
                        break;
                    case 2:
                        FullfillOrder();
                        break;
                    case 3:
                        DisplayRooms();
                        break;
                    case 4:
                        RemoveReservation();
                        break;
                    default:
                        break;
                }
            }
            else                
            {
                System.out.println("Incorrect selection.");
            }
        }
    }
    
    private void FullfillOrder()
    {
        ArrayList<Order> currentActiveOrders = DisplayOrders();
        if (currentActiveOrders.size() > 0)
        {
            System.out.println("Select an option, or 0 to return to main menu.");
            
            Scanner scanner = new Scanner(System.in);
            boolean loop = true;
            while(loop) {    
                while (!scanner.hasNextInt()){
                    scanner.nextLine();
                    System.out.println("Please enter a number.");
                }
                int choice = scanner.nextInt();
                if(choice >= 0 && choice < currentActiveOrders.size() + 1){
                    if (choice == 0)
                    {
                        System.out.println("Returning...");
                    }
                    else
                    {
                        currentInstance.CompleteOrder(currentActiveOrders.get(choice), currentActiveOrders.get(choice).roomid);
                    }
                    loop = false;
                }
                else                
                {
                    System.out.println("Incorrect selection.");
                }
            }
        }
    }
    
    private ArrayList<Order> DisplayOrders()
    {
        Room[] rooms = currentInstance.GetRooms();
        ArrayList<Order> activeOrders = new ArrayList<Order>();
        
        for(int i = 0; i < rooms.length; i++)
        {
            if (rooms[i].pendingOrders.size() > 0)
            {
                activeOrders.addAll(rooms[i].GetCurrentOrders());
            }
        }
        
        if (activeOrders.size() > 0)
        {
            for(int i = 0; i < activeOrders.size(); i++)
            {
                System.out.println("Order #" + i + 1 + "\nContents: " + activeOrders.get(i).toString());
            }
        }
        else
        {
            System.out.println("No active orders.");
        }
        
        return activeOrders;
    }
    
    private void DisplayRooms()
    {
        Room[] rooms = currentInstance.GetRooms();
        for (int i = 0; i < rooms.length; i++) {
            System.out.println(rooms[i].toString());
        }
    }    
    
    private void RemoveReservation()
    {
        DisplayRooms();
        System.out.println("Which room would you like to check out?");
        
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {        
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice > 0 && choice < currentInstance.GetRooms().length){
                if (currentInstance.GetRooms()[choice].occupied)
                {
                    System.out.println("Room #" + choice + " removed, Receipt:\n");
                    System.out.println(currentInstance.LeaveRoom(choice));
                }
                else
                {
                    System.out.println("Room #" + choice + " is unoccupied.");
                }
                loop = false;
            }
            else                
            {
                System.out.println("Incorrect selection.");
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
