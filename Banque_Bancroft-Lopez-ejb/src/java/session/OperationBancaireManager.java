/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.OperationBancaire;
import entities.CompteBancaire;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luke
 */
@Stateless
@LocalBean
public class OperationBancaireManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerOperation(OperationBancaire o) {
        persist(o);
    }

    public OperationBancaire getOperationBancaire(int idOperationBancaire) {  
        return em.find(OperationBancaire.class, idOperationBancaire);  
    }  
    
    public List<OperationBancaire> getAllOperationBancaires() {
        Query query = em.createNamedQuery("OperationBancaire.findAll");  
        return query.getResultList();
    }
    
    
    public List<OperationBancaire> getLazyOperationBancaires(int start, int nbOperations, long idCompteBancaire){
        Query query =em.createNamedQuery("CompteBancaire.getOperationsByCompteBancaireId");
        query.setParameter("idCompteBancaire", idCompteBancaire);
        query.setFirstResult(start);
        query.setMaxResults(nbOperations);
        
        return query.getResultList();
    }
          
    public List<OperationBancaire> getLazyOperationBancairesByClient(int start, int nbOperations, List<Integer> idComptes){
        Query query = em.createNamedQuery("OperationBancaire.getOperationsByClient");
        query.setParameter("idComptes", idComptes);
        query.setFirstResult(start);
        query.setMaxResults(nbOperations);
        
        return query.getResultList();
    }
    
    public int getNbOperations(){
        Query query = em.createNamedQuery("OperationBancaire.getNbOperations");
        
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public int getNbOperationsByClient(List<Integer> idComptes){
        Query query = em.createNamedQuery("OperationBancaire.getNbOperationsByClient");
        query.setParameter("idComptes", idComptes);
        
        return ((Long) query.getSingleResult()).intValue();
    }

    public OperationBancaire update(OperationBancaire operationBancaire) {
        return em.merge(operationBancaire);
    }
    
    public void delete(OperationBancaire operationBancaire) {
        OperationBancaire entity = em.merge(operationBancaire);
        em.remove(entity);
    }
}
