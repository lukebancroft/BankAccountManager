/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Luke
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Administrateur.findAll",
        query = "SELECT a FROM Administrateur a"),
    @NamedQuery(name = "Administrateur.findById",
        query = "SELECT a FROM Administrateur a WHERE a.id = :id"),
    @NamedQuery(name = "Administrateur.getNbAdministrateurs", 
        query = "SELECT COUNT(a) FROM Administrateur a")
})
public class Administrateur extends Personne implements Serializable {

    private Long numAdministrateur;
    
    public Administrateur() {
        
    }

    public Administrateur(String username, String password, Long numAdministrateur) {
        super(username, password);
        this.numAdministrateur = numAdministrateur;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public Long getNumAdministrateur() {
        return numAdministrateur;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setNumAdministrateur(Long numAdministrateur) {
        this.numAdministrateur = numAdministrateur;
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
        if (!(object instanceof Administrateur)) {
            return false;
        }
        Administrateur other = (Administrateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Administrateur[ id=" + id + " ]";
    }
    
}
