/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: MenuFenetre.java
Date crÃ©Ã©: 2013-05-03
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Ngoc-Phong Nguyen
2015-05-25 Ajout de la ligne de code qui dÃ©sactive l'option "ArrÃªter" Ã  l'ouverture de l'application
2015-06-12 Optimisation de la classe
*@author Carole Fabeleu
2015-06-08 Modification des menus Dessin, Fichier et Ordre
*******************************************************/  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * CrÃ©e le menu de la fenÃªtre de l'application.
 */
public class MenuFenetre extends JMenuBar implements PropertyChangeListener{
	
	private static final long serialVersionUID = 1536336192561843187L;

	private static final int  	MENU_FICHIER_OBTENIR_FORMES_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char 	MENU_FICHIER_OBTENIR_FORMES_TOUCHE_RACC = KeyEvent.VK_O;
	private static final String
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_FICHIER_OBTENIR_FORME = "app.frame.menus.file.form",
			
			
			MENU_ORDRE_TITRE = "app.frame.menus.order.title",
			MENU_ORDRE_NUM_SEQ_CROISSANT = "app.frame.menus.order.number1",
			MENU_ORDRE_NUM_SEQ_DECROISSANT = "app.frame.menus.order.number2",
			MENU_ORDRE_AIRE_FORME_CROISSANT = "app.frame.menus.order.area1",
			MENU_ORDRE_AIRE_FORME_DECROISSANT = "app.frame.menus.order.area2",
			MENU_ORDRE_TYPE_FORME_ORDONNE = "app.frame.menus.order.type1",
			MENU_ORDRE_TYPE_FORME_INVERSE = "app.frame.menus.order.type2",
			MENU_ORDRE_DISTANCE_MAX = "app.frame.menus.order.distance",
			MENU_ORDRE_LARGEUR_CROISSANT = "app.frame.menus.order.width1",
			MENU_ORDRE_LARGEUR_DECROISSANT = "app.frame.menus.order.width2",
			MENU_ORDRE_HAUTEUR_CROISSANT = "app.frame.menus.order.height1",
			MENU_ORDRE_HAUTEUR_DECROISSANT = "app.frame.menus.order.height2",
			MENU_ORDRE_ORIGINAL = "app.frame.menus.order.original",
			MENU_AIDE_TITRE = "app.frame.menus.help.title",
			MENU_AIDE_PROPOS = "app.frame.menus.help.about";
	
	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";  
	//private static final int DELAI_QBTENIR_FORME_MSEC = 200;
	
	// Tous les menus
	private JMenu fichier, ordre;
	private JMenuItem obtenirFormesMenuItem;

	// Tous les RadioButtons
	private JRadioButtonMenuItem
		boutonTriNseqCrois,
		boutonTriNseqDecrois,
		boutonTriAireCrois,
		boutonTriAireDecrois,
		boutonTriTypeCrois,
		boutonTriTypeDecrois,
		boutonTriDistanceCrois,
		boutonTriLargeurCrois,
		boutonTriLargeurDecrois,
		boutonTriHauteurCrois,
		boutonTriHauteurDecrois,
		boutonTriOriginal,
		item;
	
	CommBase comm; // Pour activer/dÃ©sactiver la communication avec le serveur
	
