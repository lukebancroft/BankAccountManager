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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luke
 */
@Entity
@Table(name = "COMPTEBANCAIRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT cb FROM CompteBancaire cb")})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer compteBancaireId;
    private String nom;
    private int solde;

    public CompteBancaire() {
    }
    
    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCompteBancaireId() {
        return compteBancaireId;
    }

    public void setCompteBancaireId(int compteBancaireId) {
        this.compteBancaireId = compteBancaireId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compteBancaireId != null ? compteBancaireId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.compteBancaireId == null && other.compteBancaireId != null) || (this.compteBancaireId != null && !this.compteBancaireId.equals(other.compteBancaireId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CompteBancaire[ compteBancaireId=" + compteBancaireId + " ]";
    }
    
    public void deposer(int montant) { 
        this.solde += montant;  
        
    }  
    
  public int retirer(int montant) {  
    if (montant < solde) {  
      solde -= montant;  
      return montant;  
    } else {  
      return 0;  
    }  
  }  
    
}
