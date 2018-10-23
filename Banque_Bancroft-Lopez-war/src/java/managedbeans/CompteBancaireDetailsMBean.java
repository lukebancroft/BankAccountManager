package managedbeans;  
  
import entities.CompteBancaire;  
import java.io.Serializable;  
import java.util.LinkedHashMap;
import java.util.List;  
import java.util.Map;
import javax.ejb.EJB;  
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.view.ViewScoped;  
import javax.inject.Named;  
import session.CompteBancaireManager;  
  
/** 
 * Backing bean pour la page CompteBancaireDetails.xhtml. 
 */  
@Named  
@ViewScoped  
public class CompteBancaireDetailsMBean implements Serializable {  
  private int idCompteBancaire;  
  private int compteRecepteurId;
  private int opType;  
  private int montantOp = 0;  
  private int montantVirement = 0;  
  private CompteBancaire compteBancaire;  
  private Map<Integer, String> beneficiaires = new LinkedHashMap<>();
  
  @EJB  
  private CompteBancaireManager compteBancaireManager;  
  
  public int getIdCompteBancaire() {  
    return idCompteBancaire;  
  } 
  
  public int getCompteRecepteurId() {  
    return compteRecepteurId;  
  } 
  
  public int getOpType() {  
    return opType;  
  } 
  
  public int getMontantOp() {  
    return montantOp;  
  } 
  
  public int getMontantVirement() {  
    return montantVirement;  
  }

    public Map<Integer, String> getBeneficiaires() {
        return beneficiaires;
    }

  
  public void setIdCompteBancaire(int idCompteBancaire) {  
    this.idCompteBancaire = idCompteBancaire;  
  } 
  
  public void setCompteRecepteurId(int compteRecepteurId) {  
    this.compteRecepteurId = compteRecepteurId;  
  } 
  
  public void setOpType(int opType) {  
    this.opType = opType;  
  }
  
  public void setMontantOp(int montantOp) {  
    this.montantOp = montantOp;  
  }
  
  public void setMontantVirement(int montantVirement) {  
    this.montantVirement = montantVirement;  
  }

    public void setBeneficiaires(Map<Integer, String> beneficiaires) {
        this.beneficiaires = beneficiaires;
    }
  
  
  /** 
   * Renvoie les détails du CompteBancaire courant (celui dans l'attribut CompteBancaire de 
   * cette classe), qu'on appelle une propriété (property) 
   */  
  public CompteBancaire getDetails() {  
    return compteBancaire;  
  }  
 
  
  /** 
   * Action handler - met à jour la base de données en fonction du CompteBancaire passé 
   * en paramètres, et renvoie vers la page qui affiche la liste des clients. 
     * @return 
   */  
  public String update() {  
    System.out.println("###UPDATE###");  
    compteBancaire = compteBancaireManager.update(compteBancaire);  
    return "CompteBancaireList?faces-redirect=true";  
  }  
  
    public String updateBalance() {  
    System.out.println("###OPERATION###"); 
    this.compteBancaire = compteBancaireManager.getCompteBancaire(idCompteBancaire);
    if (opType == 0) {
        compteBancaire.deposer(montantOp);
    }
    else if (opType == 1) {
        compteBancaire.retirer(montantOp);
    }
    compteBancaire = compteBancaireManager.update(compteBancaire);  
    return "CompteBancaireList?faces-redirect=true";  
  }  
    
  public String effectuerVirement() {
    System.out.println("###VIREMENT###"); 
    CompteBancaire compteEmetteur = compteBancaireManager.getCompteBancaire(idCompteBancaire);
    CompteBancaire compteRecepteur = compteBancaireManager.getCompteBancaire(compteRecepteurId);
    if (compteEmetteur.retirer(montantVirement) == montantVirement) {
        compteRecepteur.deposer(montantVirement);
    }
    compteBancaireManager.update(compteEmetteur); 
    compteBancaireManager.update(compteRecepteur);  
    return "CompteBancaireList?faces-redirect=true";  
  }
  
  public String supprimerCompte() {
    System.out.println("###SUPPRESSION###"); 
    this.compteBancaire = compteBancaireManager.getCompteBancaire(idCompteBancaire);
    compteBancaireManager.delete(compteBancaire); 
    return "CompteBancaireList?faces-redirect=true";  
      
  }
  
  /** 
   * Action handler - renvoie vers la page qui affiche la liste des CompteBancaire 
     * @return 
   */  
  public String list() {  
    System.out.println("###LIST###");  
    return "CompteBancaireList?faces-redirect=true";  
  }  
  
  public void loadCompteBancaire() {  
    this.compteBancaire = compteBancaireManager.getCompteBancaire(idCompteBancaire);
    List<CompteBancaire> comptesBeneficiaires = this.compteBancaire.getProprietaire().getComptesBeneficiaires();
    if(comptesBeneficiaires != null && comptesBeneficiaires.size() > 0) {
        comptesBeneficiaires.forEach((cb) -> {
            this.beneficiaires.put(cb.getId(), cb.getProprietaire().getNom() + " " + cb.getProprietaire().getPrenom());
      }); 
    }
  }
}