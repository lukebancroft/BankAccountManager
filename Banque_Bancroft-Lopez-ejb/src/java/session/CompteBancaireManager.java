/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
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
public class CompteBancaireManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        persist(c);
    }

    public CompteBancaire getCompteBancaire(int idCompteBancaire) {  
        return em.find(CompteBancaire.class, idCompteBancaire);  
    }  
    
    public List<CompteBancaire> getAllCompteBancaires() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");  
        return query.getResultList();
    }

    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    public void delete(CompteBancaire compteBancaire) {
        CompteBancaire entity = em.merge(compteBancaire);
        em.remove(entity);
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerComptesTest() {  
        creerCompte(new CompteBancaire("John Lennon", 150000));  
        creerCompte(new CompteBancaire("Paul McCartney", 950000));  
        creerCompte(new CompteBancaire("Ringo Starr", 20000));  
        creerCompte(new CompteBancaire("Georges Harrisson", 100000));  
    } 

    public List<OperationBancaire> getOperationsbyCompteBancaireId(long idCompteBancaire) {
        Query query = em.createNamedQuery("CompteBancaire.getOperationsByCompteBancaireId");
        query.setParameter("idCompteBancaire", idCompteBancaire);
        return query.getResultList();
  }
}
