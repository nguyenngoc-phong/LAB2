/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: Carre.java
Date créé: 2015-05-13
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Optimisation de la classe
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes carrées.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Carre extends Forme{
	private Point coord; // Coordonnée du coin en haut à gauche du carré.
	private int largeur;
	private int hauteur;
	
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
		this.coord = new Point(x1, y1);
		this.largeur = x2 - x1;
		this.hauteur = y2 - y1;
		this.couleur = Color.BLUE;
	}
	
	/**
	 * Accesseur de la coordonnée(x,y) du coin en haut à gauche du carré 
	 * @return La coordonnée(x,y) du coin en haut à gauche du carré 
	 */
	public Point getCoord() {
		return (Point) coord.clone();
	}
	
	/**
	 * Accesseur de la largeur du carré 
	 * @return La largeur du carré 
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Accesseur de la hauteur du carré 
	 * @return La hauteur du carré 
	 */
	public int getHauteur() {
		return hauteur;
	}
}
