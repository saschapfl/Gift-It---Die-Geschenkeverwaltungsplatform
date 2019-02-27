/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.exc;

/**
 *
 * @author sven
 */
public class UserExsists extends Exception{
    
    public UserExsists(String message) {
        super(message);
    }
}
