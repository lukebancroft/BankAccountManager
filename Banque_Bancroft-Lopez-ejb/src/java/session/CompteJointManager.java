/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteJoint;
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
public class CompteJointManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<CompteJoint> getLazyComptesJointBySecondProprio(int start, int nbComptes, Client client){
        
        Query query =em.createNamedQuery("CompteJoint.findAllBySecondProprio");
        query.setFirstResult(start);
        query.setMaxResults(nbComptes);
        query.setParameter("client", client);
        
        return query.getResultList();
    }
    
    public int getNbComptesJointBySecondProprio(Client client){
        Query query = em.createNamedQuery("CompteJoint.getNbComptesBySecondProprio");
        query.setParameter("client", client);
        
        return ((Long) query.getSingleResult()).intValue();
    }
}
