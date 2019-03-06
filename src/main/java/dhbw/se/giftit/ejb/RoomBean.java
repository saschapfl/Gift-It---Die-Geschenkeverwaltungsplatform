/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.ejb;

import dhbw.se.giftit.jpa.*;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
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
    
    @Resource
    EJBContext ctx;
    
    //<editor-fold defaultstate="collapsed" desc="Standardmethoden">
    public RoomEntry createNewRoom(RoomEntry room) {
        em.persist(room);
        return em.merge(room);
    }
    
    public List<RoomEntry> findAllRooms() {
        return em.createQuery("SELECT r FROM RoomEntry r ORDER BY r.id DESC").getResultList();
    }
    
    public RoomEntry findRoom(long id) {
        return em.find(RoomEntry.class, id);
    }
    
    public RoomEntry deleteRoom(long id) {
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
