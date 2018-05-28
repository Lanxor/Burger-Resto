package testtextuel;

import control.ControlAjouterAlimentMenu;
import control.ControlArchiver;
import control.ControlCommander;
import control.ControlCreerProfil;
import control.ControlEnregistrerCoordonneesBancaires;
import control.ControlSIdentifier;
import control.ControlVerifierCoordonneesBancaires;
import control.ControlVerifierIdentification;
import control.ControlVisualiserCommandeJour;
import model.ProfilUtilisateur;
import vue.BoundaryArchiver;
import vue.BoundaryCommander;
import vue.BoundaryEnregistrerCoordonneesBancaires;
import vue.BoundaryVisualiserCommandeJour;

public class TestCasArchiver {

	public static void main(String[] args) {
		// Mise en place de l'environnement
		ControlCreerProfil controlCreerProfil = new ControlCreerProfil();
		ControlAjouterAlimentMenu controlAjouterAlimentCarte = new ControlAjouterAlimentMenu(
				new ControlVerifierIdentification());
		ControlSIdentifier controlSIdentifier = new ControlSIdentifier();

		controlAjouterAlimentCarte.ajouterHamburger("baconBurger");
		controlAjouterAlimentCarte.ajouterHamburger("chickenBurger");
		controlAjouterAlimentCarte.ajouterHamburger("cheeseBurger");
		controlAjouterAlimentCarte.ajouterAccompagnement("frites");
		controlAjouterAlimentCarte.ajouterAccompagnement("pommesChips");
		controlAjouterAlimentCarte.ajouterBoisson("coca");
		controlAjouterAlimentCarte.ajouterBoisson("orangeBubbles");

		// Cr�ation et connexion du profil g�rant
		controlCreerProfil.creerProfil(ProfilUtilisateur.GERANT, "Martin", "Victor", "gmv");
		int numGerant = controlSIdentifier.sIdentifier(ProfilUtilisateur.GERANT, "Victor.Martin", "gmv");

		// Cr�ation et connexion des profils clients
		controlCreerProfil.creerProfil(ProfilUtilisateur.CLIENT, "Dupond", "Hector", "cdh");
		int numClient = controlSIdentifier.sIdentifier(ProfilUtilisateur.CLIENT, "Hector.Dupond", "cdh");

		controlCreerProfil.creerProfil(ProfilUtilisateur.CLIENT, "Durand", "Jacques", "cdj");
		int numClient2 = controlSIdentifier.sIdentifier(ProfilUtilisateur.CLIENT, "Jacques.Durand", "cdj");

		// Cr�ation et connexion des profils cuisiniers
		controlCreerProfil.creerProfil(ProfilUtilisateur.PERSONNEL, "Martin", "Stephane", "pms");
		int numCuisinier = controlSIdentifier.sIdentifier(ProfilUtilisateur.PERSONNEL, "Stephane.Martin", "pms");

		controlCreerProfil.creerProfil(ProfilUtilisateur.PERSONNEL, "Bernard", "Christophe", "pbc");
		int numCuisinier2 = controlSIdentifier.sIdentifier(ProfilUtilisateur.PERSONNEL, "Christophe.Bernard", "pbc");

		// Initialisation controleur du cas & cas Inclus/etendu
		ControlVerifierIdentification controlVerifierIdentification = new ControlVerifierIdentification();
		ControlVerifierCoordonneesBancaires controlVerifierCoordonneesBancaire = new ControlVerifierCoordonneesBancaires();
		ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires = new ControlEnregistrerCoordonneesBancaires(
				controlVerifierCoordonneesBancaire);

		// Initialisation vue du cas & cas Inclus/etendu
		BoundaryEnregistrerCoordonneesBancaires boundaryEnregistrerCoordonneesBancaires = new BoundaryEnregistrerCoordonneesBancaires(
				controlEnregistrerCoordonneesBancaires);
		BoundaryCommander boundaryCommander = new BoundaryCommander(new ControlCommander(controlVerifierIdentification),
				boundaryEnregistrerCoordonneesBancaires);

		// creation pour la visualisation de la commande
		ControlVisualiserCommandeJour controlVisualiserCommandeJour = new ControlVisualiserCommandeJour(
				controlVerifierIdentification);
		BoundaryVisualiserCommandeJour boundaryVisualiserCommandeJour = new BoundaryVisualiserCommandeJour(
				controlVisualiserCommandeJour);
		boundaryVisualiserCommandeJour.visualiserCommandeJour(numCuisinier);
		boundaryVisualiserCommandeJour.visualiserCommandeJour(numCuisinier2);

		// Lancement du test
		System.out.println("\nCommande client 1");
		boundaryCommander.commander(numClient);
		System.out.println("\nCommande client 2");
		boundaryCommander.commander(numClient2);

		// attendre 30s pour la visualisation du fichier avec les commandes
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Cas Archiver
		System.out.println("COMMANDES ARCHIVER");
		ControlArchiver controlArchiver = new ControlArchiver(controlVerifierIdentification);
		BoundaryArchiver boundaryArchiver = new BoundaryArchiver(controlArchiver);
		boundaryArchiver.archiver(numGerant);

		// Verification de la bonne realisation du cas
		// Resultat du test
		//
		// Commande client 1
		// Veuillez selectionner le numero de votre hamburger
		// Liste des hamburgers :
		// 1 : baconBurger
		// 2 : chickenBurger
		// 3 : cheeseBurger
		//
		// 1
		// Veuillez selectionner le numero de votre accompagnement
		// Liste des accompagnement :
		// 1 : frites
		// 2 : pommesChips
		//
		// 2
		// Veuillez selectionner le numero de votre boisson
		// Liste des boissons :
		// 1 : coca
		// 2 : orangeBubbles
		//
		// 2
		// Veuillez saisir votre numero de carte bancaire
		// 123154123
		// Veuillez saisir la date d'expiration de votre carte bancaire (MMAA)
		// 1019
		// Votre numero de commande est le : 1
		//
		// Commande client 2
		// Veuillez selectionner le numero de votre hamburger
		// Liste des hamburgers :
		// 1 : baconBurger
		// 2 : chickenBurger
		// 3 : cheeseBurger
		//
		// 3
		// Veuillez selectionner le numero de votre accompagnement
		// Liste des accompagnement :
		// 1 : frites
		// 2 : pommesChips
		//
		// 1
		// Veuillez selectionner le numero de votre boisson
		// Liste des boissons :
		// 1 : coca
		// 2 : orangeBubbles
		//
		// 1
		// Veuillez saisir votre numero de carte bancaire
		// 369258147
		// Veuillez saisir la date d'expiration de votre carte bancaire (MMAA)
		// 1219
		// Votre numero de commande est le : 2
		//
		// Dans le fichier ecranCuisinierCommandeJour.txt (Rafraichir le projet) :
		// Commande n�1 : baconBurger, pommesChips, orangeBubbles
		// Commande n�2 : cheeseBurger, frites, coca
		// Fermer le fichier
		// Attendre 30 secondes (Thread.sleep(30000);) l'�criture dans la console
		// de "COMMANDES ARCHIVER"
		// Ouvrir le fichier ecranCuisinierCommandeJour.txt : il doit �tre vide
		
		// 1 2 2 123154123 1019
		// 3 1 1 369258147 1219

	}

}
