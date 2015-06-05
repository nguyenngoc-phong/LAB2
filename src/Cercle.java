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
	private Point centre;
	private int rayon;
	
	/**
	 * Constructeur
	 * @param nseq : numéro de séquence du cercle
	 * @param centreX : coordonnée x du centre du cercle
	 * @param centreY : coordonnée y du centre du cercle
	 * @param rayon : rayon du cercle
	 */
	public Cercle(int nseq, int centreX, int centreY, int rayon) {
		this.nseq = nseq;
		this.centre = new Point(centreX, centreY);
		this.rayon = rayon;
		this.couleur = Color.GREEN;
	}
	
	/**
	 * Accesseur de la coordonnée(x,y) du centre du cercle 
	 * @return La coordonnée(x,y) du centre du cercle
	 */
	public Point getCentre() {
		return (Point) centre.clone();
	}

	/**
	 * Accesseur du rayon du cercle 
	 * @return La rayon du cercle
	 */
	public int getRayon() {
		return rayon;
	}
}
