/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.io.Serializable;

/**
 *
 * @author Bonkahe
 */
public class Order implements Serializable{
    private static final long serialVersionUID = 1340L;
    public Item currentItem;
    public int quantity;
    public int roomid;
    
    @Override
    public String toString() { 
        String returnstring = "";
        returnstring += currentItem.toString() + "\n";
        returnstring += "Quantity: " + quantity + "\n";
        
        return returnstring;
    } 
}
