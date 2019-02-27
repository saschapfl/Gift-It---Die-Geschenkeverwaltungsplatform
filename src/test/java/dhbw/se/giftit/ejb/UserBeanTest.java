/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.ejb;

import dhbw.se.giftit.jpa.UserEntry;
import javax.ejb.embeddable.EJBContainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sven
 */
public class UserBeanTest {
    
    public UserBeanTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getUserByUname method, of class UserBean.
     */
    @org.junit.jupiter.api.Test
    public void testGetUserByUname() throws Exception {
        System.out.println("getUserByUname");
        String uname = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserBean instance = (UserBean)container.getContext().lookup("java:global/classes/UserBean");
        UserEntry expResult = null;
        UserEntry result = instance.getUserByUname(uname);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class UserBean.
     */
    @org.junit.jupiter.api.Test
    public void testRegisterUser() throws Exception {
        System.out.println("registerUser");
        String uname = "";
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserBean instance = (UserBean)container.getContext().lookup("java:global/classes/UserBean");
        instance.registerUser(uname, password);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