	private PropertyChangeListener listener = null; // Recepteur d'information de choix de tri
	
	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuFichier();
		addMenuOrdre();
		addMenuAide();
		rafraichirMenus('I'); // Mode Initialisaion
	}

	/**
	 *  Création du menu Fichier.
	 *  Inspirée du code source fourni dans le laboratoire.
	 *  @author  Carole Fabeleu
	 *  @date:   2015-06-05 
	 */
	protected void addMenuFichier() {
		fichier = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_QBTENIR_FORME });
		
		obtenirFormesMenuItem = fichier.getItem(0);
		obtenirFormesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comm.obtenirFormes(); // Appelle méthode dans commBase pour obtenir les formes
				rafraichirMenus(null);
			}
			
		});

		fichier.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_OBTENIR_FORMES_TOUCHE_RACC,
						MENU_FICHIER_OBTENIR_FORMES_TOUCHE_MASK));
		add(fichier);
	}
	
	/**
	 *  Création du menu Ordre.
	 *  Inspirée du code source fourni dans le laboratoire.
	 *  @author  Carole Fabeleu
	 *  @date:   2015-06-05 
	 */
	protected void addMenuOrdre() {
		ordre = creerJRadioButtonMenu(MENU_ORDRE_TITRE,new String[] { MENU_ORDRE_NUM_SEQ_CROISSANT, MENU_ORDRE_NUM_SEQ_DECROISSANT,
																MENU_ORDRE_AIRE_FORME_CROISSANT, MENU_ORDRE_AIRE_FORME_DECROISSANT,
																MENU_ORDRE_TYPE_FORME_ORDONNE, MENU_ORDRE_TYPE_FORME_INVERSE,
																MENU_ORDRE_DISTANCE_MAX,
																MENU_ORDRE_LARGEUR_CROISSANT, MENU_ORDRE_LARGEUR_DECROISSANT,
																MENU_ORDRE_HAUTEUR_CROISSANT, MENU_ORDRE_HAUTEUR_DECROISSANT,
																MENU_ORDRE_ORIGINAL});
		
		// Groupe de RadioButtons
		ButtonGroup bg = new ButtonGroup();

		boutonTriNseqCrois = (JRadioButtonMenuItem)ordre.getItem(0);
		boutonTriNseqCrois.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0) {
			  firePropertyChange("TRI", "TriNseq", false); // Methode pour trier les formes par numero de sequence en ordre decroissant
			
		  }
		});
		bg.add(boutonTriNseqCrois);

		boutonTriNseqDecrois = (JRadioButtonMenuItem)ordre.getItem(1);
		boutonTriNseqDecrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				firePropertyChange("TRI", "TriNseq", true); // Methode pour trier les formes par numero de sequence en ordre decroissant
		    }
	    });
		bg.add(boutonTriNseqDecrois);

		boutonTriAireCrois = (JRadioButtonMenuItem)ordre.getItem(2);
		boutonTriAireCrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriAire", false); // Methode pour trier les formes par aire en ordre croissant
		    }
	    });	
		bg.add(boutonTriAireCrois);

		boutonTriAireDecrois = (JRadioButtonMenuItem)ordre.getItem(3);
		boutonTriAireDecrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriAire", true); // Methode pour trier les formes par aire en ordre decroissant
	    	}
	    });
		bg.add(boutonTriAireDecrois);

		boutonTriTypeCrois = (JRadioButtonMenuItem)ordre.getItem(4);
		boutonTriTypeCrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriTypeForme", false); // Methode pour trier les formes par type de forme dans le sens ordonnee
		    }
	    });
		bg.add(boutonTriTypeCrois);

		boutonTriTypeDecrois = (JRadioButtonMenuItem)ordre.getItem(5);
		boutonTriTypeDecrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriTypeForme", true); // Methode pour trier les formes par type de forme dans le sens inverse
		    }
	    });
		bg.add(boutonTriTypeDecrois);
			
		boutonTriDistanceCrois = (JRadioButtonMenuItem)ordre.getItem(6);
		boutonTriDistanceCrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  // Methode pour trier les formes par leur plus grande distance entre deux points en ordre croissant
				  firePropertyChange("TRI", "TriPlusGrandeDistance", false);
		    }
	    });
		bg.add(boutonTriDistanceCrois);

		boutonTriLargeurCrois = (JRadioButtonMenuItem)ordre.getItem(7);
		boutonTriLargeurCrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriLargeur", false); // Methode pour trier les formes par largeur en ordre croissant
		    }
	    });
		bg.add(boutonTriLargeurCrois);

		boutonTriLargeurDecrois = (JRadioButtonMenuItem)ordre.getItem(8);
		boutonTriLargeurDecrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriLargeur", true); // Methode pour trier les formes par largeur en ordre decroissant
		    }
	    });
		bg.add(boutonTriLargeurDecrois);

		boutonTriHauteurCrois = (JRadioButtonMenuItem)ordre.getItem(9);
		boutonTriHauteurCrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriHauteur", false); // Methode pour trier les formes par hauteur en ordre croissant
			}
	    });
		bg.add(boutonTriHauteurCrois);

		boutonTriHauteurDecrois = (JRadioButtonMenuItem)ordre.getItem(10);
		boutonTriHauteurDecrois.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriHauteur", true); // Methode pour trier les formes par hauteur en ordre decroissant
			
		    }
	    });
		bg.add(boutonTriHauteurDecrois);

		boutonTriOriginal = (JRadioButtonMenuItem)ordre.getItem(11);
		boutonTriOriginal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriOriginal", false); // Methode pour trier les formes selon l'ordre original
			}
	    });
		bg.add(boutonTriOriginal);
		
		add(ordre);

	}

	/**
	 *  CrÃ©er le menu "Help". 
	 */
	private void addMenuAide() {
		JMenu menu = creerMenu(MENU_AIDE_TITRE, new String[] { MENU_AIDE_PROPOS });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,  LangueConfig.getResource(MESSAGE_DIALOGUE_A_PROPOS), 
						LangueConfig.getResource(MENU_AIDE_PROPOS), JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(menu);
	}


	/**
	 *  Activer ou désactiver les items du menu selon la sélection. 
	 *  @param etatMenuFenetre : charactère déterminant si des menus doivent être changés selon l'état du menu.
	 *  
	 *  @author: Fabeleu Carole
	 *  @date: 09/06/2015
	 */
	private void rafraichirMenus(char etatMenuFenetre) {
		obtenirFormesMenuItem.setEnabled(!comm.isActif());

		if(etatMenuFenetre == 'I') { // Desactive le menu ordre lors du lancement de l'application
			ordre.setEnabled(false);
		}
		else {
			ordre.setEnabled(!(comm.isActif()));
		}

		if(etatMenuFenetre == 'F') { // Met le curseur des RadioButtons sur le tri original a la fin de l'obtention de formes
			boutonTriOriginal.isSelected(true);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(arg0.getPropertyName().equals("FIN-CONNEXION")) {
			rafraichirMenus('F'); // Mode Fin de connexion
		}
	 }
	
	/**
	 * CrÃ©er un Ã©lÃ©ment de menu Ã  partir d'un champs principal et ses Ã©lÃ©ments
	 * @param titleKey champs principal
	 * @param itemKeys Ã©lÃ©ments
	 * @return le menu
	 */
	private static JMenu creerMenu(String titleKey,String[] itemKeys) {
        JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
        for(int i=0; i < itemKeys.length; ++i) {
           menu.add(new JMenuItem(LangueConfig.getResource(itemKeys[i])));
        }
        return menu;
   }
	
	/**
	 * Créer un élement de menu RadioButton Ã  partir d'un champ principal et ses éléments.
	 * Inspirée du code source fourni dans le laboratoire.
	 * @param titleKey : Champ principal
	 * @param itemKeys : Éléments
	 * @return Le menu
	 *  @author  Carole Fabeleu
	 *  @date:   09/06/2015
	 */
	private static JMenu creerJRadioButtonMenu(String titleKey,String[] itemKeys) {
        JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
        for(int i=0; i < itemKeys.length; ++i) {
           menu.add(new JRadioButtonMenuItem(LangueConfig.getResource(itemKeys[i])));
        }
        return menu;
   }
	
	/**
	 * Définir le récepteur du l'information du choix de tri dans le menu Ordre
	 * @param listener sera alerté lors de l'appel de "firePropertyChanger"
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
}
