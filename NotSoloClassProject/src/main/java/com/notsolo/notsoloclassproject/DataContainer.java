/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notsolo.notsoloclassproject;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bonkahe
 */
public class DataContainer implements Serializable {
    private static final long serialVersionUID = 1337L;
    
    public Room[] currentRooms;
    public Item[] currentMenu;
    
    public String employeeLogin;
    public String employeePassword;
}
