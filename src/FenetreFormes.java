/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
Date dern. modif. 2015-06-12
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *@author Ngoc-Phong Nguyen
2015-05-18 Ajout de la méthode dessinerFormes() et du contenu dans le constructeur et dans la méthode paintComponent().
2015-05-18 Optimisation de la méthode paintComponent().
2015-06-08 Ajout de la liste cha�n�e de formes noeudTete.
2015-06-08 Modification des m�thodes paintComponent, dessinerFormes et changerTrait. Optimisation de la classe.
2015-06-12 Optimisation de la classe. Modification de l'ent�te et ajout des commentaires.
 *@author Richard Kantchil
2015-06-12 Optimisation de la classe. Modification de l'ent�te et ajout des commentaires.
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
	
	// Liste cha�n�e de formes
	Noeud noeudTete;

	public static final int increment = 40; // Diff�rence en x et y entre les formes tri�es
	private boolean affichageTrie = false; // D�termine si l'affichage est selon un tri ou non

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

				// Dessine � partir du coin en haut � gauche
				if (affichageTrie) {
					g.fillRect(position, position, ca.getLargeur(), ca.getHauteur());

					// Dessine le contour du carr�
					changerTrait(g, true);
					g.drawRect(position, position, ca.getLargeur(), ca.getHauteur());
				}

				// Dessine selon les coordonn�es des formes
				else {
					g.fillRect(ca.getCoordHG().x, ca.getCoordHG().y, ca.getLargeur(), ca.getHauteur());

					// Dessine le contour du carr�
					changerTrait(g, true);
					g.drawRect(ca.getCoordHG().x, ca.getCoordHG().y, ca.getLargeur(), ca.getHauteur());
				}
			}

			else if (f instanceof Rectangle) {

				Rectangle r = (Rectangle) f;
				g.setColor(r.getCouleur());

				// Dessine � partir du coin en haut � gauche
				if (affichageTrie) {
					g.fillRect(position, position, r.getLargeur(), r.getHauteur());

					// Dessine le contour du rectangle
					changerTrait(g, true);
					g.drawRect(position, position, r.getLargeur(), r.getHauteur());
				}
				
				// Dessine selon les coordonn�es des formes
				else {
					g.fillRect(r.getCoordHG().x, r.getCoordHG().y, r.getLargeur(), r.getHauteur());

					// Dessine le contour du rectangle
					changerTrait(g, true);
					g.drawRect(r.getCoordHG().x, r.getCoordHG().y, r.getLargeur(), r.getHauteur());
				}
			}

			else if (f instanceof Cercle) {

				Cercle ce = (Cercle) f;
				g.setColor(ce.getCouleur());

				// Dessine � partir du coin en haut � gauche
				if (affichageTrie) {
					g.fillOval(position, position, ce.getLargeur(), ce.getHauteur());

					// Dessine le contour du cercle
					changerTrait(g, true);
					g.drawRect(position, position, ce.getLargeur(), ce.getHauteur());
				}
				
				// Dessine selon les coordonn�es des formes
				else {
					g.fillOval(ce.getCoordHG().x, ce.getCoordHG().y, ce.getLargeur(), ce.getHauteur());

					// Dessine le contour du cercle
					changerTrait(g, true);
					g.drawRect(ce.getCoordHG().x, ce.getCoordHG().y, ce.getLargeur(), ce.getHauteur());
				}
			}

			else if (f instanceof Ovale) {

				Ovale o = (Ovale) f;
				g.setColor(o.getCouleur());

				// Dessine � partir du coin en haut � gauche
				if (affichageTrie) {
					g.fillOval(position, position, o.getLargeur(), o.getHauteur());

					// Dessine le contour de l'ovale
					changerTrait(g, true);
					g.drawRect(position, position, o.getLargeur(), o.getHauteur());
				}
				
				// Dessine selon les coordonn�es des formes
				else {
					g.fillOval(o.getCoordHG().x, o.getCoordHG().y, o.getLargeur(), o.getHauteur());

					// Dessine le contour de l'ovale
					changerTrait(g, true);
					g.drawRect(o.getCoordHG().x, o.getCoordHG().y, o.getLargeur(), o.getHauteur());
				}
			}

			else if (f instanceof Ligne) {

				Ligne l = (Ligne) f;
				changerTrait(g, false); // Ramene le trait des lignes de la fen�tre � la normale
				g.setColor(l.getCouleur());

				// Dessine � partir du coin en haut � gauche
				if (affichageTrie) {
					g.drawLine(
							position + l.getCoordPRCoordHG(1).x,
							position + l.getCoordPRCoordHG(1).y,
							position + l.getCoordPRCoordHG(2).x,
							position + l.getCoordPRCoordHG(2).y);

					// Dessine le contour de la ligne
					changerTrait(g, true);
					g.drawRect(position, position,
							l.getLargeur(),
							l.getHauteur());

				}
				
				// Dessine selon les coordonn�es des formes
				else {
					g.drawLine(l.getCoord1().x, l.getCoord1().y, l.getCoord2().x, l.getCoord2().y);

					// Dessine le contour de la ligne
					changerTrait(g, true);
					g.drawRect(l.getCoordHG().x, l.getCoordHG().y, l.getLargeur(), l.getHauteur());
				}
			}
			
			n = n.noeudSuivant();
			position = position + increment;
			
		}
	}

	/**
	 * Remplace l'attribut noeudTete par celui reçu de FenetrePrincipale et
	 * dessine les formes dans cette derni�re
	 * @param noeudTete : la liste cha�n�e de formes reçu de FenetrePrincipale
	 * @param affichageTrie : bool�en d�terminant si l'affichage est selon un tri ou non
	 */
	public void dessinerFormes(Noeud noeudTete, boolean affichageTrie) {
		this.noeudTete = noeudTete;
		this.affichageTrie = affichageTrie;
		repaint();
	}

	/**
	 * Modifie l'objet Graphics pour modifier le trait des lignes dessin�es.
	 * @param g : un objet de type Graphics
	 * @param contour : bool�en d�terminant si le trait doit �tre modifi� pour les contours des formes (true) ou les formes lin�aires (false)
	 */
	private void changerTrait(Graphics g, boolean contours) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		// Les pointill�s seront deux fois plus longs que les espaces entre eux.
		float[] style = { 10, 5 };
		if (contours) {
			g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, style, 0));
		}
		else {
			g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_MITER, 10.0f));
		}
	}

	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver l'espace
	 * nécessaire à son affichage
	 */
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}
}
