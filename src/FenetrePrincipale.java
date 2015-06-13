/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: FenetrePrincipale.java
Date créé: 2013-05-03
Date dern. modif. 2015-06-12
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
2015-06-12 Optimisation de la classe. Modification de l'entête.
*@author Carole Fabeleu
2015-06-08 Modification de la méthode propertyChange() pour réagir au changement du type de tri du menu MenuFenetre
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
	
	private GestionFormes gestionFormes; // Classe gérant la liste cha�n�e de formes

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

		comm.setPropertyChangeListenerIsActif(menu);
		menu.addPropertyChangeListener(this);
		
		// Événement qui ferme la connexion au serveur lorsque l'utilisateur ferme la fenêtre par le bouton "X" du GUI.
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				menu.comm.stop();
			}
		});
	}
	
	/**
	 * Appelé lorsque le sujet lance "firePropertyChanger"
	 * @author: Fabeleu Carole
	 * 2015-06-08 Ajout du code pour que la méthode réagisse au changement du type de tri du menu MenuFenetre.
	 * @author: Ngoc-Phong Nguyen
	 * 2015-06-12 Optimisation de la méthode.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(arg0.getPropertyName().equals("ENVOIE-FORME")) {
			gestionFormes.ajouterForme((String) arg0.getNewValue()); // Envoie la chaîne contenant les paramètres de la forme reçu du serveur à gestionFormes pour celle-ci l'ajoute � sa liste cha�n�e de formes
			fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), false); // Redessine les formes dans la fenêtre FenetreFormes selon les coordonn�es des formes.
		}
		else if(arg0.getPropertyName().equals("TRI")) {
			gestionFormes.trierNoeuds((String) arg0.getOldValue(), (Boolean) arg0.getNewValue()); //Appliquer un tri selon le type reçu en paramètre
			
			if(!arg0.getOldValue().equals("TriOriginal")) {
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), true); // Redessine les formes dans la fenêtre FenetreFormes selon le tri
			}
			else {
				fenetreFormes.dessinerFormes(gestionFormes.getNoeudTete(), false); // Redessine les formes dans la fenêtre FenetreFormes selon l'ordre original
			}
		}
	}
}
