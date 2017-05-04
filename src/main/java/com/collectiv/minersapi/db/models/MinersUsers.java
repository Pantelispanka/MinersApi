/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/**
 *
 * @author pantelispanka
 */
@Entity
@Table(name = "miners_users", catalog = "miners", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MinersUsers.findAll", query = "SELECT m FROM MinersUsers m")
    , @NamedQuery(name = "MinersUsers.findById", query = "SELECT m FROM MinersUsers m WHERE m.id = :id")
    , @NamedQuery(name = "MinersUsers.findByDateCreated", query = "SELECT m FROM MinersUsers m WHERE m.dateCreated = :dateCreated")
    , @NamedQuery(name = "MinersUsers.findByEmail", query = "SELECT m FROM MinersUsers m WHERE m.email = :email")})
public class MinersUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String email;
    
    @OneToOne(mappedBy = "userId")
    @Cascade({CascadeType.ALL, CascadeType.PERSIST})
    private UserPasswords userPasswords;
    
    @JoinColumn(name = "item_status", referencedColumnName = "id")
    @ManyToOne
    private ItemStatus itemStatus;
    @JoinColumn(name = "user_possition", referencedColumnName = "id")
    @ManyToOne
    private Possition userPossition;
    @JoinColumn(name = "user_role", referencedColumnName = "id")
    @ManyToOne
    private UserRole userRole;
    @OneToMany(mappedBy = "userId")
    @Cascade({CascadeType.ALL})
    private Collection<UserInf> userInfCollection;

    public MinersUsers() {
    }

    public MinersUsers(Integer id) {
        this.id = id;
    }

    public MinersUsers(Integer id, Date dateCreated, String email
            , UserPasswords pass, UserRole role, ItemStatus status,
            Possition poss) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.email = email;
        this.userPasswords = pass;
        this.userRole = role;
        this.itemStatus = status;
        this.userPossition = poss;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public UserPasswords getUserPasswords() {
        return userPasswords;
    }

    public void setUserPassword(UserPasswords userPasswords) {
        this.userPasswords = userPasswords;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Possition getUserPossition() {
        return userPossition;
    }

    public void setUserPossition(Possition userPossition) {
        this.userPossition = userPossition;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @XmlTransient
    public Collection<UserInf> getUserInfCollection() {
        return userInfCollection;
    }

    public void setUserInfCollection(Collection<UserInf> userInfCollection) {
        this.userInfCollection = userInfCollection;
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
        if (!(object instanceof MinersUsers)) {
            return false;
        }
        MinersUsers other = (MinersUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.collectiv.minersapi.db.models.MinersUsers[ id=" + id + " ]";
    }
    
}
