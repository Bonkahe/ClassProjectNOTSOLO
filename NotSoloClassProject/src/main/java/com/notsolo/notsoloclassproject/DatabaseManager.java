/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.util.*; 

/**
 *
 * @author Bonkahe
 */
public class DatabaseManager {
   private static DatabaseManager singleton = new DatabaseManager( );

   private List<Room> currentRooms;
   
   private List<Item> currentMenu;
   
   
   public void InitializeDatabase()
   {
       
   }
   
   /**
    * Should get the list of current rooms.
    * @return List current room list.
    */
   public List<Room> GetRooms(){ return currentRooms; }
   
   /**
    * Gets list of current Menu Items.
    * @return List returns current menu.
    */
   public List<Item> GetMenu(){ return currentMenu; }
   
   /**
    * Gets the current bill for a given room.
    * 
    * @param roomId The room id to be retrieved, to get a list of all current rooms use GetRooms() method.
    * 
    * @return Receipt current receipt for the room containing all costs.
    */
   public Receipt GetRoomRecipt(int roomId){ return currentRooms.get(roomId).GetReciept(); }
      
   /**
    * Remove the current occupant of the given room.
    * 
    * @param roomId The room id to be retrieved, to get a list of all current rooms use GetRooms() method.
    */
   public void MakeRoomVacant(int roomId){ currentRooms.get(roomId).ClearRoom(); }
   
   /**
    * Will Fill the room with the users data.
    */
   public void MakeRoomFilled(){}
   
   
   
   private DatabaseManager() { }

   public static DatabaseManager getInstance( ) {
      return singleton;
   }
}
