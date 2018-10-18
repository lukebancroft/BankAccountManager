/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;


import entities.CompteBancaire;
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
@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable {
    private List<CompteBancaire> compteBancaireList;  
  
    @EJB  
    private CompteBancaireManager compteBancaireManager;  
  
    public CompteBancaireMBean() {  
    }  
  
    /** 
     * Renvoie la liste des comptes bancaires pour affichage dans une DataTable 
     * @return 
     */  
    public List<CompteBancaire>getCompteBancaires() {  
        return compteBancaireManager.getAllCompteBancaires();  
    }  
  
    /** 
     * Action handler - appelé lorsque l'utilisateur sélectionne une ligne dans 
     * la DataTable pour voir les détails 
     */  
    public String showDetails(int idCompteBancaire) {  
        return "CompteBancaireDetails?idCompteBancaire=" + idCompteBancaire;
    }  
    
    public String showOperations(int idCompteBancaire) {
        return "OperationsCompteBancaire?idCompteBancaire=" + idCompteBancaire;
    }
    
}
