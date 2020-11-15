/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.util.List;
import java.io.Serializable;

/**
 *
 * @author Bonkahe
 */
public class Room {
    
    public boolean occupied;
    public String reservationName;
    public List<Order> orders;
    
    public Receipt GetReciept()
    {
        return new Receipt();
    }
    
    public void ClearRoom()
    {
        
    }
    
    public void GetCurrentOrders()
    {
        
    }
    
    public void AddOrder()
    {
        
    }
    
    public void CompleteOrder()
    {
        
    }
}
