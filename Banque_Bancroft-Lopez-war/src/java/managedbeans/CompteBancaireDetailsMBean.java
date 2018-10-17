package managedbeans;  
  
import entities.CompteBancaire;  
import java.io.Serializable;  
import java.util.List;  
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
  private int opType;  
  private int montantOp = 0;  
  private CompteBancaire compteBancaire;  
  
  @EJB  
  private CompteBancaireManager compteBancaireManager;  
  
  public int getIdCompteBancaire() {  
    return idCompteBancaire;  
  } 
  
  public int getOpType() {  
    return opType;  
  } 
  
  public int getMontantOp() {  
    return montantOp;  
  } 
  
  public void setIdCompteBancaire(int idCompteBancaire) {  
    this.idCompteBancaire = idCompteBancaire;  
  } 
  
  public void setOpType(int opType) {  
    this.opType = opType;  
  }
  
  public void setMontantOp(int montantOp) {  
    this.montantOp = montantOp;  
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
   */  
  public String update() {  
    System.out.println("###UPDATE###");  
    compteBancaire = compteBancaireManager.update(compteBancaire);  
    return "CompteBancaireList?faces-redirect=true";  
  }  
  
    public String updateBalance() {  
    System.out.println("###UPDATE###"); 
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
  
  /** 
   * Action handler - renvoie vers la page qui affiche la liste des CompteBancaire 
   */  
  public String list() {  
    System.out.println("###LIST###");  
    return "CompteBancaireList?faces-redirect=true";  
  }  
  
  public void loadCompteBancaire() {  
    this.compteBancaire = compteBancaireManager.getCompteBancaire(idCompteBancaire);  
  } 
}