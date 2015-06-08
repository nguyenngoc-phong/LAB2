/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: Forme.java
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
 * Cette classe s'occupe des formes en général et c'est elle dont tous les autres classes "forme" hérite.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public abstract class Forme {
	protected int nseq;
	protected Color couleur;
	
	/**
	 * Accesseur du numéro de séquence de la forme
	 * @return Le numéro de séquence de la forme
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
	
	public abstract int getLargeur();
	public abstract int getHauteur();
	public abstract double getAire();
	public abstract double getPlusGrandeDistance();
}
