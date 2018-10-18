/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.OperationBancaire;
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

    public OperationBancaire update(OperationBancaire operationBancaire) {
        return em.merge(operationBancaire);
    }
    
    public void delete(OperationBancaire operationBancaire) {
        OperationBancaire entity = em.merge(operationBancaire);
        em.remove(entity);
    }
}
