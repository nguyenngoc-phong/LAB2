/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: Ovale.java
Date créé: 2015-05-13
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes elliptiques.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Ovale extends Forme{
	// private Point centre;
	// private int rayonH;
	// private int rayonV;
	
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
		// this.centre = new Point(centreX, centreY);
		// this.rayonH = rayonH;
		// this.rayonV = rayonV;
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
		// return (Point) centre.clone();
		return null;
	}

	/**
	 * Accesseur du rayon horizontale de l'ovale
	 * @return Le rayon horizontale de l'ovale
	 */
	@Deprecated
	public int getRayonH() {
		// return rayonH;
		return 0;
	}

	/**
	 * Accesseur du rayon verticale de l'ovale
	 * @return Le rayon verticale de l'ovale
	 */
	@Deprecated
	public int getRayonV() {
		// return rayonV;
		return 0;
	}

	/*
	@Deprecated
	@Override
	public int getLargeur() {
		// return 2 * rayonH;
		return 0;
	}
	*/

	/*
	@Deprecated
	@Override
	public int getHauteur() {
		// return 2 * rayonV;
		return 0;
	}
	*/
	
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
