/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Luke
 */
@Entity
public class CompteJoint extends CompteBancaire implements Serializable {

    @JoinColumn(name = "secondProprietaire", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch=FetchType.EAGER)
    private Client secondProprietaire;

    public CompteJoint() {
    }
    
    public CompteJoint(Client proprietaire ,Client secondProprietaire, int solde) {
        super(proprietaire, solde);
        this.secondProprietaire = secondProprietaire;
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    public Client getSecondProprietaire() {
        return secondProprietaire;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setSecondProprietaire(Client secondProprietaire) {
        this.secondProprietaire = secondProprietaire;
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
        if (!(object instanceof CompteJoint)) {
            return false;
        }
        CompteJoint other = (CompteJoint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompteJoint[ id=" + id + " ]";
    }
    
}
