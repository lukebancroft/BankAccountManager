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
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Luke
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll",
        query = "SELECT cb FROM CompteBancaire cb"),
    @NamedQuery(name = "CompteBancaire.findById",
        query = "SELECT c FROM CompteBancaire c WHERE c.id = :id"),
    @NamedQuery(name = "CompteBancaire.findByNomProprietaire",
        query = "SELECT c FROM CompteBancaire c WHERE c.nomProprietaire = :nomProprietaire"),
    @NamedQuery(name = "CompteBancaire.getOperationsByCompteBancaireId",
        query = "SELECT o FROM CompteBancaire c join c.operations o WHERE c.id = :idCompteBancaire"),
    @NamedQuery(name = "CompteBanquaire.getNbComptes", 
        query = "SELECT COUNT(c) FROM CompteBancaire c")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nomProprietaire;
    private int solde;
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    private Collection<OperationBancaire> operations = new ArrayList<>();

    public CompteBancaire() {
    }
    
    public CompteBancaire(String nomProprietaire, int solde) {
        this.nomProprietaire = nomProprietaire;
        this.solde = solde;
        OperationBancaire op = new OperationBancaire("Création du compte", solde);  
        operations.add(op);
    }

    public Integer getId() {
        return id;
    }
    
    /**
     * Get the value of nomProprietaire
     *
     * @return the value of nomProprietaire
     */
    public String getNomProprietaire() {
        return nomProprietaire;
    }

    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public int getSolde() {
        return solde;
    }

    public Collection<OperationBancaire> getOperations() {
        return operations;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }
    
    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void setOperations(Collection<OperationBancaire> operations) {
        this.operations = operations;
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
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CompteBancaire[ compteBancaireId=" + id + " ]";
    }
    
    public void deposer(int montant) { 
        this.solde += montant;  
        OperationBancaire op = new OperationBancaire("Crédit", montant);  
        operations.add(op);
    }  
    
  public int retirer(int montant) {  
    if (montant < solde) {  
      solde -= montant;  
      OperationBancaire op = new OperationBancaire("Débit", montant);  
      operations.add(op);
      return montant;  
    } else {  
      return 0;  
    }  
  }  
    
}
