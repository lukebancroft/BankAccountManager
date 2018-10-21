# Banque_Bancroft-Lopez
Gestionnaire de compte bancaires - TP3/TP4/mini-projet J2EE MBDS par Edouard Amosse
http://miageprojet2.unice.fr/Intranet_de_Michel_Buffa/Cours_composants_distribu%C3%A9s_pour_l'entreprise_2013-2013/TP3_2013_JSF2%2f%2fEJB_%3a_Ecriture_d'un_gestionnaire_de_comptes_bancaires


Connexion à la BDD :
- Dans les properties de Services > Databases > Java DB, sélectionner le dossier du projet en "Database location"
- Connexion à la BDD "BanqueBancroftLopez" avec les identifiants "BancroftLopez" "123"

Lazy Loading et Filtres :
- Les filtres et tris ne peuvent fonctionner en même temps que le Lazy Loading mais les deux sont implémentés séparément.
- De base le Lazy Loading est en place. Si on veut pouvoir filtrer et trier les listes de comptes et d'opérations, il suffit de :
    o Dans CompteBancaireList.xhtml de changer la value du DataTable #{compteBancaireMBean.compteBancairesLazy} 
	  par #{compteBancaireMBean.compteBancaires} et passer le lazy="true" à false.
	o Dans OperationsCompteBancaire.xhtml de changer la value du DataTable #{operationsCompteBancaireMBean.operationBancairesLazy} 
	  par #{operationsCompteBancaireMBean.operationsCompteBancaires} et passer le lazy="true" à false.

Rapport de Conception :
- Le TP 3 a été réalisé entièrement
- Le TP 4 il reste seulement :
    o le Message Driven Bean pour les ordres de transfert bancaires
- Sur le mini-projet il reste :
    o Login des utilisateurs
    o Visibilité des pages en fonction du rôle utilisateur (Client, Conseiller, Administrateur)
    o Utiliser un session bean stateful


Projet réalisé par :
- Bancroft-Richardson Luke 
- Lopez Fabien

MBDS Nice Sophia Antipolis 2018/2019
