
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
            System.out.println("Would you like the manage customer orders(1), manage the database(2), or change database settings(3)? Type 4 to exit.");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice > 0 && choice < 5){
                switch(choice){
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
                        System.out.println("Returning to main menu...");
                        loop = false;
                        break;
                }
            }
            else                
            {
                System.out.println("Incorrect selection.");
            }
        }
    }
    
    private void OrderManager()
    {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {            
            System.out.println("Display Orders(1), Fullfill Order(2), Display Rooms(3), Remove Room Reservation(4), Return to Admin Menu(5)");
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice > 0 && choice < 6){
                switch(choice){
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
                        //RemoveReservation();
                        break;
                    default:
                        System.out.println("Returning to admin menu...");
                        loop = false;
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
        for (Room room : rooms) {
            System.out.println(room.toString());
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
