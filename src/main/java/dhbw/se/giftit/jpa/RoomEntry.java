/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author spfli
 */
@Entity
public class RoomEntry implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Date deadlineCollection;
    private Date deadlineRating;
    private double entireBudget = 0.0;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntry> users = new ArrayList<UserEntry>();

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<IdeaEntry> ideas = new ArrayList<IdeaEntry>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "BUDGET_COLLECTION")
    @MapKeyColumn(name = "BUDGET_USER")
    @Column(name = "BUDGET_BUDGET")
    Map<String, Double> budget = new HashMap<>();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public RoomEntry() {

    }

    public RoomEntry(String name, Date deadlineCollection, Date deadlineRating, HashMap<String, Double> budget, List<UserEntry> users) {
        this.name = name;
        this.deadlineCollection = deadlineCollection;
        this.deadlineRating = deadlineRating;
        this.budget = budget;
        this.users = users;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter und Setter">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeadlineCollection() {
        return deadlineCollection;
    }

    public void setDeadlineCollection(Date deadlineCollection) {
        this.deadlineCollection = deadlineCollection;
    }

    public Date getDeadlineRating() {
        return deadlineRating;
    }

    public void setDeadlineRating(Date deadlineRating) {
        this.deadlineRating = deadlineRating;
    }

    public Map<String, Double> getBudget() {
        return budget;
    }

    public void setBudget(HashMap<String, Double> budget) {
        this.budget = budget;
    }

    public List<UserEntry> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntry> users) {
        this.users = users;
    }

    public List<IdeaEntry> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<IdeaEntry> ideas) {
        this.ideas = ideas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEntireBudget() {
        return entireBudget;
    }

    public void setEntireBudget(double entireBudget) {
        this.entireBudget = entireBudget;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Auto-Generierter Kram">
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
//</editor-fold>

    public void addUserToRoom(UserEntry user) {
        this.users.add(user);
    }

}
