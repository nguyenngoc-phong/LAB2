/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: Cercle.java
Date cr��: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-06-08 Modification des attributs et des accesseurs
2015-05-18 Modification de l'ent�te
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes circulaires.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Cercle extends Forme{
	// Attributs plus utilis�s depuis v.1.7 (2015-06-08)
	@Deprecated private Point centre;
	@Deprecated private int rayon;
	
	/**
	 * Constructeur
	 * @param nseq : num�ro de s�quence du cercle
	 * @param centreX : coordonn�e x du centre du cercle
	 * @param centreY : coordonn�e y du centre du cercle
	 * @param rayon : rayon du cercle
	 */
	public Cercle(int nseq, int centreX, int centreY, int rayon) {
		this.nseq = nseq;
		this.centre = new Point(centreX, centreY);
		this.rayon = rayon;
		this.coordHG = new Point(centreX - rayon, centreY - rayon);
		this.largeur = 2 * rayon;
		this.hauteur = 2 * rayon;
		this.couleur = Color.GREEN;
	}
	
	/**
	 * Accesseur de la coordonn�e(x,y) du centre du cercle 
	 * @return La coordonn�e(x,y) du centre du cercle
	 */
	@Deprecated
	public Point getCentre() {
		return (Point) centre.clone();
	}

	/**
	 * Accesseur du rayon du cercle 
	 * @return La rayon du cercle
	 */
	@Deprecated
	public int getRayon() {
		return rayon;
	}
	
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
