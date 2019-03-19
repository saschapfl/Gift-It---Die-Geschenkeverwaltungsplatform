/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
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

    private int like;
    private int dislike;
    private String name;
    private String price;
    private String description;
    private String link;
    private String user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "VOTE_COLLECTION")
    @MapKeyColumn(name = "VOTE_USER")
    @Column(name = "VOTE")
    Map<String, String> votes = new HashMap<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private RoomEntry room;

    public IdeaEntry() {

    }
    //<editor-fold defaultstate="collapsed" desc="Konstruktor">

    public IdeaEntry(int like, int dislike, String name, String price, String description, String link, RoomEntry room, String user) {
        this.like = like;
        this.dislike = dislike;
        this.name = name;
        this.price = price;
        this.description = description;
        this.link = link;
        this.room = room;
        this.user = user;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getter and setter">
    public RoomEntry getRoom() {
        return room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
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

//</editor-fold>
    public Map<String, String> getVotes() {
        return votes;
    }

    public void setVotes(Map<String, String> votes) {
        this.votes = votes;
    }
}
