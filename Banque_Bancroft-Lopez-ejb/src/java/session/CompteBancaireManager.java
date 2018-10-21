/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import entities.OperationBancaire;
import java.util.Date;
import java.util.List;
import javax.ejb.Timer;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luke
 */
@Singleton
@LocalBean
@Startup
public class CompteBancaireManager {

    @PersistenceContext(unitName = "Banque_Bancroft-Lopez-ejbPU")
    private EntityManager em;

    @Resource
    private TimerService timerService;

    @PostConstruct
    private void init() {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("CalendarProgTimerDemo_Info");
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.hour("*").minute("*").second("01,11,21,31,41,51");
        timerService.createCalendarTimer(schedule, timerConfig); 
    } 

    @Timeout
    public void execute(Timer timer) {
        for (CompteBancaire c : getAllCompteBancaires()){
            c.setSolde(c.getSolde()+1);
            update(c);
        }
        System.out.println("Timer Service : " + timer.getInfo());
        System.out.println("Execution Time : " + new Date());
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
}