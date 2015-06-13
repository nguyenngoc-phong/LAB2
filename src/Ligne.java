/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
…tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: Ligne.java
Date cr√©√©: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-06-08 Modification des attributs et des accesseurs
2015-05-18 Modification de l'entÍte
*******************************************************/
import java.awt.Color;
import java.awt.Point;

/**
 * Cette classe s'occupe des formes lin√©aires.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class Ligne extends Forme {
	private Point coord1;
	private Point coord2;
	
	/**
	 * Constructeur
	 * @param nseq : num√©ro de s√©quence de la ligne
	 * @param x1 : coordonn√©e x du d√©but de la ligne
	 * @param y1 : coordonn√©e y du d√©but de la ligne
	 * @param x2 : coordonn√©e x de la fin de la ligne
	 * @param y2 : coordonn√©e y de la fin de la ligne
	 */
	public Ligne(int nseq, int x1, int y1, int x2, int y2) {
		this.nseq = nseq;
		this.coord1 = new Point(x1, y1);
		this.coord2 = new Point(x2, y2);
		this.coordHG = new Point(Math.min(x1, x2), Math.min(y1, y2));
		this.largeur = Math.abs(x2 - x1);
		this.hauteur = Math.abs(y2 - y1);
		this.couleur = Color.BLACK;
	}
	
	/**
	 * Accesseur de la coordonn√©e(x,y) du d√©but de la ligne.
	 * @return La coordonn√©e(x,y) du d√©but de la ligne.
	 */
	public Point getCoord1() {
		return (Point) coord1.clone();
	}
	
	/**
	 * Accesseur de la coordonn√©e(x,y) de la fin de la ligne.
	 * @return La coordonn√©e(x,y) de la fin de la ligne.
	 */
	public Point getCoord2() {
		return (Point) coord2.clone();
	}
	
	/**
	 * Accesseur de l'aire de la ligne.
	 * Son aire est Ègale ‡ sa longueur * 1 pixel.
	 * @return L'aire de la ligne
	 */
	@Override
	public double getAire() {
		return getPlusGrandeDistance();
	}
	
	/**
	 * Accesseur de la plus grande distance entre deux points de la ligne.
	 * En d'autres mots, cette mÈthode retourne la longueur de la ligne.
	 * @return La plus grande distance entre deux points de la ligne
	 */
	@Override
	public double getPlusGrandeDistance() {
		return Math.sqrt(Math.pow(coord2.x - coord1.x, 2) + Math.pow(coord2.y - coord1.y, 2));
	}
	
	/**
	 * Accesseur d'une des coordonnÈes de la ligne (coord1 ou coord2) par rapport au coin en haut ‡ gauche de la ligne.
	 * @param numCoordTrie : Le numÈro du point cherchÈ.
	 * @return L'une des coordonnÈes de la ligne (coord1 ou coord2) par rapport au coin en haut ‡ gauche de la ligne.
	 */
	public Point getCoordPRCoordHG(int numCoordTrie) {
		Point coordTrie = new Point(); // Initialise le point de retour ‡ (0, 0)
		if(numCoordTrie == 1) {
			coordTrie.x = coord1.x - coordHG.x; // Donne la diffÈrence en x entre le point coord1 et le coin en haut ‡ gauche de la ligne.
			coordTrie.y = coord1.y - coordHG.y; // Donne la diffÈrence en y entre le point coord1 et le coin en haut ‡ gauche de la ligne.
		}
		if(numCoordTrie == 2) {
			coordTrie.x = coord2.x - coordHG.x; // Donne la diffÈrence en x entre le point coord2 et le coin en haut ‡ gauche de la ligne.
			coordTrie.y = coord2.y - coordHG.y; // Donne la diffÈrence en y entre le point coord2 et le coin en haut ‡ gauche de la ligne.
		}
		// La coordonnÈe cherchÈe.
		return coordTrie;
	}
}
