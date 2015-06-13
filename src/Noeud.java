/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: Noeud.java
Date créé: 2015-06-07
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-06-07 Version initiale
2015-05-18 Modification de l'entête
*******************************************************/

/**
 * Cette classe s'occupe des noeud de la liste chaînée.
 * @author Ngoc-Phong Nguyen
 * @date 2015/06/07
 */
public class Noeud {
	private int numNoeud; // Numéro de noeud
	private Forme forme;
	private Noeud noeudSuivant;

	/**
	 * Constructeur
	 * @param numNoeud : numéro de noeud
	 * @param forme : forme à conserver dans le noeud
	 */
	public Noeud(int numNoeud, Forme forme) {
		this.numNoeud = numNoeud;
		this.forme = forme;
		this.noeudSuivant = null;
	}
	
	
	/**
	 * Accesseur du noeud suivant dans la liste chaînée.
	 * @return Le noeud suivant de la liste chaînée.
	 */
	public Noeud noeudSuivant() {
		return noeudSuivant;
	}
	
	
	/**
	 * Mutateur du noeud suivant dans la liste chaînée.
	 * @param noeudSuivant : le nouveau noeud suivant de la liste chaînée.
	 */
	public void setNoeudSuivant(Noeud noeudSuivant) {
		this.noeudSuivant = noeudSuivant;
	}
	
	/**
	 * Accesseur du numéro de noeud
	 * @return Le numéro de noeud
	 */
	public int getNumNoeud() {
		return numNoeud;
	}
	
	/**
	 * Accesseur de la forme conservée dans le noeud
	 * @return La forme conservée dans le noeud 
	 */
	public Forme getForme() {
		return forme;
	}
}
