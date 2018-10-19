/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Luke
 */
@Entity
public class Client extends Personne implements Serializable {

    private String nom;
    private String prenom;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="proprietaire")
    private Collection<CompteBancaire> comptes = new ArrayList<>();

    public Client() {
    }
    
    public Client(String username, String password, String nom, String prenom) {
        super(username, password);
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getId() {
        return id;
    }
    
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Collection<CompteBancaire> getComptes() {
        return comptes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setComptes(Collection<CompteBancaire> comptes) {
        this.comptes = comptes;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ id=" + id + " ]";
    }

    
}