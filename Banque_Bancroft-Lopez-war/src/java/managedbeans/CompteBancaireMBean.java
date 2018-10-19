/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;


import entities.Client;
import entities.CompteBancaire;
import entities.Personne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import session.ClientManager;
import session.CompteBancaireManager;

/**
 *
 * @author Luke
 */
@Named
@ViewScoped
public class CompteBancaireMBean implements Serializable {
    private List<CompteBancaire> compteBancaireList; 
    private LazyDataModel<CompteBancaire> compteBancaireLazyList;
  
    @EJB  
    private CompteBancaireManager compteBancaireManager;
  
    public CompteBancaireMBean() {  
        compteBancaireLazyList = new LazyDataModel<CompteBancaire>(){

            @Override
            public List load(int i, int i1, String string, SortOrder so, Map map) {
                List<CompteBancaire> comptes = new ArrayList<CompteBancaire>();
                comptes = compteBancaireManager.getLazyCompteBancaires(i, i1);
                System.out.println("####### Taille du Lazy ####### : " + comptes.size());
                return comptes;
            }            
                          
            @Override     
            public int getRowCount() {       
                return compteBancaireManager.getNbComptes();      
            }
        };
    }
  
    /** 
     * Renvoie la liste des comptes bancaires pour affichage dans une DataTable 
     * @return 
     */  
    public List<CompteBancaire>getCompteBancaires() {  
        return compteBancaireManager.getAllCompteBancaires();  
    } 
    
    /** 
     * Renvoie la liste des comptes bancaires avec lazy loading pour affichage dans une DataTable 
     * @return 
     */  
    public LazyDataModel getCompteBancairesLazy() {  
        return compteBancaireLazyList;  
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
    
    /*public void onIndexLoad() {
        Client c1 = new Client("lulu", "123", "luke", "br");
        Client c2 = new Client("fabi", "123", "fabien", "l");
        Client c3 = new Client("amo", "123", "amosse", "a");
        
        CompteBancaire cb1 = new CompteBancaire(c1, 100);
        CompteBancaire cb2 = new CompteBancaire(c1, 1200);
        CompteBancaire cb3 = new CompteBancaire(c2, 2000);
        CompteBancaire cb4 = new CompteBancaire(c3, 31000);
        cm.persist(c1);
        compteBancaireManager.persist(cb1);
    }*/
}