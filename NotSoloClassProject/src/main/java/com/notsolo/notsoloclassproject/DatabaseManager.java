/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    public void InitializeDatabase()
    {
        currentData = new DataContainer();
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Please enter an admin username:");
        currentData.employeeLogin = scanner.nextLine();
        System.out.println("Please enter an admin password:");
        currentData.employeePassword = scanner.nextLine();
        System.out.println("Please enter how many rooms your facility has:");
       
        while (!scanner.hasNextInt()){
            scanner.nextLine();  // throw away the bad input
            System.out.println("Please enter a valid number");
        }
        int count = scanner.nextInt();
        currentData.currentRooms = new Room[count];
        
        SaveDatabase(currentData);
    }

    private void CreateMenu()
    {

    }

    private void FillRooms()
    {

    }

    /**
     * Should get the list of current rooms.
     * @return List current room list.
     */
    public Room[] GetRooms(){ return currentData.currentRooms; }

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
     */
    public void MakeRoomVacant(int roomId){ currentData.currentRooms[roomId].ClearRoom(); }

    /**
     * Will Fill the room with the users data.
     */
    public void MakeRoomFilled(){}
   
   
    private void SaveDatabase(Object serObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(curDirectoryPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            //System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Object LoadDatabase(){
        try { 
            FileInputStream fileIn = new FileInputStream(curDirectoryPath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj = objectIn.readObject();
 
            //System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
   
   
    private DatabaseManager() { }

    public static DatabaseManager getInstance( ) {
       return singleton;
    }
}
