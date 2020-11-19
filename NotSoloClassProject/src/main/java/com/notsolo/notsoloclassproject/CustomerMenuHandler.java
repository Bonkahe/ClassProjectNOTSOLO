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
            System.out.println("Would you like to:"+"\n"+"(1)Get menu"+"\n"+"(2)Place an Order"+"\n"+"(3)See Orders"+"\n"+"(4)Get Reciept"+"\n"+"(5)Cash Out"+"\n"+"(6)Log out temporarily");
            int choice = scanner.nextInt();
            while(!scanner.hasNextInt()){
                System.out.println("Please enter a whole number.");
            }
            if(choice > 0 && choice < 6){
                switch(choice){
                    case 1:
                        GetMenu();
                        break;
                    case 2:
                        PlaceOrder();
                        break;
                    case 3:
                        SeeOrders();
                        break;
                    case 4:
                        GetReceipt();
                        break;
                    case 5:
                        CashOut();
                        loop = false;
                        break;
                    default:
                        System.out.println("Thank you for using our program");
                        loop = false;
                        break;
                }
            }
            else
            {
                System.out.println("Please enter a valid option (1 through 7).");
            }
        }
        scanner.close();
    }
    private void GetMenu(){
        //get the menu
        //print the menu using while loop
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        Item[] item = currentInstance.GetMenu();
        
        for (int i = 1; i < item.length; i++) {
            System.out.println("Item #" + i + ": " + item[i].toString());
        }
    }
    private void PlaceOrder(){
        GetMenu();
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        System.out.println("Select an option from the menu.");
        Item[] item = currentInstance.GetMenu();

        Scanner scanner = new Scanner(System.in);
        //int choice = scanner.nextInt();
        boolean loop = true;
        while(loop) {    
            while (!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("Please enter a number.");
            }
            int choice = scanner.nextInt();
            if(choice > 0 && choice < item.length){
                Order temp = new Order();
                temp.currentItem = item[choice];
                temp.quantity = 1;
                currentInstance.AddOrder(temp, roomId);
            }
            else                
            {
                System.out.println("Please select from the menu.");
            }
        }
        
        //take order as long as its from menu
        //send to addorder
        //add to receipt
    }
    private void SeeOrders(){
        //print all orders
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        Room[] rooms = currentInstance.GetRooms();
        rooms[roomId].toString();
    }
    private void GetReceipt(){
        //get receipt
        //print receipt
        //getroomreceipt()
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        Room[] rooms = currentInstance.GetRooms();
        Receipt receipt = rooms[roomId].GetReciept();
        receipt.toString();
    }
    private void CashOut(){
        //pay bill
        //leave room
        DatabaseManager currentInstance = DatabaseManager.getInstance();
        Room[] rooms = currentInstance.GetRooms();
    }
}
