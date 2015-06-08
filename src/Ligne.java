/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: Ligne.java
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
 * Cette classe s'occupe des formes linéaires.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Ligne extends Forme {
	private Point coord1;
	private Point coord2;
	
	/**
	 * Constructeur
	 * @param nseq : numéro de séquence de la ligne
	 * @param x1 : coordonnée x du début de la ligne
	 * @param y1 : coordonnée y du début de la ligne
	 * @param x2 : coordonnée x de la fin de la ligne
	 * @param y2 : coordonnée y de la fin de la ligne
	 */
	public Ligne(int nseq, int x1, int y1, int x2, int y2) {
		this.nseq = nseq;
		this.coord1 = new Point(x1, y1);
		this.coord2 = new Point(x2, y2);
		this.couleur = Color.BLACK;
	}
	
	/**
	 * Accesseur de la coordonnée(x,y) du début de la ligne.
	 * @return La coordonnée(x,y) du début de la ligne.
	 */
	public Point getCoord1() {
		return (Point) coord1.clone();
	}
	
	/**
	 * Accesseur de la coordonnée(x,y) de la fin de la ligne.
	 * @return La coordonnée(x,y) de la fin de la ligne.
	 */
	public Point getCoord2() {
		return (Point) coord2.clone();
	}
	
	/**
	 * Accesseur de l'aire de la ligne.
	 * Son aire est �gale � sa longueur * 1 pixel.
	 * @return L'aire de la ligne
	 */
	@Override
	public double getAire() {
		return getPlusGrandeDistance();
	}
	
	/**
	 * Accesseur de la plus grande distance entre deux points de la ligne.
	 * En d'autres mots, cette m�thode retourne la longueur de la ligne.
	 * @return La plus grande distance entre deux points de la ligne
	 */
	@Override
	public double getPlusGrandeDistance() {
		return Math.sqrt(Math.pow(coord2.x - coord1.x, 2) + Math.pow(coord2.y - coord1.y, 2));
	}

	@Override
	public int getLargeur() {
		return Math.abs(coord2.x - coord1.x);
	}

	@Override
	public int getHauteur() {
		return Math.abs(coord2.y - coord1.y);
	}
}
