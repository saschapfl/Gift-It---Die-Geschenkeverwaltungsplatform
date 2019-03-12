/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.ejb;

import dhbw.se.giftit.ejb.IdeaBean;
import dhbw.se.giftit.ejb.UserBean;
import dhbw.se.giftit.jpa.IdeaEntry;
import dhbw.se.giftit.jpa.UserEntry;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sven
 */

// Diese Klasse dient zum Bewerten(Like,Dislike und remove likes, dislikes ) von Ideen
@Stateless
public class IdeaRatingBean {
   
    @EJB
    IdeaBean ideaBean;
    
    @EJB
    UserBean userBean;
    
    public void like(long id, HttpServletRequest request) {      
        IdeaEntry idea = ideaBean.findIdea(id);
        UserEntry user = userBean.getUser();
        List<UserEntry> listusersliked = idea.getUsersLiked();

        String flag = watchUserInList(id);
        
        if(flag != "false") {
            // User Liste holen und mit aktuellen User füllen
            listusersliked.add(user);
            idea.setUsersLiked(listusersliked);
            // Like's holen und eins hochzählen
            String like = idea.getLike();
            int likes = Integer.parseInt(like);
            likes = likes + 1;
            like = Integer.toString(likes);
            idea.setLike(like);
            // IdeaObject einmal updaten
            ideaBean.updateIdea(idea);
        } else {
            // error Message senden das User bereits gevotet hat!
            // Session holen und Ideas an jsp weiterleiten
            HttpSession session = request.getSession();
            String error = "Sie haben bereits für diese Idee gevotet!";
            session.setAttribute("error", error);
        }
    }
    
    public void dislike(long id) {
        IdeaEntry idea = ideaBean.findIdea(id);
        UserEntry user = userBean.getUser();
        List<UserEntry> listusersdisliked = idea.getUsersDisliked();
        
        String flag = watchUserInList(id);
        if (flag != "false") {
            // User Liste holen und mit aktuellen User füllen
            listusersdisliked.add(user);
            idea.setUsersDisliked(listusersdisliked);
            // Dislikes's holen und eins hochzählen
            String dislike = idea.getDislike();
            int dislikes = Integer.parseInt(dislike);
            dislikes = dislikes + 1;
            dislike = Integer.toString(dislikes);
            idea.setDislike(dislike);
            // IdeaObject einmal updaten
            ideaBean.updateIdea(idea);
        }
    }
    
    public String watchUserInList(long id) {
        String flag2 = "true";
        IdeaEntry idea = ideaBean.findIdea(id);
        UserEntry user = userBean.getUser();
        String uname = user.getUsername();
        List<UserEntry> listusersdisliked = idea.getUsersDisliked();
        List<UserEntry> listusersliked = idea.getUsersLiked();

        for(UserEntry acc: listusersliked) {
            if(acc.getUsername().equals(uname)) {
                flag2 = "false";
            }     
        }
        for(UserEntry acc: listusersdisliked) {
            if(acc.getUsername().equals(uname)) {
                flag2 = "false";
            }     
        }
        return flag2;
    }
    
    public void removeRating(long id) {
        
        String flag2 = null;
        IdeaEntry idea = ideaBean.findIdea(id);
        UserEntry user = userBean.getUser();
        String uname = user.getUsername();
        List<UserEntry> listusersdisliked = idea.getUsersDisliked();
        List<UserEntry> listusersliked = idea.getUsersLiked();
        
        UserEntry account = null;
        for(UserEntry acc: listusersliked) {
            if(acc.getUsername().equals(uname)) {
                account = acc;
                flag2 = "like";
            }     
        }
        for(UserEntry acc: listusersdisliked) {
            if(acc.getUsername().equals(uname)) {
                account = acc;
                flag2 = "dislike";
            }     
        }

        if (flag2 == "like") {
            listusersliked.remove(account);
            idea.setUsersLiked(listusersliked);
            String like = idea.getLike();
            int likes = Integer.parseInt(like);
            likes = likes - 1;
            like = Integer.toString(likes);
            idea.setLike(like);

        } else {
            listusersdisliked.remove(account);
            idea.setUsersLiked(listusersdisliked);
            String dislike = idea.getDislike();
            int dislikes = Integer.parseInt(dislike);
            dislikes = dislikes - 1;
            dislike = Integer.toString(dislikes);
            idea.setDislike(dislike);
        }
        ideaBean.updateIdea(idea);
    }
}
