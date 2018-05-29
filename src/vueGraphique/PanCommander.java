package vueGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlCommander;
import model.Hamburger;

public class PanCommander extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControlCommander controlCommande;
	private PanEnregistrerCoordonneesBancaires panEnregistrerCoordonneesBancaires;
	private int numClient = 0;
	private int numeroHamburger = 0;
	private int numeroAccompagnement = 0;
	private int numeroBoisson = 0;
	private Font policeTitre = new Font("Calibri", Font.BOLD, 24);
	private Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);
	private Box boxMiseEnPageCommande = Box.createVerticalBox();
	private Box boxChoixHamburger = Box.createHorizontalBox();
	private Box boxChoixAccompagnement = Box.createHorizontalBox();
	private Box boxChoixBoisson = Box.createHorizontalBox();

	/**
	 * Constructeur PanCommander.
	 * 
	 * @param controlCommander                   Control commander commande.
	 * @param panEnregistrerCoordonneesBancaires Panel
	 *                                           enregistrerCoordonneesBancaires.
	 */
	public PanCommander(ControlCommander controlCommande,
			PanEnregistrerCoordonneesBancaires panEnregistrerCoordonneesBancaires) {
		this.controlCommande = controlCommande;
		this.panEnregistrerCoordonneesBancaires = panEnregistrerCoordonneesBancaires;
	}

	/**
	 * Initialise le panel
	 */
	public void initialisation() {
		this.setBackground(Color.YELLOW);

		JLabel texteCommander = new JLabel("Votre menu");
		texteCommander.setFont(policeTitre);

		/* Hamburger */
		JLabel texteHamburger = new JLabel("Choisissez votre hamburger :");
		texteHamburger.setFont(policeParagraphe);
		boxMiseEnPageCommande.add(texteCommander);
		boxMiseEnPageCommande.add(Box.createRigidArea(new Dimension(0, 30)));

		boxChoixHamburger.add(texteHamburger);
		boxMiseEnPageCommande.add(boxChoixHamburger);
		this.add(boxMiseEnPageCommande);

		/* Accompagnement */
		JLabel texteAccompagnement = new JLabel("Choisissez votre accompagnement :");
		texteAccompagnement.setFont(policeParagraphe);
		boxChoixAccompagnement.add(texteAccompagnement);
		
		boxMiseEnPageCommande.add(Box.createRigidArea(new Dimension(0, 30)));
		boxMiseEnPageCommande.add(boxChoixAccompagnement);
		this.add(boxMiseEnPageCommande);

		/* Hamburger */
		JLabel texteBoisson = new JLabel("Choisisez votre boisson :");
		texteBoisson.setFont(policeParagraphe);

		boxChoixBoisson.add(texteBoisson);
		
		boxMiseEnPageCommande.add(Box.createRigidArea(new Dimension(0, 30)));
		boxMiseEnPageCommande.add(boxChoixBoisson);
		this.add(boxMiseEnPageCommande);

	}

	/**
	 * Permet a un client de faire une commande a partir de son numero
	 * 
	 * @param numClient
	 */
	public void commander(int numClient) {
		this.numClient = numClient;
		if (controlCommande.verifierIdentification(numClient)) {

								/* Hamburger */
			List<String> listeHamburger = controlCommande.getListHamburger();

			final JComboBox<String> comboBoxHamburger = new JComboBox<>();
			comboBoxHamburger.addItem("");
			
			for (String item : listeHamburger) {
				comboBoxHamburger.addItem(item);
			}
			comboBoxHamburger.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					numeroHamburger = comboBoxHamburger.getSelectedIndex() - 1;
				}
			});

			this.boxChoixHamburger.add(Box.createRigidArea(new Dimension(10, 0)));
			this.boxChoixHamburger.add(comboBoxHamburger);

								/* Accompagnement */
			List<String> listeAccompagnement = controlCommande.getListAccompagnement();

			final JComboBox<String> comboBoxAccompagnement = new JComboBox<>();
			comboBoxHamburger.addItem("");
			for (String item : listeAccompagnement) {
				comboBoxAccompagnement.addItem(item);
			}
			comboBoxAccompagnement.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					numeroAccompagnement = comboBoxAccompagnement.getSelectedIndex() - 1;
				}
			});
			
			this.boxChoixAccompagnement.add(Box.createRigidArea(new Dimension(10, 0)));
			this.boxChoixAccompagnement.add(comboBoxAccompagnement);

								/* Boisson */
			List<String> listeBoisson = controlCommande.getListBoisson();

			final JComboBox<String> comboBoxBoisson = new JComboBox<>();
			comboBoxBoisson.addItem("");
			for (String item : listeBoisson) {
				comboBoxBoisson.addItem(item);
			}
			comboBoxBoisson.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					numeroBoisson = comboBoxBoisson.getSelectedIndex() - 1;
				}
			});

			this.boxChoixBoisson.add(Box.createRigidArea(new Dimension(10, 0)));
			this.boxChoixBoisson.add(comboBoxBoisson);

		}
	}
}
