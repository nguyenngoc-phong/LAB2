/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: Rectangle.java
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
 * Cette classe s'occupe des formes rectangulaires.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Rectangle extends Forme{
	private Point coord;
	private int largeur;
	private int hauteur;
	
	/**
	 * Constructeur
	 * @param nseq : numéro de séquence du rectangle
	 * @param x1 : coordonnée x du coin en haut à gauche du rectangle
	 * @param y1 : coordonnée y du coin en haut à gauche du rectangle
	 * @param x2 : coordonnée x du coin en bas à droite du rectangle
	 * @param y2 : coordonnée y du coin en bas à droite du rectangle
	 */
	public Rectangle(int nseq, int x1, int y1, int x2, int y2) {
		this.nseq = nseq;
		this.coord = new Point(x1, y1);
		this.largeur = x2 - x1;
		this.hauteur = y2 - y1;
		this.couleur = Color.CYAN;
	}
	
	/**
	 * Accesseur de la coordonnée(x,y) du coin en haut à gauche du rectangle
	 * @return La coordonnée(x,y) du coin en haut à gauche du rectangle
	 */
	public Point getCoord() {
		return (Point) coord.clone();
	}
	
	/**
	 * Accesseur de la largeur du rectangle
	 * @return La largeur du rectangle
	 */
	@Override
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Accesseur de la hauteur du rectangle
	 * @return La hauteur du rectangle
	 */
	@Override
	public int getHauteur() {
		return hauteur;
	}
	
	/**
	 * Accesseur de l'aire du rectangle
	 * @return L'aire du rectangle
	 */
	@Override
	public double getAire() {
		return largeur * hauteur;
	}
	
	/**
	 * Accesseur de la plus grande distance entre deux points du rectangle
	 * @return La plus grande distance entre deux points du rectangle
	 */
	@Override
	public double getPlusGrandeDistance() {
		return Math.sqrt(Math.pow(largeur, 2) + Math.pow(hauteur, 2));
	}
}
