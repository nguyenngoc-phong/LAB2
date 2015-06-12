/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetrePrincipale.java
Date créé: 2013-05-03
*******************************************************
Le code de l'événement qui réagit à la fermeture de la fênetre par le bouton "X" du GUI est basé sur un exemple sur Internet d'un auteur inconnu sur la page suivante :

http://www.java2s.com/Code/Java/Swing-JFC/Reacttoframecloseaction.htm

*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Ngoc-Phong Nguyen
2015-05-13 Modification de la méthode propertyChange() et ajout de gestionFormes et de sa méthode ajouterForme()
2015-05-25 Ajout de la méthode dessinerFormes() de fenetreFormes
2015-05-28 Ajout de l'événement windowClosing()
*******************************************************/  

import java.awt.BorderLayout;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
 
/**
 * Cette classe représente la fenêtre principale de l'application 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{
	
	private static final long serialVersionUID = -1210804336046370508L;
	private FenetreFormes fenetreFormes;
	
	private GestionFormes gestionFormes; // Classe gérant le tableau de formes

	/**
	 * Constructeur
	 */
	public FenetrePrincipale(CommBase comm){
		final MenuFenetre menu = new MenuFenetre(comm);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH); 
		this.fenetreFormes = new FenetreFormes();
		this.add(fenetreFormes, BorderLayout.CENTER); // Ajoute la fenêtre de forme à la fenêtre principale
		this.pack(); // Ajuste la dimension de la fenêtre principale selon celle de ses composants
		this.setVisible(true); // Rend la fenêtre principale visible.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gestionFormes = new GestionFormes();
		comm.setPropertyChangeListenerMenu(menu);
		
		// Événement qui ferme la connexion au serveur lorsque l'utilisateur ferme la fenêtre par le bouton "X" du GUI.
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				menu.comm.stop();
			}
		});
	}
	
	/**
	 * ajout du code qui applique le tri selon chaque option et redessine
	 * les formes
	 * @author: Fabeleu Carole
	 */
	// Appelé lorsque le sujet lance "firePropertyChanger"
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(arg0.getPropertyName().equals("ENVOIE-FORME")) {
			gestionFormes.ajouterForme((String) arg0.getNewValue()); // Envoie la chaîne contenant les paramètres de la forme reçu du serveur à gestionFormes pour celle-ci l'ajoute au tableau des formes
			fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), false); // Redessine les formes dans le tableau de gestionFormes
		}
		
		if(arg0.getPropertyName().equals("TRI")) {
			
			if ((int) arg0.getNewValue()==1) {
				gestionFormes.trierNoeuds("TriNseq", false); //Appliquer un tri par numSeq croissant
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			
			else if ((int) arg0.getNewValue()==2) {
				gestionFormes.trierNoeuds("TriNseq", true); //Appliquer un tri par numSeq decroissant
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
		
			else if ((int) arg0.getNewValue()==3) {
				gestionFormes.trierNoeuds("TriAire", false); //Appliquer un tri par aire de forme croissant
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			
			else if ((int) arg0.getNewValue()==4) {
				gestionFormes.trierNoeuds("TriAire", true); //Appliquer un tri par aire de forme d�croissante
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			else if ((int) arg0.getNewValue()==5) {
				gestionFormes.trierNoeuds("TriTypeForme", false); //Appliquer un tri par type de forme dans un certain ordre
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			else if ((int) arg0.getNewValue()==6) {
				gestionFormes.trierNoeuds("TriTypeForme", true); //Appliquer un tri par type de forme dans l'ordre inverse
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			else if ((int) arg0.getNewValue()==7) {
				gestionFormes.trierNoeuds("TriPlusGrandeDistance", false); //Appliquer un tri par distance maximale entre 2 points de la forme
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
		  }
		
			else if ((int) arg0.getNewValue()==8) {
				gestionFormes.trierNoeuds("TriLargeur", false); //Appliquer un tri par aire de forme croissant
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			
			else if ((int) arg0.getNewValue()==9) {
				gestionFormes.trierNoeuds("TriLargeur", true); //Appliquer un tri par aire de forme d�croissante
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			else if ((int) arg0.getNewValue()==10) {
				gestionFormes.trierNoeuds("TriHauteur", false); //Appliquer un tri par type de forme dans un certain ordre
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			else if ((int) arg0.getNewValue()==11) {
				gestionFormes.trierNoeuds("TriHauteur", true); //Appliquer un tri par type de forme dans l'ordre inverse
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans le tableau de gestionFormes
			}
			else if ((int) arg0.getNewValue()==12) {
				gestionFormes.trierNoeuds("TriOriginal", false); //Appliquer un tri par distance maximale entre 2 points de la forme
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), false); // Redessine les formes dans le tableau de gestionFormes
		  }
	 }
	
  }
	
}
