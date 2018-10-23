/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Client;
import entities.CompteBancaire;
import entities.CompteJoint;
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
import session.CompteJointManager;

/**
 *
 * @author Luke
 */
@Named
@ViewScoped
public class ClientCompteBancaireListMBean implements Serializable {
    private int idClient; 
    private LazyDataModel<CompteBancaire> compteBancaireLazyList;
    private LazyDataModel<CompteBancaire> compteJointLazyList;
    private Client client;  
    
    @EJB  
    private CompteBancaireManager compteBancaireManager;
    @EJB
    private ClientManager clientManager;
    @EJB
    private CompteJointManager compteJointManager;
    
    public ClientCompteBancaireListMBean() {
        compteBancaireLazyList = new LazyDataModel<CompteBancaire>(){

            @Override
            public List load(int i, int i1, String string, SortOrder so, Map map) {
                List<CompteBancaire> comptes = compteBancaireManager.getLazyCompteBancairesByClient(i, i1, client);
                return comptes;
            }            
                          
            @Override     
            public int getRowCount() {       
                return compteBancaireManager.getNbComptesByClient(client);      
            }
        };
        
        compteJointLazyList = new LazyDataModel<CompteBancaire>(){

            @Override
            public List load(int i, int i1, String string, SortOrder so, Map map) {
                List<CompteJoint> comptes = compteJointManager.getLazyComptesJointBySecondProprio(i, i1, client);
                return comptes;
            }            
                          
            @Override     
            public int getRowCount() {       
                return compteJointManager.getNbComptesJointBySecondProprio(client);      
            }
        };
    }

    public int getIdClient() {
        return idClient;
    }

    public LazyDataModel<CompteBancaire> getCompteBancaireLazyList() {
        return compteBancaireLazyList;
    }

    public LazyDataModel<CompteBancaire> getCompteJointLazyList() {
        return compteJointLazyList;
    }

    public Client getClient() {
        return client;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setCompteBancaireLazyList(LazyDataModel<CompteBancaire> compteBancaireLazyList) {
        this.compteBancaireLazyList = compteBancaireLazyList;
    }

    public void setCompteJointLazyList(LazyDataModel<CompteBancaire> compteJointLazyList) {
        this.compteJointLazyList = compteJointLazyList;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public void loadCompteBancaire() {   
        this.client = clientManager.getClient(Long.valueOf(idClient));
    }
    
    public String showOperations() {  
        return "OperationsClient?idClient=" + this.idClient;
    } 
    
}
