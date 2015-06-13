/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: Carre.java
Date créé: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Optimisation de la classe
2015-06-08 Modification des attributs et des accesseurs
2015-05-18 Modification de l'entête
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes carrées.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Carre extends Forme{
	// Attributs plus utilisés depuis v.1.7 (2015-06-08)
	@Deprecated private Point coord;
	@Deprecated private int largeur;
	@Deprecated private int hauteur;
	
	/**
	 * Constructeur
	 * @param nseq : numéro de séquence du carré
	 * @param x1 : coordonnée x du coin en haut à gauche du carré
	 * @param y1 : coordonnée y du coin en haut à gauche du carré
	 * @param x2 : coordonnée x du coin en bas à droite du carré
	 * @param y2 : coordonnée y du coin en bas à droite du carré
	 */
	public Carre(int nseq, int x1, int y1, int x2, int y2) {
		this.nseq = nseq;
		this.coordHG = new Point(x1, y1);
		this.largeur = x2 - x1;
		this.hauteur = y2 - y1;
		this.couleur = Color.BLUE;
	}
	
	/**
	 * Accesseur de la coordonnée(x,y) du coin en haut à gauche du carré 
	 * @return La coordonnée(x,y) du coin en haut à gauche du carré 
	 */
	@Deprecated
	public Point getCoord() {
		return (Point) coord.clone();
	}
	
	/**
	 * Accesseur de la largeur du carré 
	 * @return La largeur du carré 
	 */
	@Deprecated
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Accesseur de la hauteur du carré 
	 * @return La hauteur du carré 
	 */
	@Deprecated
	public int getHauteur() {
		return hauteur;
	}
	
	/**
	 * Accesseur de l'aire du carré 
	 * @return L'aire du carré 
	 */
	@Override
	public double getAire() {
		return largeur * hauteur;
	}
	
	/**
	 * Accesseur de la plus grande distance entre deux points du carré
	 * @return La plus grande distance entre deux points du carré
	 */
	@Override
	public double getPlusGrandeDistance() {
		return Math.sqrt(Math.pow(largeur, 2) + Math.pow(hauteur, 2));
	}
}
