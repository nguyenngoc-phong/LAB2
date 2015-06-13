/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
…tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: Forme.java
Date cr√©√©: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-06-08 Modification des attributs et des accesseurs
2015-06-12 Modification de l'entÍte
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes en g√©n√©ral et c'est elle dont tous les autres classes "forme" h√©rite.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public abstract class Forme {
	protected int nseq;
	protected Color couleur;
	protected Point coordHG;
	protected int largeur;
	protected int hauteur;
	
	/**
	 * Accesseur du num√©ro de s√©quence de la forme
	 * @return Le num√©ro de s√©quence de la forme
	 */
	public int getNseq() {
		return nseq;
	}

	/**
	 * Accesseur de la couleur de la forme
	 * @return La couleur de la forme
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * Accesseur de la coordonnÈe du coin en haut ‡ gauche de la forme
	 * @return La coordonnÈe du coin en haut ‡ gauche de la forme
	 */
	public Point getCoordHG() {
		return (Point) coordHG.clone();
	}

	/**
	 * Accesseur de la largeur de la forme
	 * @return La largeur de la forme
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Accesseur de la hauteur de la forme
	 * @return La hauteur de la forme
	 */
	public int getHauteur() {
		return hauteur;
	}
	
	/**
	 * Accesseur abstrait de l'aire de la forme
	 * @return L'aire de la forme
	 */
	public abstract double getAire();
	
	/**
	 * Accesseur abstrait de la plus grande distance entre deux points de la forme
	 * @return La plus grande distance entre deux points de la forme
	 */
	public abstract double getPlusGrandeDistance();
}
