/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.ejb;

import dhbw.se.giftit.jpa.*;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author spfli
 */
@Stateless
public class RoomBean {
    //Referenz auf Persistence Klasse holen
    @PersistenceContext
    EntityManager em;
    
    //<editor-fold defaultstate="collapsed" desc="Standardmethoden">
    public RoomEntry createNewRoom(Date deadlineCollection, Date deadlineRating, int budget, UserEntry purchaser, List<UserEntry> users) {
        RoomEntry room = new RoomEntry(deadlineCollection, deadlineRating, budget, purchaser, users);
        em.persist(room);
        return em.merge(room);
    }
    
    public List<RoomEntry> findAllRooms() {
        return em.createQuery("SELECT r FROM Room r ORDER BY r.id DESC").getResultList();
    }
    
    public RoomEntry findRoom(long id) {
        return em.find(RoomEntry.class, id);
    }
    
    public RoomEntry deleteWaste(long id) {
        RoomEntry room = em.find(RoomEntry.class, id);
        
        if (room != null) {
            em.remove(room);
        }
        
        return room;
    }
    
    public RoomEntry updateRoom(RoomEntry room) {
        return em.merge(room);
    }
//</editor-fold>
    
}
