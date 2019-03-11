/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.jpa;

import dhbw.se.giftit.web.HashGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "USER_TABLE")
public class UserEntry implements Serializable {
    
    @Id
    @Column(name = "USERNAME", length = 64)
    @Size(min = 5, max = 64, message = "Der Benutzername muss zwischen fünf und 64 Zeichen lang sein.")
    @NotNull(message = "Der Benutzername darf nicht leer sein.")
    private String username;
    
    @Column(name = "PASSWORD_HASH", length = 64)
    @NotNull(message = "Das Passwort darf nicht leer sein.")
    private String passwordHash;
    
    public class Password {
        @Size(min = 6, max = 64, message = "Das Passwort muss zwischen sechs und 64 Zeichen lang sein.")
        public String password = "";
    }    
    @Transient
    private final Password password = new Password();
    
    @ElementCollection
    @CollectionTable(
            name = "USER_GROUP",
            joinColumns = @JoinColumn(name = "USERNAME")
    )
    @Column(name = "GROUPNAME")
    List<String> groups = new ArrayList<>();
    
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    List<RoomEntry> raeume = new ArrayList<>();
    
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    List<IdeaEntry> idea = new ArrayList<>();
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktor">
    public UserEntry() {
        
    }
    
    public UserEntry(String username, String password) {
        this.username = username;
        this.password.password = password;
        this.passwordHash = HashGenerator.hashPassword(password);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoomEntry> getRaeume() {
        return raeume;
    }

    public void setRaeume(List<RoomEntry> raeume) {
        this.raeume = raeume;
    }

    public List<IdeaEntry> getIdea() {
        return idea;
    }

    public void setIdea(List<IdeaEntry> idea) {
        this.idea = idea;
    }
    

    public List<String> getGroups() {
    List<String> groupsCopy = new ArrayList<>();

    this.groups.forEach((groupname) -> {
        groupsCopy.add(groupname);
    });

    return groupsCopy;
    }

    public void addToGroup(String groupname) {
        if (!this.groups.contains(groupname)) {
            this.groups.add(groupname);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Password Verarbeitung">
    public Password getPassword() {
        return this.password;
    }
    
    // speichert password in nicht gespeichertes Feld password für spätere Validationprüfung und
    // legt password unter passwordHash ab
    public void setPassword(String password) {
        this.password.password = password;
        this.passwordHash = HashGenerator.hashPassword(password);
    }
    //</editor-fold>
    
    
}
