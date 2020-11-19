
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
    
    DatabaseManager currentInstance;
    
    /**
     * Called to begin the in employee menu loop, when returned will come back to the main menu.
     */
    public void InRoute()
    {
        currentInstance = DatabaseManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {            
            System.out.println("Manage Customer Orders(1), Manage the Database(2), Exit(0)");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice >= 0 && choice < 3){
                switch(choice){
                    case 0:
                        System.out.println("Returning to main menu...");
                        return;
                    case 1:
                        OrderManager();
                        break;
                    case 2:
                        DatabaseManager();
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
    
    /**
     * Handles the management of more important database options.
     */
    private void DatabaseManager()
    {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {            
            System.out.println("Change admin password(1), Return to Admin Menu(0)");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice >= 0 && choice < 2){
                switch(choice){
                    case 0:
                        System.out.println("Returning to admin menu...");
                        return;
                    case 1:
                        ChangePassword();
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
    
    /**
     * Takes input from user and changes the password, so long as they know the current one.
     */
    private void ChangePassword()
    {
        System.out.println("Please enter your current password:");
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();
        if (currentInstance.GetCredentials(input))
        {
            System.out.println("Please enter your new password:");
            input = scanner.nextLine();
            currentInstance.SetCredentials(input);
            
            System.out.println("Password changed to " + input + ", returning...");
        }
        else
        {
            System.out.println("Incorrect password, returning...");
        }
    }
    
    /**
     * Handles more day to day operation, specifically filling orders, printing off reciepts etc, 
     * when returning it will come back to the employee menu enroute.
     */
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
    
    /**
     * Handles fullfilling an order, it displays the menu, then requests the user to input a selection
     * to complete.
     */
    private void FullfillOrder()
    {
        ArrayList<Order> currentActiveOrders = DisplayOrders();
        if (currentActiveOrders.size() > 0)
        {
            System.out.println("Select an option, or -1 to return to main menu.");
            
            Scanner scanner = new Scanner(System.in);
            boolean loop = true;
            while(loop) {    
                while (!scanner.hasNextInt()){
                    scanner.nextLine();
                    System.out.println("Please enter a number.");
                }
                int choice = scanner.nextInt();
                if(choice >= -1 && choice < currentActiveOrders.size()){
                    if (choice == -1)
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
    
    /**
     * multi purpose display orders function, will display every active order 
     * that needs to be filled.
     * @return Returns the current order list, usefull to have at times.
     */
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
                System.out.println("Order #" + i + "\nContents: " + activeOrders.get(i).toString());
            }
        }
        else
        {
            System.out.println("No active orders.");
        }
        
        return activeOrders;
    }
    /**
     * more or less the same as the display orders function but a little less
     * complicated, and handles rooms instead.
     * @return The current array of rooms.
     */
    private Room[] DisplayRooms()
    {
        Room[] rooms = currentInstance.GetRooms();
        for (int i = 0; i < rooms.length; i++) {
            System.out.println(rooms[i].toString());
        }
        return rooms;
    }    
    
    /**
     * Handles removing a room from the database, allows the user to select
     * from a list of all the rooms, and then removes them printing out the
     * receipt for the room.
     */
    private void RemoveReservation()
    {
        Room[] currooms = DisplayRooms();
        System.out.println("Which room would you like to check out? (type -1 to back out)");
        
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {        
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice >= -1 && choice < currooms.length){
                if (choice == -1)
                {
                    return;
                }
                
                if (currooms[choice].occupied)
                {
                    System.out.println("Room #" + choice + " removed,\n Receipt:\n");
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
}
