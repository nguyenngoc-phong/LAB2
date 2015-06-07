/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Ngoc-Phong Nguyen
2015-05-18 Ajout de la méthode dessinerFormes() et du contenu dans le constructeur et dans la méthode paintComponent().
2015-05-18 Optimisation de la méthode paintComponent().
*******************************************************/  

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Cette fenêtre gère l'affichage des formes 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent{
	
	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(500,500);
	
	// Tableau de formes
	private static Noeud noeudTete = null;
		
	/**
	 * Constructeur
	 */
	public FenetreFormes(){
	}
	
	/*
	 * Affiche la liste de formes 
	 */
	public void paintComponent(Graphics g){
		Noeud n = noeudTete;
		while (n != null) {
			Forme f = n.getForme();
			
			// Vérifie quel sous-classe de Forme est la forme f.
			if(f instanceof Carre) {
				Carre ca = (Carre) f;
				g.setColor(ca.getCouleur());
				g.fillRect(ca.getCoord().x, ca.getCoord().y, ca.getLargeur(), ca.getHauteur());
			}
			else if(f instanceof Rectangle) {
				Rectangle r = (Rectangle) f;
				g.setColor(r.getCouleur());
				g.fillRect(r.getCoord().x, r.getCoord().y, r.getLargeur(), r.getHauteur());
			}
			else if(f instanceof Cercle) {
				Cercle ce = (Cercle) f;
				g.setColor(ce.getCouleur());
				g.fillOval(ce.getCentre().x, ce.getCentre().y, ce.getRayon(), ce.getRayon());
			}
			else if(f instanceof Ovale) {
				Ovale o = (Ovale) f;
				g.setColor(o.getCouleur());
				g.fillOval(o.getCentre().x, o.getCentre().y, o.getRayonH(), o.getRayonV());
			}
			else if(f instanceof Ligne) {
				Ligne l = (Ligne) f;
				g.setColor(l.getCouleur());
				g.drawLine(l.getCoord1().x, l.getCoord1().y, l.getCoord2().x, l.getCoord2().y);
			}
			
			n = n.noeudSuivant();
		}
	}
	
	/**
	 * Remplace l'attribut listeFormes par celui reçu de FenetreFormes et dessine les formes dans cette première
	 * @param listeFormes : le tableau de formes reçu de FenetrePrincipale
	 */
	public void dessinerFormes(Noeud noeudTete){
		this.noeudTete = noeudTete;
		repaint();
	}
	
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver 
	 * l'espace nécessaire à son affichage
	 */
	@Override 
	public Dimension getPreferredSize(){
		return dimension;
	}
}
