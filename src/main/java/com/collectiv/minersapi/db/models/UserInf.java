/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pantelispanka
 */
@Entity
@Table(name = "user_inf", catalog = "miners", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserInf.findAll", query = "SELECT u FROM UserInf u")
    , @NamedQuery(name = "UserInf.findById", query = "SELECT u FROM UserInf u WHERE u.id = :id")
    , @NamedQuery(name = "UserInf.findByDateUpdated", query = "SELECT u FROM UserInf u WHERE u.dateUpdated = :dateUpdated")
    , @NamedQuery(name = "UserInf.findByUserName", query = "SELECT u FROM UserInf u WHERE u.userName = :userName")})
public class UserInf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    @Size(max = 80)
    @Column(name = "user_name", length = 80)
    private String userName;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private MinersUsers userId;

    public UserInf() {
    }

    public UserInf(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (!(object instanceof UserInf)) {
            return false;
        }
        UserInf other = (UserInf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.collectiv.minersapi.db.models.UserInf[ id=" + id + " ]";
    }
    
}
