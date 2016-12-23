/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pantelispanka
 */
@Entity
@Table(name = "user_passwords")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPasswords.findAll", query = "SELECT u FROM UserPasswords u")
    , @NamedQuery(name = "UserPasswords.findById", query = "SELECT u FROM UserPasswords u WHERE u.id = :id")
    , @NamedQuery(name = "UserPasswords.findByPassword", query = "SELECT u FROM UserPasswords u WHERE u.password = :password")
    , @NamedQuery(name = "UserPasswords.findByUserId", query = "Select u FROM UserPasswords u WHERE u.userId.id = :userId")
})
public class UserPasswords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private MinersUsers userId;

    public UserPasswords() {
    }

    public UserPasswords(Integer id) {
        this.id = id;
    }

    public UserPasswords(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MinersUsers getUserId() {
        return userId;
    }

    public void setUserId(MinersUsers userId) {
        this.userId = userId;
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
        if (!(object instanceof UserPasswords)) {
            return false;
        }
        UserPasswords other = (UserPasswords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.collectiv.minersapi.db.models.UserPasswords[ id=" + id + " ]";
    }
    
}
