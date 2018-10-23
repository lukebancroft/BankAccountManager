/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Luke
 */
@Entity
public class CompteCourant extends CompteBancaire implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "compteCourant_client",
        joinColumns = @JoinColumn(name = "compteCourant_id"),
        inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> coproprietaires = new ArrayList<>();
    
    public CompteCourant() {
    }
    
    public CompteCourant(Client proprietaire, int solde) {
        super(proprietaire, solde);
        proprietaire.addCompteBeneficiaire(this);
    }
    
    public CompteCourant(Client proprietaire, int solde, List<Client> coproprietaires) {
        super(proprietaire, solde);
        this.coproprietaires = coproprietaires;
        proprietaire.addCompteBeneficiaire(this);
        for(Client copro : coproprietaires) {
            copro.addCompteBeneficiaire(this);
        }
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    public List<Client> getCoproprietaires() {
        return coproprietaires;
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCoproprietaires(List<Client> coproprietaires) {
        for(Client coproprietaire : this.coproprietaires) {
            coproprietaire.removeCompteBeneficiaire(this);
        }
        this.coproprietaires = coproprietaires;
        for(Client coproprietaire : this.coproprietaires) {
            coproprietaire.addCompteBeneficiaire(this);
        }
    }
    
    public void addCoproprietaire(Client client) {
        coproprietaires.add(client);
        client.addCompte(this);
        client.addCompteBeneficiaire(this);
    }
 
    public void removeCoproprietaire(Client client) {
        coproprietaires.remove(client);
        client.removeCompte(this);
        client.removeCompteBeneficiaire(this);
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
        if (!(object instanceof CompteCourant)) {
            return false;
        }
        CompteCourant other = (CompteCourant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompteCourant[ id=" + id + " ]";
    }
    
}
