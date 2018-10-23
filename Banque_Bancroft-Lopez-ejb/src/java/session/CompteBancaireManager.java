/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteBancaire;
import entities.CompteEpargne;
import entities.OperationBancaire;
import java.util.List;
import javax.ejb.Timer;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luke
 */
@Stateless
@LocalBean
@Startup
public class CompteBancaireManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    @SuppressWarnings("unused")
    @Schedule(second="*/10", minute="*", hour="*", dayOfWeek="*", 
            dayOfMonth="*", month="*", year="*", info="MyTimer")
    private void scheduledTimeout(final Timer t) {
        for (CompteBancaire c : getAllCompteBancaires()){
            if(c instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) c;
                ce.setSolde(ce.getSolde() + (ce.getSolde() * ce.getTxInteret()/100));
                update(ce);
            }
        }
        System.out.println("@Schedule called at: " + new java.util.Date());
        System.out.println("Les comptes épargne ont été crédités de leut taux d'intérêt.");
        System.out.println("____________________________________________");
    }
    
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
    
    public List<CompteBancaire> getAllCompteBancairesByProprietaire(Client proprietaire) {
        Query query = em.createNamedQuery("CompteBancaire.findByProprietaire");  
        query.setParameter("proprietaire", proprietaire);
        return query.getResultList();
    }
    
    public List<CompteBancaire> getLazyCompteBancaires(int start, int nbComptes){
        
        Query query =em.createNamedQuery("CompteBancaire.findAll");
        query.setFirstResult(start);
        query.setMaxResults(nbComptes);
        
        return query.getResultList();
    }
    
    public int getNbComptes(){
        Query query = em.createNamedQuery("CompteBancaire.getNbComptes");
        
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public int getNbComptesByClient(Client client){
        Query query = em.createNamedQuery("CompteBancaire.getNbComptesByClient");
        query.setParameter("client", client);
        
        return ((Long) query.getSingleResult()).intValue();
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

    /*public void creerComptesTest() {  
        creerCompte(new CompteBancaire("John Lennon", 150000));  
        creerCompte(new CompteBancaire("Paul McCartney", 950000));  
        creerCompte(new CompteBancaire("Ringo Starr", 20000));  
        creerCompte(new CompteBancaire("Georges Harrisson", 100000));  
    }*/

    public List<OperationBancaire> getOperationsbyCompteBancaireId(long idCompteBancaire) {
        Query query = em.createNamedQuery("CompteBancaire.getOperationsByCompteBancaireId");
        query.setParameter("idCompteBancaire", idCompteBancaire);
        return query.getResultList();
    }
    
    public List<CompteBancaire> getLazyCompteBancairesByClient(int start, int nbComptes, Client client){
        
        Query query =em.createNamedQuery("CompteBancaire.findAllByClient");
        query.setFirstResult(start);
        query.setMaxResults(nbComptes);
        query.setParameter("client", client);
        
        return query.getResultList();
    }
}