/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Luke
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Client.findAll",
        query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById",
        query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.getNbClients", 
        query = "SELECT COUNT(c) FROM Client c")
})
public class Client extends Personne implements Serializable {

    private String nom;
    private String prenom;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="proprietaire")
    private Collection<CompteBancaire> comptes = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "client_compteBeneficiaire",
        joinColumns = @JoinColumn(name = "client_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name = "compteBancaire_id", referencedColumnName="id"))
    private List<CompteBancaire> comptesBeneficiaires = new ArrayList<>();

    public Client() {
    }
    
    public Client(String username, String password, String nom, String prenom) {
        super(username, password);
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Client(String username, String password, String nom, String prenom, Collection<CompteBancaire> comptes) {
        super(username, password);
        this.nom = nom;
        this.prenom = prenom;
        this.comptes = comptes;
    }

    @Override
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

    public List<CompteBancaire> getComptesBeneficiaires() {
        return comptesBeneficiaires;
    }

    @Override
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

    public void setComptesBeneficiaires(List<CompteBancaire> comptesBeneficiaires) {
        this.comptesBeneficiaires = comptesBeneficiaires;
    }
    
    public void addCompte(CompteBancaire compte) {
        comptes.add(compte);
        addCompteBeneficiaire(compte);
    }
 
    public void removeCompte(CompteBancaire compte) {
        comptes.remove(compte);
        removeCompteBeneficiaire(compte);
    }
    
    public void addCompteBeneficiaire(CompteBancaire compte) {
        comptesBeneficiaires.add(compte);
    }
 
    public void removeCompteBeneficiaire(CompteBancaire compte) {
        comptesBeneficiaires.remove(compte);
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
