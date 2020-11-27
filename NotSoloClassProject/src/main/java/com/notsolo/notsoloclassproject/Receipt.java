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
public class Receipt {
    
    private String reservationName;
    private ArrayList<Order> orders;
    
    public Receipt(String reservationName, ArrayList<Order> orders)
    {
        this.reservationName = reservationName;
        this.orders = orders;
    }
    
    public double GetTotal()
    {
        double total = 0;
        for(int i = 0; i < orders.size(); i++)
        {
            total = total + (orders.get(i).currentItem.GetPrice() * orders.get(i).quantity);
        }
        
        return total;
    }
    
    @Override
    public String toString() { 
        String output = "Bill recipient:" + reservationName + "\nItems Ordered:";
        for(int i = 0; i < orders.size(); i++)
        {
            output = output + "\nItem #" + i + " details:" + orders.get(i).currentItem.GetReceipt();
            //output = output +  orders.get(i).currentItem.toString();
        }
        output = output + "\nTotal:$" + GetTotal();
        
        return output;
    } 
}
