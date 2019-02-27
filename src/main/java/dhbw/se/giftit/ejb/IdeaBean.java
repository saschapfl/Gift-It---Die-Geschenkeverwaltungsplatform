/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.ejb;

import dhbw.se.giftit.jpa.IdeaEntry;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Viktoria
 */
public class IdeaBean {
     //Referenz auf Persistence Klasse holen
    @PersistenceContext
    EntityManager em;
    
    
    //<editor-fold defaultstate="collapsed" desc="Standardmethoden">
    public IdeaEntry createNewIdea(int like, int dislike, String name, int price, String description, String link, String picture){
        IdeaEntry idea = new IdeaEntry(like, dislike, name, price, description, link, picture);
        em.persist(idea);
        return em.merge(idea);
    }
    
    public List<IdeaEntry> findAllIdeas() {
        return em.createQuery("SELECT i FROM Idea i ORDER BY i.id DESC").getResultList();
    }
    
    public IdeaEntry findIdea(long id) {
        return em.find(IdeaEntry.class, id);
    }
    
    public IdeaEntry deleteWaste(long id) {
        IdeaEntry idea = em.find(IdeaEntry.class, id);
        
        if (idea != null) {
            em.remove(idea);
        }
        
        return idea;
    }
    
    public IdeaEntry updateIdea(IdeaEntry idea) {
        return em.merge(idea);
    }
//</editor-fold>
}
