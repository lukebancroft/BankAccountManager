/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
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
public class ClientManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerClient(Client c) {
        persist(c);
    }

    public int getNbClients(){
        Query query = em.createNamedQuery("Client.getNbClients");
        
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public List<Client> getAllClients() {
        Query query = em.createNamedQuery("Client.findAll");  
        return query.getResultList();
    }
}
