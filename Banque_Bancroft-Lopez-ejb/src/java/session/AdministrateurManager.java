/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Administrateur;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luke
 */
@Stateless
@LocalBean
public class AdministrateurManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerAdministrateur(Administrateur a) {
        persist(a);
    }

    public int getNbAdministrateurs(){
        Query query = em.createNamedQuery("Administrateur.getNbAdministrateurs");
        
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public List<Administrateur> getAllAdministrateurs() {
        Query query = em.createNamedQuery("Administrateur.findAll");  
        return query.getResultList();
    }
}
