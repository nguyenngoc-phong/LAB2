/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date crÃ©Ã©: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Ngoc-Phong Nguyen
2015-05-25 Ajout de la ligne de code qui dÃ©sactive l'option "ArrÃªter" Ã  l'ouverture de l'application
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

	private static final int  	MENU_FICHIER_QBTENIR_FORME_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char 	MENU_FICHIER_QBTENIR_FORME_TOUCHE_RACC = KeyEvent.VK_O;
	private static final String
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_FICHIER_QBTENIR_FORME = "app.frame.menus.file.form",
			
			
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
	
	private JMenu fichier, ordre;
	private JMenuItem obtenirFormesMenuItem;
	private JRadioButtonMenuItem br1, br2, br3, br4, br5, br6, br7, br8, br9, br10, br11, br12, item;
	
	CommBase comm; // Pour activer/dÃ©sactiver la communication avec le serveur
	
	private PropertyChangeListener listener = null;
	
	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuFichier();
		addMenuOrdre();
		addMenuAide();
	}

	/**
	 *  Creation du menu Fichier 
	 *  source: inspirée du code source fourni  dans le laboratoire
	 *  @author  Carole Fabeleu
	 *  @date:   2015-06-05 
	 */
	protected void addMenuFichier() {
		fichier = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_QBTENIR_FORME });
		
		obtenirFormesMenuItem = fichier.getItem(0);
		obtenirFormesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comm.obtenirFormes();      //creer méthode dans commBase pour obtenir les formes
				if(comm.isActif()) {
					ordre.setEnabled(false);
					br12.setSelected(true);
				}
				rafraichirMenus();
			}
			
		});
		fichier.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QBTENIR_FORME_TOUCHE_RACC,
						MENU_FICHIER_QBTENIR_FORME_TOUCHE_MASK));
		add(fichier);
	}
	
	/**
	 *  Creation du menu Ordre 
	 *  source: inspirée du code source fourni  dans le laboratoire
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
		ButtonGroup bg = new ButtonGroup();

		br1 = (JRadioButtonMenuItem)ordre.getItem(0);
			
			br1.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0) {
			  firePropertyChange("TRI", "TriNseq", false);
			// comm.afficherParNumSeqCroissant();  //methode pour afficher les formes par numero de séquence croissant
			
		  }
		});

		br2 = (JRadioButtonMenuItem)ordre.getItem(1);
			
			br2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				firePropertyChange("TRI", "TriNseq", true);
			// comm.afficherParNumSeqDecroissant();       //methode pour afficher les formes par numero de séquence décroissant
			
		    }
	    });
			
		br3 = (JRadioButtonMenuItem)ordre.getItem(2);
			
			br3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriAire", false);
			//comm.afficherParAireCroissant();   //methode pour afficher les formes par aire de  forme croissante
			
		    }
	    });	

		br4 = (JRadioButtonMenuItem)ordre.getItem(3);
		
			br4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriAire", true);
			//comm.afficherParAireDecroissant();         //methode pour afficher les formes par aire de  forme décroissante
			
		    }
	    });

		br5 = (JRadioButtonMenuItem)ordre.getItem(4);
			
			br5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriTypeForme", false);
			//comm.afficherParTypeForme();			 //methode pour afficher les formes par type de formes par ordre
			
		    }
	    });

		br6 = (JRadioButtonMenuItem)ordre.getItem(5);
			
			br6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriTypeForme", true);
			// comm.afficherParTypeFormeInverse();			 //methode pour afficher les formes par type de forme dans l'ordre inverse
			
		    }
	    });
			
		br7 = (JRadioButtonMenuItem)ordre.getItem(6);
		br7.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			  firePropertyChange("TRI", "TriPlusGrandeDistance", false);
		// comm.afficherParDistanceCroissante();     //methode pour afficher les formes par distance maximale
		
	    }
	    });

		br8 = (JRadioButtonMenuItem)ordre.getItem(7);
			
			br8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriLargeur", false);
			// comm.afficherParLargeur();			 //methode pour afficher les formes par type de formes par ordre
			
		    }
	    });

		br9 = (JRadioButtonMenuItem)ordre.getItem(8);
			
			br9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriLargeur", true);
			// comm.afficherParLargeurInverse();			 //methode pour afficher les formes par type de forme dans l'ordre inverse
			
		    }
	    });
		br10 = (JRadioButtonMenuItem)ordre.getItem(9);
		
			br10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriHauteur", false);
			// comm.afficherParHauteur();     //methode pour afficher les formes par distance maximale
		
			}
	    });

		br11 = (JRadioButtonMenuItem)ordre.getItem(10);
			
			br11.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriHauteur", true);
			// comm.afficherParHauteurInverse();			 //methode pour afficher les formes par type de forme dans l'ordre inverse
			
		    }
	    });
		br12 = (JRadioButtonMenuItem)ordre.getItem(11);
		
			br12.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				  firePropertyChange("TRI", "TriOriginal", false);
			// comm.afficherParOrdreOriginal();     //methode pour afficher les formes par distance maximale
		
			}
	    });
		
		add(ordre);
		
		bg.add(br1);
		bg.add(br2);
		bg.add(br3);
		bg.add(br4);
		bg.add(br5);
		bg.add(br6);
		bg.add(br7);
		bg.add(br8);
		bg.add(br9);
		bg.add(br10);
		bg.add(br11);
		bg.add(br12);
		
		ordre.setEnabled(false);
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
	 *  @author: Fabeleu Carole
	 *  @date: 09/06/2015
	 */
	private void rafraichirMenus() {
		obtenirFormesMenuItem.setEnabled(!comm.isActif());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(arg0.getPropertyName().equals("FIN-CONNEXION")) {
			obtenirFormesMenuItem.setEnabled(!comm.isActif());
			ordre.setEnabled(true);
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
	 * Créer un élement de menu RadioButton Ã  partir d'un champ principal et ses elements
	 * @param titleKey champs principal
	 * @param itemKeys elements
	 * @return le menu
	 *  source: inspirée du code source fourni  dans le laboratoire
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
	
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
}
