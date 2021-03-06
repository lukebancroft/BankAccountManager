/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Conseiller;
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
public class ConseillerManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerConseiller(Conseiller c) {
        persist(c);
    }

    public int getNbConseillers(){
        Query query = em.createNamedQuery("Conseiller.getNbConseillers");
        
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public List<Conseiller> getAllConseillers() {
        Query query = em.createNamedQuery("Conseiller.findAll");  
        return query.getResultList();
    }
    
}
