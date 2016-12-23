/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pantelispanka
 */
@Entity
@Table(name = "possition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Possition.findAll", query = "SELECT p FROM Possition p")
    , @NamedQuery(name = "Possition.findById", query = "SELECT p FROM Possition p WHERE p.id = :id")
    , @NamedQuery(name = "Possition.findByPossition", query = "SELECT p FROM Possition p WHERE p.possition = :possition")})
public class Possition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 80)
    @Column(name = "possition")
    private String possition;
    @OneToMany(mappedBy = "userPossition")
    private Collection<MinersUsers> minersUsersCollection;

    public Possition() {
    }

    public Possition(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPossition() {
        return possition;
    }

    public void setPossition(String possition) {
        this.possition = possition;
    }

    @XmlTransient
    public Collection<MinersUsers> getMinersUsersCollection() {
        return minersUsersCollection;
    }

    public void setMinersUsersCollection(Collection<MinersUsers> minersUsersCollection) {
        this.minersUsersCollection = minersUsersCollection;
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
        if (!(object instanceof Possition)) {
            return false;
        }
        Possition other = (Possition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.collectiv.minersapi.db.models.Possition[ id=" + id + " ]";
    }
    
}
