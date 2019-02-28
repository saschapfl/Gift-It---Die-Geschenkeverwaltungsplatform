/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.ejb;

import dhbw.se.giftit.exc.UserExsists;
import dhbw.se.giftit.jpa.UserEntry;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sven
 */
@Stateless
public class UserBean {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Resource
    EJBContext ctx;
    
    // Liefert aktuelles User Objekt anhand von eindeutigem Usernamen zurück
    public UserEntry getUserByUname(String uname) {       
        return this.entityManager.find(UserEntry.class, uname);    
    }
    
    //<editor-fold defaultstate="collapsed" desc="registrieren">
    // aktuellen User registrieren
    public void registerUser(String uname, String password) throws UserExsists {
        if(entityManager.find(UserEntry.class, uname) != null) {
            throw new UserExsists("Der Benutzer ist bereits vorhanden!");
        }
        else {
            UserEntry User = new UserEntry(uname, password);
            User.addToGroup("giftit-secure");
            entityManager.persist(User);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="löschen">
    // User anahand von username löschen
    public void deleteUserByUname(String uname) {
        UserEntry user =  entityManager.find(UserEntry.class, uname);
        if( user != null) {
            entityManager.remove(user);
        }
    }
    
    //User anahand von Objekt löschen
    public void deleteUserByObject(UserEntry user){
        entityManager.remove(user);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="update">
    // User updaten anahnd uname
    public void updateUserByUname(String uname) {
        UserEntry user = entityManager.find(UserEntry.class, uname);
        if( user != null) {
            entityManager.merge(user);
        }
    }
    
    // User objekt updaten
    public void updateUserByObject(UserEntry user) {
        entityManager.merge(user);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="User holen">
    // aktuellen User holen
    public UserEntry getUser() {
        return this.entityManager.find(UserEntry.class, this.ctx.getCallerPrincipal().getName());
    }
    
    // alle User holen
    public List<UserEntry> getAllUsers() {
        List<UserEntry> allUsers = this.entityManager.createNamedQuery("TABLE_USERS.findAll").getResultList();
        return allUsers;
    }
    //</editor-fold>
    
    
}


