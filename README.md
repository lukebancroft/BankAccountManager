# Banque_Bancroft-Lopez
Banque_Bancroft-Lopez est un gestionnaire de comptes bancaires permettant de :
- Visualiser des statistiques sur les données des entités sous forme de pie chart
- Visualiser l'ensemble de ces données dans des listes et de les exporter sous format pdf, xls, csv ou xml
- Effectuer des virements d'un compte bancaire à un autre
- Créditer/Débiter un compte bancaire
- Visualiser les opérations effectués sur un compte bancaire en particulier ou tous ceux d'un utilisateur
- D'augmenter le solde d'un compte épargne selon un temps défini et en fonction de son taux d'intérêt

Installation
-

**Connexion à la BDD :**
- Dans les properties de Services > Databases > Java DB, sélectionner le dossier du projet en "Database location"
- Connexion à la BDD Sample fournie par NetBeans
- Optionnel : supprimez toutes les tables existantes dans le schéma APP 

**Environnement de développement :**
- Si besoin, ajouter les librairies externes suivantes dans le dossier Libraries de Banque_Bancroft-Lopez-war :
* PrimeFaces 6.2
* Admin-Theme-1.0.0-RC19
* iText-2.1.7
* Poi-3.16
* XmlBeans-2.3.0

les jars correspondants se situent dans :
```sh
./Banque_Bancroft-Lopez-war/web/WEB-INF/lib
```

**Lancement du projet :**
- Il est préférable de supprimer toutes les tables de la base de données qui sera utilisée avant de lancer le projet
- Assurez-vous que la stratégie de création des tables dans le fichier persistance.xml soit en "Drop and create"
- N'oubliez pas de clean et de build le projet avant de le deploy
- Connectez-vous à l'adresse suivant :
```sh
http://localhost:8080/Banque_Bancroft-Lopez-war/faces/dashboard.xhtml
```

Projet
-

**Relations entre les entités :**
Le projet est constitué de 3 entités principales :
- ```Personne```, resprésentant une personne physique qui serait amené à utiliser le système. Il possède au minimum un *id*, un nom d'utilisateur *username* et un mot de passe *password*. Trois entités héritent de Personne :
* ```Client```, ayant un *nom*, un *prenom*, une liste de comptes bancaires dont il est propriétaire *comptes* et une liste de comptes bancaires auquel il peut éffectuer des virements *comptesBeneficiaires*
* ```Conseiller```, ayant simplement un numéro de conseiller *numConseiller*
* ```Administrateur```, ayant simplement un numéro d'administrateur *numAdministrateur*
- ```CompteBancaire```, représentant un compte Bancaire possédé par au moins un propriétaire et pouvant effectuer plusieurs types d'opérations bancaires. Il possède au minimum un *id*, un *solde*, une liste de toutes ses *operations* ainsi qu'un client *proprietaire*. Trois entités héritent de CompteBancaire
* ```CompteCourant```, qui peut avoir un nombre illimité de *coproprietaires*, bien que son *proprietaire* initial détient le compte
* ```CompteEpargne```, qui possède un taux d'intérêt *txInteret*, graçe auquel son solde augmentera dans un lapse de temps pré-défini
* ```CompteJoint```, qui peut avoir un second propriétaire *secondProprietaire*, bien que son *proprietaire* initial détient le compte
- ```OperationBancaire```, représentant une opération bancaire effectué sur un compte bancaire appartenant à un client. Il possède un *id*, une *description*, une date *dateOperation* ainsi qu'un *montant*

Fonctionnalités / Contraintes du projet
-

- Génération de données de test
- Chaque opération sur un compte bancaire entraîne la création d'une opération bancaire qui lui est associée
- Listes de comptes et d'opérations
* Mise en place du filtrage et du tri des listes
* Mise en place d'un Lazy Loading des listes
* *Attention, les filtres ne sont pas implémentées simultanéments avec le lazy loading. Pour utiliser les filtres plutôt que le lazy loading, changez #{compteBancaireMBean.compteBancairesLazy} par #{compteBancaireMBean.compteBancaires} et changez la valeur de "lazy" à false dans la DataTable de CompteBancaireList.xhtml. Cette opération est possible sur toutes les pages contenant des listes.*
- Utilisation d'un template pour toutes les pages
- Utilisation de "comptes bénéficiaires" pour les transferts d'argent d'un compte à un autre
- Composants PrimeFaces non vus en cours :
* p:breadcrumb pour la navigation
* p:chart pour l'affichage des statistiques sur le dashboard
- Implémentation de plusieurs types de comptes bancaires
- EJBTimer pour simuler le taux d'intérêt d'un compte épargne
- Navigation entre pages avec faces-redirect=true
- Utilisation de @ViewScoped et @RequestScoped (DashboardMBean)
- Utilisation de viewParam et viewAction dans le metadata
- Relations @OneToMany, @ManyToOne et @ManyToMany
- Export des données en pdf, xls, csv et xml
- Retrait, dépôt et virements sur les comptes

Projet M2 MBDS Nice Sophia Antipolis - Promo 2018/2019 - Enseignant : Edouard Amosse - Langage : J2EE JSF - Réalisé par : Bancroft-Ricahrdson Luke et Lopez Fabien
