/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;
import java.util.Scanner;

/**
 *
 * @author Bonkahe
 */
public class CustomerMenuHandler {
    private int roomId;
    public CustomerMenuHandler(int roomId)
    {
        this.roomId = roomId;
    }
    
    public void InRoute()
    {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            System.out.println("Would you like to:"+"\n"+"(1)Get menu"+"\n"+"(2)Place an Order"+"\n"+"(3)Remove an Order"+"\n"+"(4)See Orders"+"\n"+"(5)Get Reciept"+"\n"+"(6)Cash Out"+"\n"+"(7)Log out temporarily");
            int choice = scanner.nextInt();
            while(!scanner.hasNextInt() || choice > 7){
                System.out.println("Please enter a valid option (1 through 7)");
        }
            if(choice > 0 && choice < 7){
                switch(choice){
                    case 1:
                        GetMenu();
                        break;
                    case 2:
                        PlaceOrder();
                        break;
                    case 3:
                        RemoveOrder();
                        break;
                    case 4:
                        SeeOrders();
                        break;
                    case 5:
                        GetReceipt();
                        break;
                    case 6:
                        CashOut();
                        break;
                    default:
                        System.out.println("Thank you for using our program");
                        loop = false;
                        break;
                }
            }
        }
        scanner.close();
    }
    private static void GetMenu(){
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        Item[] item = currentInstance.GetMenu();
        //get the menu
        //print the menu using while loop
    }
    private static void PlaceOrder(){
        //take order as long as its from menu
        //send to addorder
        //add to receipt
    }
    private static void RemoveOrder(){
        //take as long as its been added to order already
        //remove from orders
    }
    private static void SeeOrders(){
        //print all orders
    }
    private static void GetReceipt(){
        //get receipt
        //print receipt
        //getroomreceipt()
    }
    private static void CashOut(){
        //pay bill
        //leave room
    }
}
