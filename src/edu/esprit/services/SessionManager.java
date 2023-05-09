/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.codename1.io.Preferences;
import edu.esprit.entities.User;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    private static User u;

    public static User getU() {
        return u;
    }

    public static void setU(User u) {
        SessionManager.u = u;
    }
    
}
