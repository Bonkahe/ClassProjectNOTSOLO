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
public class ItemMainCourse implements Item, Serializable{
    private static final long serialVersionUID = 1339L;
    private String title;
    private String description;
    private double price;
    private int calories;
    
    public ItemMainCourse(String title, String description, double price, int calories)
    {
        this.title = title;
        this.description = description;
        this.price = price;
        this.calories = calories;
    }
    
    @Override
    public String toString() { 
        return ("Main Course:" + title + "\nDescription:" + description + "\nPrice per order: $" + price + "\nDish adds " + calories + " calories.\n");
    } 

    @Override
    public double GetPrice() {
        return price;
    }

    @Override
    public String GetReceipt() {
        return (title + ": $" + price);
    }
}
