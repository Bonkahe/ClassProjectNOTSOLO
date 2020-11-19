/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*; 

/**
 *
 * @author Bonkahe
 */
public class DatabaseManager {
    private static DatabaseManager singleton = new DatabaseManager( );

    public String curDirectoryPath;

    private DataContainer currentData;

    /**
     * Checks if database file exists, if so loads it, if not initializes the process to create the database.
     */
    public void CheckDatabaseIntegrity()
    {
        File tempFile = new File(curDirectoryPath);
        boolean exists = tempFile.exists();
        if (exists)
        {
            currentData = (DataContainer) LoadDatabase();
        }
        else
        {
            System.out.println("WARNING: No Database file found, please create a new one.");
            InitializeDatabase();
        }
    }

    /**
     * Will allow the user to input options to create the database objects.
     */
    private void InitializeDatabase()
    {
        currentData = new DataContainer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an admin password:");
        currentData.employeePassword = scanner.nextLine();
        System.out.println("Please enter how many rooms your facility has:");
       
        while (!scanner.hasNextInt()){
            scanner.nextLine();  // throw away the bad input
            System.out.println("Please enter a valid number");
        }
        int count = scanner.nextInt();
        currentData.currentRooms = new Room[count];
        for (int i = 0; i < count; i++)
        {
            currentData.currentRooms[i] = new Room(i);
        }
        CreateMenu();
        
        SaveDatabase(currentData);
    }
    
    /**
     * Checks if the given password matches the one in the database.
     * @param inputPassword the password attempt to verify.
     * @return true if it is correct, false if the password given is incorrect.
     */
    public boolean GetCredentials(String inputPassword){ return currentData.employeePassword.equals(inputPassword); }
    
    public void SetCredentials(String inputPassword)
    {
        currentData.employeePassword = inputPassword;
        SaveDatabase(currentData);
    }

    /**
     * Should get the list of current rooms.
     * @return List current room list.
     */
    public Room[] GetRooms(){ return currentData.currentRooms; }
    
    /**
     * Get first room listed with given reservation name.
     * @param reservationName name to search for.
     * @return int room id of found room, -1 if not found.
     */
    public int GetReservedRoom(String reservationName)
    {
        for (int i = 0; i < currentData.currentRooms.length; i++)
        {
            if (currentData.currentRooms[i].occupied)
            {
                if (currentData.currentRooms[i].reservationName.equals(reservationName))
                { 
                    return i;
                }
            }
        }        
        return -1;
    }

    /**
     * Gets list of current Menu Items.
     * @return List returns current menu.
     */
    public Item[] GetMenu(){ return currentData.currentMenu; }

    /**
     * Gets the current bill for a given room.
     * 
     * @param roomId The room id to be retrieved, to get a list of all current rooms use GetRooms() method.
     * 
     * @return Receipt current receipt for the room containing all costs.
     */
    public Receipt GetRoomRecipt(int roomId){ return currentData.currentRooms[roomId].GetReciept(); }

    /**
     * Remove the current occupant of the given room.
     * 
     * @param roomId The room id to be retrieved, to get a list of all current rooms use GetRooms() method.
     * @return The receipt containing all delievered orders to this room, as well as the total owed.
     */
    public Receipt LeaveRoom(int roomId)
    { 
        Receipt curReceipt = currentData.currentRooms[roomId].GetReciept();
        currentData.currentRooms[roomId].ClearRoom(); 
        SaveDatabase(currentData);
        return curReceipt;
    }

    /**
     * Will Fill the room with the users data.
     * @param roomId The room id to fill.
     * @param reservationName the name to fill the room under.
     */
    public void FillRoom(int roomId, String reservationName){
        if (currentData.currentRooms[roomId].occupied)
        {
            System.out.println("Returned: Room occupied.");
        }
        else
        {
            currentData.currentRooms[roomId].occupied = true;
            currentData.currentRooms[roomId].reservationName = reservationName;
        }
        SaveDatabase(currentData);
    }
    
    /**
     * Adds an order to the given room.
     * @param addedOrder specific order to add.
     * @param roomId The room id to add an order.
     */
    public void AddOrder(Order addedOrder, int roomId)
    {
        currentData.currentRooms[roomId].AddOrder(addedOrder);
        SaveDatabase(currentData);
    }
    
    /**
     * Adds an order to the given room.
     * @param completedOrder order id to complete.
     * @param roomId The room id to add an order.
     */
    public void CompleteOrder(Order completedOrder, int roomId)
    {
        currentData.currentRooms[roomId].CompleteOrder(completedOrder);
        SaveDatabase(currentData);
    }
   
    /**
     * Outputs the given object to the curDirectoryPath.
     */
    private void SaveDatabase(Object serObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(curDirectoryPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Imports the object from the curDirectoryPath.
     */
    private Object LoadDatabase(){
        try { 
            FileInputStream fileIn = new FileInputStream(curDirectoryPath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn); 
            Object obj = objectIn.readObject(); 
            objectIn.close();
            return obj;
 
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Handles generation of menu items.
     */
    private void CreateMenu()
    {
        Item[] tempitems = new Item[]{
            new ItemMainCourse("Salmon Filete", "A fresh salmon chop prepared on a bed of white rice.", 12.99, 1100),
            new ItemMainCourse("Angus Burger", "Quarter pound of fresh angus beef with lettuce mayo and ketchup.", 9.99, 1500),
            new ItemMainCourse("Grilled Chicken", "Chicken grilled to perfection, served with a bed of white rice.", 13.99, 1200),
            new ItemSideDish("Mashed Potatoes", "American classic mashed potatoes, served with white gravy.", 2.99, 500),
            new ItemSideDish("Green Beans", "A southern classic, in house snapped green beans.", 1.50, 120),
            new ItemSideDish("Baked Potatoe", "A large Idaho potatoe, served with butter and condiments.", 2.50, 600),
            new ItemSideDish("Baked Beans", "Sweet southern baked beans, with maple bacon.", 2.50, 500),
            new ItemSideDish("Brussel Sprouts", "Sauteed in butter and lightly steamed. ", 2.99, 450)
        };
        
        currentData.currentMenu = tempitems;
    }
   
   
    private DatabaseManager() { }

    public static DatabaseManager getInstance( ) {
       return singleton;
    }
}
