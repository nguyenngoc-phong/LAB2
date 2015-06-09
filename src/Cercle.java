/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: Cercle.java
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
 * Cette classe s'occupe des formes circulaires.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Cercle extends Forme{
	// private Point centre;
	// private int rayon;
	
	/**
	 * Constructeur
	 * @param nseq : numéro de séquence du cercle
	 * @param centreX : coordonnée x du centre du cercle
	 * @param centreY : coordonnée y du centre du cercle
	 * @param rayon : rayon du cercle
	 */
	public Cercle(int nseq, int centreX, int centreY, int rayon) {
		this.nseq = nseq;
		// this.centre = new Point(centreX, centreY);
		// this.rayon = rayon;
		this.coordHG = new Point(centreX - rayon, centreY - rayon);
		this.largeur = 2 * rayon;
		this.hauteur = 2 * rayon;
		this.couleur = Color.GREEN;
	}
	
	/**
	 * Accesseur de la coordonnée(x,y) du centre du cercle 
	 * @return La coordonnée(x,y) du centre du cercle
	 */
	@Deprecated
	public Point getCentre() {
		// return (Point) centre.clone();
		return null;
	}

	/**
	 * Accesseur du rayon du cercle 
	 * @return La rayon du cercle
	 */
	@Deprecated
	public int getRayon() {
		// return rayon;
		return 0;
	}

	/*
	@Deprecated
	@Override
	public int getLargeur() {
		// return 2 * rayon;
		return 0;
	}
	*/

	/*
	@Deprecated
	@Override
	public int getHauteur() {
		// return 2 * rayon;
		return 0;
	}
	*/
	
	/**
	 * Accesseur de l'aire du cercle
	 * @return L'aire du cercle
	 */
	@Override
	public double getAire() {
		return Math.PI * Math.pow(largeur / 2, 2);
	}
	
	/**
	 * Accesseur de la plus grande distance entre deux points du cercle
	 * @return La plus grande distance entre deux points du cercle
	 */
	@Override
	public double getPlusGrandeDistance() {
		return largeur;
	}
}
