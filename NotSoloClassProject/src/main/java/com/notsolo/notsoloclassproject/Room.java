/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bonkahe
 */
public class Room implements Serializable{
    private static final long serialVersionUID = 1341L;
    public int roomid;
    public boolean occupied;
    public String reservationName;
    public ArrayList<Order> pendingOrders;
    public ArrayList<Order> fullfilledOrders;

    public Room() {
        this.pendingOrders = new ArrayList<>();
        this.fullfilledOrders = new ArrayList<>();
    }
    
    public Receipt GetReciept()
    {
        return new Receipt(reservationName, fullfilledOrders);
    }
    
    public void ClearRoom()
    {
        reservationName = "";
        pendingOrders.clear();
        fullfilledOrders.clear();
    }
    
    public ArrayList<Order> GetCurrentOrders()
    {
        return pendingOrders;
    }
    
    public ArrayList<Order> GetFilledOrders()
    {
        return fullfilledOrders;
    }
    
    public void AddOrder(Order neworder)
    {
        neworder.roomid = roomid;
        pendingOrders.add(neworder);
    }
    
    public void CompleteOrder(Order completedOrder)
    {
        fullfilledOrders.add(completedOrder);
        pendingOrders.remove(completedOrder);
    }
    
    @Override
    public String toString() { 
        String returnstring = "";
        if (occupied)
        {
            returnstring += "Room #" + roomid + " is occupied under " + reservationName + ".\n"; 
            returnstring += "Information:\n";
            returnstring += pendingOrders.size() + " Pending orders.\n";
            for (int j = 0; j < pendingOrders.size(); j++)
            {
                returnstring += pendingOrders.get(j).toString();
            }
            returnstring += fullfilledOrders.size() + " Pending orders.\n";
            for (int j = 0; j < fullfilledOrders.size(); j++)
            {
                returnstring += fullfilledOrders.get(j).toString();
            }
        }
        else
        {
            returnstring += "Room #" + roomid + " is unoccupied.\n";
        }
        return returnstring;
    } 
}
