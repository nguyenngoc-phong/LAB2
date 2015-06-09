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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JComponent;

/**
 * Cette fenêtre gère l'affichage des formes
 * 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent {

	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(500, 500);
	
	// Tableau de formes
	Noeud noeudTete;

	public static final int increment = 40;
	private boolean affichageTrie = false;

	/**
	 * Constructeur
	 */
	public FenetreFormes() {
	}

	/*
	 * Affiche la liste de formes
	 */
	public void paintComponent(Graphics g) {
		
		Noeud n = noeudTete;
		int position = 0;

		while (n != null) {

			Forme f = n.getForme();
			
			// Vérifie quel sous-classe de Forme est la forme f.
			if (f instanceof Carre) {
				Carre ca = (Carre) f;
				g.setColor(ca.getCouleur());
				if (affichageTrie) {
					g.fillRect(position, position, ca.getLargeur(), ca.getHauteur());
					changerContour(g, true);
					g.drawRect(position, position, ca.getLargeur(), ca.getHauteur());
				} else {
					g.fillRect(ca.getCoordHG().x, ca.getCoordHG().y, ca.getLargeur(), ca.getHauteur());
					changerContour(g, true);
					g.drawRect(ca.getCoordHG().x, ca.getCoordHG().y, ca.getLargeur(), ca.getHauteur());
				}
			}

			else if (f instanceof Rectangle) {
				Rectangle r = (Rectangle) f;
				g.setColor(r.getCouleur());
				if (affichageTrie) {
					g.fillRect(position, position, r.getLargeur(), r.getHauteur());
					changerContour(g, true);
					g.drawRect(position, position, r.getLargeur(), r.getHauteur());
				} else {
					g.fillRect(r.getCoordHG().x, r.getCoordHG().y, r.getLargeur(), r.getHauteur());
					changerContour(g, true);
					g.drawRect(r.getCoordHG().x, r.getCoordHG().y, r.getLargeur(), r.getHauteur());
				}
			}

			else if (f instanceof Cercle) {
				Cercle ce = (Cercle) f;
				g.setColor(ce.getCouleur());
				if (affichageTrie) {
					g.fillOval(position, position, ce.getLargeur(), ce.getHauteur());
					changerContour(g, true);
					g.drawRect(position, position, ce.getLargeur(), ce.getHauteur());
				} else {
					g.fillOval(ce.getCoordHG().x, ce.getCoordHG().y, ce.getLargeur(), ce.getHauteur());
					changerContour(g, true);
					g.drawRect(ce.getCoordHG().x, ce.getCoordHG().y, ce.getLargeur(), ce.getHauteur());
				}
			}

			else if (f instanceof Ovale) {
				Ovale o = (Ovale) f;
				g.setColor(o.getCouleur());
				if (affichageTrie) {
					g.fillOval(position, position, o.getLargeur(), o.getHauteur());
					changerContour(g, true);
					g.drawRect(position, position, o.getLargeur(), o.getHauteur());
				} else {
					g.fillOval(o.getCoordHG().x, o.getCoordHG().y, o.getLargeur(), o.getHauteur());
					changerContour(g, true);
					g.drawRect(o.getCoordHG().x, o.getCoordHG().y, o.getLargeur(), o.getHauteur());
				}
			}

			else if (f instanceof Ligne) {
				Ligne l = (Ligne) f;
				changerContour(g, false);
				g.setColor(l.getCouleur());
				if (affichageTrie) {
					g.drawLine(
							position + l.getCoordPRCoordHG(1).x,
							position + l.getCoordPRCoordHG(1).y,
							position + l.getCoordPRCoordHG(2).x,
							position + l.getCoordPRCoordHG(2).y);
					changerContour(g, true);
					g.drawRect(position, position,
							l.getLargeur(),
							l.getHauteur());

				} else {
					g.drawLine(l.getCoord1().x, l.getCoord1().y,
							l.getCoord2().x, l.getCoord2().y);
					changerContour(g, true);
					g.drawRect(l.getCoordHG().x, l.getCoordHG().y, l.getLargeur(), l.getHauteur());
				}
			}
			
			n = n.noeudSuivant();
			position = position + increment;
			
		}
	}

	/**
	 * Remplace l'attribut listeFormes par celui reçu de FenetreFormes et
	 * dessine les formes dans cette première *
	 * 
	 * @param listeFormes
	 *            : le tableau de formes reçu de FenetrePrincipale
	 */
	public void dessinerFormes(Noeud noeudTete) {
		this.noeudTete = noeudTete;
		repaint();
	}

	/**
	 * Modifie l'objet Graphics en vue de dessiner les contours de chaque forme
	 * *
	 * 
	 * @param g
	 *            : un objet de type Graphics
	 */

	private void changerContour(Graphics g, boolean contour) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		float[] style = { 10, 5 };
		/** les pointill�s seront 2 fois plus long que les blancs */
		if (contour) {
			g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, style, 0));
		}
		else {
			g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_MITER, 10.0f));
		}
	}

	/**
	 * ReTransforme l'objet Graphics en vue de dessiner des lignes pleines *
	 * 
	 * @param g
	 *            : un objet de type Graphics
	 */
	@Deprecated
	private void ramenerContour(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		float[] style = { 10, 5 };
		/** les pointill�s seront 2 fois plus long que les blancs */
		g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_MITER, 10.0f));

	}

	/**
	 * Permet de calculer les distances verticales et horizontales entres deux
	 * extremites d'une ligne
	 * 
	 * @param x
	 *            : un entier representant l'abscisse d'un point
	 * @param y
	 *            : un entier representant l'ordonnee d'un point
	 */
	@Deprecated
	private int compareLigne(int x, int y) {
		int difference = x - y;
		if (x < y)
			difference = y - x;
		return difference;
	}
	/**
	 * Permet de centrer la bordure en pointilles autour d'une ligne a partir du
	 * point le plus proche de l'origine
	 * 
	 * @param x
	 *            : un entier representant l'abscisse d'un point
	 * @param y
	 *            : un entier representant l'ordonnee d'un point
	 */
	@Deprecated
	private int minCoordonne(int x, int y) {

		int min = y;
		if (x < y)
			min = x;

		return min;
	}

	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver l'espace
	 * nécessaire à son affichage
	 */
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}

	/**
	 * Mutateur de l'attribut trier
	 * 
	 * @param trier
	 *            : un boolean qui sera affecte a l'attribut trier
	 * 
	 */
	public void setAffichageTrie(boolean affichageTrie) {
		this.affichageTrie = affichageTrie;
	}
}
