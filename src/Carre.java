/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: Carre.java
Date cr��: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Optimisation de la classe
2015-06-08 Modification des attributs et des accesseurs
2015-05-18 Modification de l'ent�te
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes carr�es.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Carre extends Forme{
	// Attributs plus utilis�s depuis v.1.7 (2015-06-08)
	@Deprecated private Point coord;
	@Deprecated private int largeur;
	@Deprecated private int hauteur;
	
	/**
	 * Constructeur
	 * @param nseq : num�ro de s�quence du carr�
	 * @param x1 : coordonn�e x du coin en haut � gauche du carr�
	 * @param y1 : coordonn�e y du coin en haut � gauche du carr�
	 * @param x2 : coordonn�e x du coin en bas � droite du carr�
	 * @param y2 : coordonn�e y du coin en bas � droite du carr�
	 */
	public Carre(int nseq, int x1, int y1, int x2, int y2) {
		this.nseq = nseq;
		this.coordHG = new Point(x1, y1);
		this.largeur = x2 - x1;
		this.hauteur = y2 - y1;
		this.couleur = Color.BLUE;
	}
	
	/**
	 * Accesseur de la coordonn�e(x,y) du coin en haut � gauche du carr� 
	 * @return La coordonn�e(x,y) du coin en haut � gauche du carr� 
	 */
	@Deprecated
	public Point getCoord() {
		return (Point) coord.clone();
	}
	
	/**
	 * Accesseur de la largeur du carr� 
	 * @return La largeur du carr� 
	 */
	@Deprecated
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Accesseur de la hauteur du carr� 
	 * @return La hauteur du carr� 
	 */
	@Deprecated
	public int getHauteur() {
		return hauteur;
	}
	
	/**
	 * Accesseur de l'aire du carr� 
	 * @return L'aire du carr� 
	 */
	@Override
	public double getAire() {
		return largeur * hauteur;
	}
	
	/**
	 * Accesseur de la plus grande distance entre deux points du carr�
	 * @return La plus grande distance entre deux points du carr�
	 */
	@Override
	public double getPlusGrandeDistance() {
		return Math.sqrt(Math.pow(largeur, 2) + Math.pow(hauteur, 2));
	}
}
