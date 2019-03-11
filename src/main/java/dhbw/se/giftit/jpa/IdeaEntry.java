/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Viktoria Swidersky
 */
@Entity
@Table(name = "Idea_Table")
public class IdeaEntry implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String like;
    private String dislike;
    private String name;
    private String price;
    private String description;
    private String link;
    private String picture;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntry> users = new ArrayList<UserEntry>();
    
    @ManyToOne
    private RoomEntry room = null;
            
    public IdeaEntry (){
        
    }
    //<editor-fold defaultstate="collapsed" desc="Konstruktor">
    
    public IdeaEntry ( String like, String dislike, String name, String price, String description, String link, String picture, RoomEntry room, List<UserEntry> users){
        this.like = like;
        this.dislike = dislike;
        this.name = name;
        this.price = price;
        this.description = description;
        this.link = link;
        this.picture = picture;
        this.room = room;
        this.users = users;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getter and setter">
    public RoomEntry getRoom(){
        return room;
    }
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLike() {
        return like;
    }
    
    public void setLike(String like) {
        this.like = like;
    }
    
    public String getDislike() {
        return dislike;
    }
    
    public void setDislike(String dislike) {
        this.dislike = dislike;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public List<UserEntry> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntry> users) {
        this.users = users;
    }
    
//</editor-fold>


    
}
