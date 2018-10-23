/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Client;
import entities.CompteBancaire;
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
import session.ClientManager;
import session.CompteBancaireManager;
import session.OperationBancaireManager;

/**
 *
 * @author Luke
 */
@Named
@ViewScoped
public class OperationsClientMBean implements Serializable {

    private LazyDataModel<OperationBancaire> operationBancaireLazyList;
    private int idClient;
    private Client client;
    
    @EJB  
    private OperationBancaireManager operationBancaireManager; 
    @EJB  
    private ClientManager clientManager;
    @EJB  
    private CompteBancaireManager compteBancaireManager;
          
    public OperationsClientMBean() {
        
    }

    public void onLoad() {
        this.client = clientManager.getClient(Long.valueOf(idClient));
        
        operationBancaireLazyList = new LazyDataModel<OperationBancaire>(){
                
            @Override
            public List load(int i, int i1, String string, SortOrder so, Map map) {
            List<CompteBancaire> comptes = compteBancaireManager.getAllCompteBancairesByProprietaire(client);
            List<Integer> idComptes = new ArrayList<>();
                for(CompteBancaire cb : comptes) {
                    idComptes.add(cb.getId());
                }
                List<OperationBancaire> operations = operationBancaireManager.getLazyOperationBancairesByClient(i, i1, idComptes);
                return operations;
            }            
                          
            @Override     
            public int getRowCount() { 
            List<CompteBancaire> comptes = compteBancaireManager.getAllCompteBancairesByProprietaire(client);
            List<Integer> idComptes = new ArrayList<>();
                for(CompteBancaire cb : comptes) {
                    idComptes.add(cb.getId());
                }
                return operationBancaireManager.getNbOperationsByClient(idComptes);      
            }
        };
    }
    
    public int getIdClient() {
        return idClient;
    }

    public Client getClient() {
        return client;
    }

    public LazyDataModel<OperationBancaire> getOperationBancaireLazyList() {
        return operationBancaireLazyList;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOperationBancaireLazyList(LazyDataModel<OperationBancaire> operationBancaireLazyList) {
        this.operationBancaireLazyList = operationBancaireLazyList;
    }
    
    public String list() {  
        return "ClientCompteBancaireList?idClient= " + this.idClient + "&faces-redirect=true";  
      }
}
