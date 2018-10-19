/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.OperationBancaire;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.CompteBancaireManager;

/**
 *
 * @author Luke
 */
@Named
@ViewScoped  
public class OperationsCompteBancaireMBean implements Serializable {
    private List<OperationBancaire> operationBancaireList;  
      
  @EJB  
  private CompteBancaireManager compteBancaireManager; 
  
  private int idCompteBancaire;  

    /**
     * Creates a new instance of OperationsCompteBancaireMBean
     */
    public OperationsCompteBancaireMBean() {
    }
    
    public int getIdCompteBancaire() {  
        return idCompteBancaire;  
    }
    
    public void setIdCompteBancaire(int idCompteBancaire) {  
        this.idCompteBancaire = idCompteBancaire;  
    }
    
    public List<OperationBancaire>getOperationsCompteBancaire() {  
        System.out.println("ID IS :" + idCompteBancaire);
        System.out.println(compteBancaireManager.getCompteBancaire(idCompteBancaire).getOperations().size());
        return compteBancaireManager.getOperationsbyCompteBancaireId(idCompteBancaire);
    }
    
    public String list() {  
    System.out.println("###LIST###");  
    return "CompteBancaireList?faces-redirect=true";  
  }
    
}
