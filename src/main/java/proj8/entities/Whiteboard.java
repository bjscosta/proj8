/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author brunocosta
 */
@Entity
@Table(name = "witeboard")
@NamedQueries({
    @NamedQuery(name = Whiteboard.WHITEBOARD_FIND_BY_USERNAME, 
            query = "SELECT u FROM Whiteboard u WHERE u.username = :username")
})

public class Whiteboard implements Serializable {

    public static final String WHITEBOARD_FIND_BY_USERNAME = "Whiteboard.findByUser";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    
    @NotNull
    @Column(name = "user")
    private String username;
    
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "figure", nullable = false)
    private byte[] image;
    
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date saveDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Whiteboard)) {
            return false;
        }
        Whiteboard other = (Whiteboard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj8.entities.Whiteboard[ id=" + id + " ]";
    }
    
}
