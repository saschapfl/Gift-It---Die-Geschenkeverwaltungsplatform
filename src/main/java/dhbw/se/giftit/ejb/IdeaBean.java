/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.ejb;

import dhbw.se.giftit.jpa.IdeaEntry;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Viktoria
 */
@Stateless
public class IdeaBean {
    //Referenz auf Persistence Klasse holen

    @PersistenceContext
    EntityManager em;

    @EJB
    UserBean userBean;

    //<editor-fold defaultstate="collapsed" desc="Standardmethoden">
    public void CreateNewIdea(IdeaEntry idea) {
        em.persist(idea);
    }

    public List<IdeaEntry> findAllIdeas() {
        return em.createQuery("SELECT * FROM Idea_Table i ORDER BY i.id DESC").getResultList();
    }

    public IdeaEntry findIdea(long id) {
        return em.find(IdeaEntry.class, id);
    }

    public IdeaEntry deleteIdea(long id) {
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

    public void performAction(String id, String action) {
        IdeaEntry idea = em.find(IdeaEntry.class, Long.parseLong(id));
        Map<String, String> votes = idea.getVotes();
        String username = userBean.getUser().getUsername();
        switch (action) {
            case "like":
                if (votes.keySet().contains(username)) {
                    if (votes.get(username).equals("like")) {
                        votes.remove(username);
                        idea.setLike(idea.getLike() - 1);
                    }
                } else {
                    votes.put(username, "like");
                    idea.setLike(idea.getLike() + 1);
                }
                break;
            case "dislike":
                if (votes.keySet().contains(username)) {
                    if (votes.get(username).equals("dislike")) {
                        votes.remove(username);
                        idea.setDislike(idea.getDislike() - 1);
                    }
                } else {
                    votes.put(username, "dislike");
                    idea.setDislike(idea.getDislike() + 1);
                }
                break;
            case "delete":
                this.deleteIdea(Long.parseLong(id));
                break;
        }
    }

}
