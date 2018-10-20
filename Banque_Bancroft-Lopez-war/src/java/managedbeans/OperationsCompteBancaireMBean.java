/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.OperationBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import session.CompteBancaireManager;
import session.OperationBancaireManager;

/**
 *
 * @author Luke
 */
@Named
@ViewScoped  
public class OperationsCompteBancaireMBean implements Serializable {
    private List<OperationBancaire> operationBancaireList;
    private LazyDataModel<OperationBancaire> operationBancaireLazyList;
      
  @EJB  
  private CompteBancaireManager compteBancaireManager; 
  @EJB  
  private OperationBancaireManager operationBancaireManager; 
  
  private int idCompteBancaire;  

    /**
     * Creates a new instance of OperationsCompteBancaireMBean
     */
    public OperationsCompteBancaireMBean() {
        operationBancaireLazyList = new LazyDataModel<OperationBancaire>(){

            @Override
            public List load(int i, int i1, String string, SortOrder so, Map map) {
                List<OperationBancaire> operations = new ArrayList<OperationBancaire>();
                operations = operationBancaireManager.getLazyOperationBancaires(i, i1, idCompteBancaire);
                System.out.println("####### Taille du Lazy ####### : " + operations.size());
                return operations;
            }            
                          
            @Override     
            public int getRowCount() {       
                return operationBancaireManager.getNbOperations();      
            }
        };
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
    
    /** 
     * Renvoie la liste des operations bancaires avec lazy loading pour affichage dans une DataTable 
     * @return 
     */  
    public LazyDataModel getOperationBancairesLazy() {  
        return operationBancaireLazyList;  
    }
    
    public String list() {  
    System.out.println("###LIST###");  
    return "CompteBancaireList?faces-redirect=true";  
  }
    
}
