/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Administrateur;
import entities.Client;
import entities.CompteBancaire;
import entities.Conseiller;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.PieChartModel;
import session.AdministrateurManager;
import session.ClientManager;
import session.CompteBancaireManager;
import session.ConseillerManager;
import session.OperationBancaireManager;

@Named(value = "dashboardMBean")
@RequestScoped
public class DashboardMBean {
    private PieChartModel pieModel;
    
    @EJB  
    private CompteBancaireManager compteBancaireManager;
    @EJB
    private OperationBancaireManager operationBancaireManager;
    @EJB
    private ClientManager clientManager;
    @EJB
    private ConseillerManager conseillerManager;
    @EJB
    private AdministrateurManager administrateurManager;
    
    public DashboardMBean() {
    }
 
    @PostConstruct
    public void init() {
        makeTestData();
        createPieModel();
    }
 
    public PieChartModel getPieModel() {
        return pieModel;
    }
     
    private void makeTestData() {
        if(clientManager.getNbClients() == 0) {
            Client cl1 = new Client("lulu", "123", "Richardson", "Luke");
            Client cl2 = new Client("fabi", "123", "Lopez", "Fabien");
            Client cl3 = new Client("dani", "123", "Kuchta", "Daniel");
            Client cl4 = new Client("amo", "123", "Edouard", "Amosse");
            Client cl5 = new Client("coco", "123", "Launay", "Daniel");
            clientManager.creerClient(cl1);
            clientManager.creerClient(cl2);
            clientManager.creerClient(cl3);
            clientManager.creerClient(cl4);
            clientManager.creerClient(cl5);

            Conseiller co1 = new Conseiller("cons1", "123", 9982L);
            Conseiller co2 = new Conseiller("cons2", "123", 7852L);
            conseillerManager.creerConseiller(co1);
            conseillerManager.creerConseiller(co2);
            
            Administrateur ad1 = new Administrateur("admin", "123", 001L);
            administrateurManager.creerAdministrateur(ad1);
            
            CompteBancaire cb1 = new CompteBancaire(cl1, 3525);
            CompteBancaire cb2 = new CompteBancaire(cl2, 5500);
            CompteBancaire cb3 = new CompteBancaire(cl3, 2550);
            CompteBancaire cb4 = new CompteBancaire(cl4, 7500);
            CompteBancaire cb5 = new CompteBancaire(cl4, 12500);
            CompteBancaire cb6 = new CompteBancaire(cl5, 25000);
            CompteBancaire cb7 = new CompteBancaire(cl5, 32000);
            CompteBancaire cb8 = new CompteBancaire(cl5, 975);
            compteBancaireManager.creerCompte(cb1);
            compteBancaireManager.creerCompte(cb2);
            compteBancaireManager.creerCompte(cb3);
            compteBancaireManager.creerCompte(cb4);
            compteBancaireManager.creerCompte(cb5);
            compteBancaireManager.creerCompte(cb6);
            compteBancaireManager.creerCompte(cb7);
            compteBancaireManager.creerCompte(cb8);
        }
    }
    
    private void createPieModel() {
        pieModel = new PieChartModel();
         
        pieModel.set("Clients", clientManager.getNbClients());
        pieModel.set("Comptes", compteBancaireManager.getNbComptes());
        pieModel.set("Opérations", operationBancaireManager.getNbOperations());
         
        pieModel.setTitle("Statistiques des enitités");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(375);
        pieModel.setShadow(false);
    }
}
