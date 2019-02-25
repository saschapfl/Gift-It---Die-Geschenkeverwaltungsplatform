/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author spfli
 */
@Entity
public class RoomEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Date deadlineCollection;
    private Date deadlineRating;
    private int budget;
    private User purchaser;
    
    @ManyToMany
    private List<User> users;
    
    @OneToMany
    private List<Idea> ideas;
    
    public RoomEntry(){
        
    }
    
    public RoomEntry(Date deadlineCollection, Date deadlineRating, int budget, User purchaser, List<User> users){
        this.deadlineCollection = deadlineCollection;
        this.deadlineRating = deadlineRating;
        this.budget = budget;
        this.purchaser = purchaser;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDeadlineCollection(){
        return deadlineCollection;
    }
    
    public void setDeadlineCollection(Date deadlineCollection){
        this.deadlineCollection = deadlineCollection;
    }
    
    public Date getDeadlineRating(){
        return deadlineRating;
    }
    
    public void setDeadlineRating(Date deadlineRating){
        this.deadlineRating = deadlineRating;
    }
    
    public int getBudget(){
        return budget;
    }
    
    public void setBudget(int budget){
        this.budget = budget;
    }
    
    public User getPurchaser(){
        return purchaser;
    }
    
    public void setPurchaser(User purchaser){
        this.purchaser = purchaser;
    }
    
    public List<User> getUsers(){
        return users;
    }
    
    public void setUsers(List<User> users){
        this.users = users;
    }
    
    public List<Idea> getIdeas(){
        return ideas;
    }
    
    public void setIdeas(List<Idea> ideas){
        this.ideas = ideas;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomEntry)) {
            return false;
        }
        RoomEntry other = (RoomEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
