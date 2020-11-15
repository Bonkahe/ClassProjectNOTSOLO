/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.util.ArrayList;

/**
 *
 * @author Bonkahe
 */
public class Room {
    
    public boolean occupied;
    public String reservationName;
    private ArrayList<Order> pendingOrders;
    private ArrayList<Order> fullfilledOrders;

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
        pendingOrders.add(neworder);
    }
    
    public void CompleteOrder(int completedOrder)
    {
        fullfilledOrders.add(pendingOrders.get(completedOrder));
        pendingOrders.remove(completedOrder);
    }
}
