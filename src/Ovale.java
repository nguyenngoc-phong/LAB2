/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: Ovale.java
Date créé: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-06-08 Modification des attributs et des accesseurs
2015-05-18 Modification de l'entête
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes elliptiques.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Ovale extends Forme{
	// Attributs plus utilisés depuis v.1.7 (2015-06-08)
	@Deprecated private Point centre;
	@Deprecated private int rayonH;
	@Deprecated private int rayonV;
	
	/**
	 * Constructeur
	 * @param nseq : numéro de séquence de l'ovale
	 * @param centreX : coordonnée x du centre de l'ovale
	 * @param centreY : coordonnée y du centre de l'ovale
	 * @param rayonH : rayon horizontale de l'ovale
	 * @param rayonV : rayon verticale de l'ovale
	 */
	public Ovale(int nseq, int centreX, int centreY, int rayonH, int rayonV) {
		this.nseq = nseq;
		this.centre = new Point(centreX, centreY);
		this.rayonH = rayonH;
		this.rayonV = rayonV;
		this.coordHG = new Point(centreX - rayonH, centreY - rayonV);
		this.largeur = 2 * rayonH;
		this.hauteur = 2 * rayonV;
		this.couleur = Color.RED;
	}

	/**
	 * Accesseur de la coordonnée(x,y) du centre de l'ovale
	 * @return La coordonnée(x,y) du centre de l'ovale
	 */
	@Deprecated
	public Point getCentre() {
		return (Point) centre.clone();
	}

	/**
	 * Accesseur du rayon horizontale de l'ovale
	 * @return Le rayon horizontale de l'ovale
	 */
	@Deprecated
	public int getRayonH() {
		return rayonH;
	}

	/**
	 * Accesseur du rayon verticale de l'ovale
	 * @return Le rayon verticale de l'ovale
	 */
	@Deprecated
	public int getRayonV() {
		return rayonV;
	}
	
	/**
	 * Accesseur de l'aire de l'ovale
	 * @return L'aire de l'ovale
	 */
	@Override
	public double getAire() {
		return Math.PI * (largeur / 2) * (hauteur / 2);
	}
	
	/**
	 * Accesseur de la plus grande distance entre deux points de l'ovale
	 * @return La plus grande distance entre deux points de l'ovale
	 */
	@Override
	public double getPlusGrandeDistance() {
		if (largeur > hauteur) {
			return largeur;
		}
		else {
			return hauteur;
		}
	}
}
