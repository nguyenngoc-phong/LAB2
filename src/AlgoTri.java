/******************************************************
Cours: LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: AlgoTri.java
Date créé: 2015-06-07
Date dern. modif. 2015-06-08
*******************************************************
Le code de cette classe est bas� sur l'exemple d'algorithme de tri de liste cha�n�e sur Internet de Program Creek sur la page suivante :

http://www.programcreek.com/2012/11/leetcode-solution-merge-sort-linkedlist-in-java/

*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2013-06-07 Version initiale
2013-06-08 Ajout des commentaires
*******************************************************/

/**
 * Cette classe s'occupe de trier les noeuds selon les paramètres des formes contenues à l'intérieur.
 * @author Ngoc-Phong Nguyen
 * @date 2015/06/07
 */
public class AlgoTri {
	private String choixTri;
	private boolean triInverse; // Booléen permettant de déterminer si le tri est en ordre décroissant ou non.
	
	/**
	 * Constructeur
	 */
	public AlgoTri() {
		choixTri = null;
		triInverse = false;
	}
	
	/**
	 * Trie les noeuds selon le choix de tri envoyé en paramètres.
	 * Elle divise la liste chaînée reçu en paramètres en noeud individuel et les compare entre elles par la suite.
	 * @param noeudTete : noeud-tête de la liste chaînée
	 * @param choixTri : type de tri choisi
	 * @param triInverse : booléen déterminant si le tri est décroissant ou non.
	 * @return
	 */
	public Noeud trier(Noeud noeudTete, String choixTri, boolean triInverse) {
		// Initialisation de choixTri et triInverse seulement lors du premier appel de la méthode trier().
		if (choixTri != null) {
			this.choixTri = choixTri;
			this.triInverse = triInverse;
		}
		
		// Si le noeud reçu en paramètre est nul ou qu'il n'a pas de noeud suivant, retourner la noeud.
		if (noeudTete == null || noeudTete.noeudSuivant() == null) {
			return noeudTete;
		}
		
		// Comptabilise le nombre de noeuds total dans la liste chaînée du noeud reçu en paramètres.
		int nbNoeuds = 0;
		Noeud n1 = noeudTete;
		while (n1 != null) {
			nbNoeuds++;
			n1 = n1.noeudSuivant();
		}
		
		// Détermine où est le milieu de la liste chaînée et la divise en deux en enlevant la référence de la partie de gauche à celle de droite.
		int milieu = nbNoeuds / 2;
		int i = 0;
		Noeud n2 = noeudTete, gauche = noeudTete, droite = null;
		while (n2 != null) {
			i++;
			if (i == milieu) {
				droite = n2.noeudSuivant();
				n2.setNoeudSuivant(null);
			}
			else {
				n2 = n2.noeudSuivant();
			}
		}
		
		
		// Redivise la partie de gauche et celle de droite en plus petites parties en réappellant récursivement la méthode trier().
		gauche = trier(gauche, null, false);
		droite = trier(droite, null, false);
		
		// Fusionne la partie de gauche avec la partie de droite et les réarrange selon le choix de tri en appelant la méthode echangerNoeuds().
		Noeud noeudTrie = echangerNoeuds(gauche, droite);
		
		return noeudTrie;
	}
	
	/**
	 * Fusionne deux listes chaînées ensemble en réarrangeant les références au noeud suivant dans les noeuds eux-même.
	 * @param gauche : la première liste chaînée à fusionner.
	 * @param droite : la deuxième liste chaînée à fusionner.
	 * @return
	 */
	private Noeud echangerNoeuds(Noeud gauche, Noeud droite) {
		// Initialise deux noeuds temporaires : l'une pour garder en mémoire la liste chaînée triée et l'autre pour parcourir celle-ci.
		Noeud noeudTete = new Noeud(-1, null);
		Noeud n = noeudTete;
		
		// Boucle pour fusionner les deux noeuds tant et aussi longtemps que l'une d'entre elles n'est pas nulle.
		while (gauche != null && droite != null) {
			// Comparer les noeuds-tête de la partie de gauche et celle de droite
			int resultat = comparerNoeuds(gauche, droite);
			/*
			 *  Si la partie de gauche est plus petite que celle de droite ou que celle-ci est plus grande que celle de droite mais que le tri est en ordre décroissant,
			 *  ajoute le noeud-tête de la partie de gauche à la fin de la liste chaînée triée et avance sa liste chaînée d'un noeud.
			 */
			if ((resultat == -1 && !triInverse) || (resultat == 1 && triInverse)) {
				n.setNoeudSuivant(new Noeud(gauche.getNumNoeud(), gauche.getForme()));
				gauche = gauche.noeudSuivant();
			}
			/*
			 *  Si la partie de droite est plus petite que celle de gauche ou que celle-ci est plus grande que celle de gauche mais que le tri est en ordre décroissant,
			 *  ajoute le noeud-tête de la partie de droite à la fin de la liste chaînée triée et avance sa liste chaînée d'un noeud.
			 */
			else if ((resultat == 1 && !triInverse) || (resultat == -1 && triInverse)){
				n.setNoeudSuivant(new Noeud(droite.getNumNoeud(), droite.getForme()));
				droite = droite.noeudSuivant();
			}
			/*
			 *  Dans le cas où la partie de gauche est égale à la partie de droite,
			 *  ajoute le noeud-tête de la partie de gauche puis celle de la partie de droite à la fin de la liste chaînée triée et avance leurs listes chaînées d'un noeud.
			 */
			else {
				n.setNoeudSuivant(new Noeud(gauche.getNumNoeud(), gauche.getForme()));
				n = n.noeudSuivant();
				n.setNoeudSuivant(new Noeud(droite.getNumNoeud(), droite.getForme()));
				gauche = gauche.noeudSuivant();
				droite = droite.noeudSuivant();
			}
			// Avance l'itérateur de la liste chaînée triée d'un noeud.
			n = n.noeudSuivant();
		}

		// Ajoute ce qui reste de la partie de droite à la fin chaînée triée si la partie de gauche est nulle.
		if (gauche == null) {
			n.setNoeudSuivant(droite);
		}
		// Ajoute ce qui reste de la partie de gauche à la fin chaînée triée si la partie de droite est nulle.
		else {
			n.setNoeudSuivant(gauche);
		}
		
		// Retourne la liste chainée triée selon le deuxième noeud puisque le premier ne servait qu'à conserver la liste.
		return noeudTete.noeudSuivant();
	}
	
	/**
	 * Comparer les formes contenues dans deux noeuds entre eux selon le tri choisi et retourne le résulat.
	 * @param gauche : Le premier noeud à comparer.
	 * @param droite : Le deuxième noeud à comparer.
	 * @return -1 si l'attribut de la forme du premier noeud est plus petit que celui du deuxième, 1 si c'est l'inverse et 0 si les deux attributs sont égals.
	 */
	private int comparerNoeuds(Noeud gauche, Noeud droite) {
		// Cherche les formes contenues dans les deux noeuds.
		Forme forme1 = gauche.getForme();
		Forme forme2 = droite.getForme();
		
		// Compare le numéro de séquence des formes.
		if(choixTri.equals("TriNseq")) {
			if(forme1.getNseq() < forme2.getNseq()) {
				return -1;
			}
			else if(forme1.getNseq() > forme2.getNseq()) {
				return 1;
			}
		}
		// Compare l'aire des formes.
		else if(choixTri.equals("TriAire")) {
			if(forme1.getAire() < forme2.getAire()) {
				return -1;
			}
			else if(forme1.getAire() > forme2.getAire()) {
				return 1;
			}
		}
		// Compare le type de forme entre deux points des formes.
		else if(choixTri.equals("TriTypeForme")) {
			if(forme1 instanceof Carre && !(forme2 instanceof Carre)) {
				return -1;
			}
			else if(forme1 instanceof Rectangle && !(forme2 instanceof Rectangle)) {
				if(forme2 instanceof Carre) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else if(forme1 instanceof Cercle && !(forme2 instanceof Cercle)) {
				if(forme2 instanceof Carre || forme2 instanceof Rectangle) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else if(forme1 instanceof Ovale && !(forme2 instanceof Ovale)) {
				if(forme2 instanceof Ligne) {
					return -1;
				}
				else {
					return 1;
				}
			}
			else if(!(forme2 instanceof Ligne)) {
				return 1;
			}
		}
		// Compare la plus grande distance entre deux points des formes.
		else if(choixTri.equals("TriPlusGrandeDistance")) {
			if(forme1.getPlusGrandeDistance() < forme2.getPlusGrandeDistance()) {
				return -1;
			}
			else if(forme1.getPlusGrandeDistance() > forme2.getPlusGrandeDistance()) {
				return 1;
			}
		}
		// Compare la largeur des formes.
		else if(choixTri.equals("TriLargeur")) {
			if(forme1.getLargeur() < forme2.getLargeur()) {
				return -1;
			}
			else if(forme1.getLargeur() > forme2.getLargeur()) {
				return 1;
			}
		}
		// Compare la hauteur des formes.
		else if(choixTri.equals("TriHauteur")) {
			if(forme1.getHauteur() < forme2.getHauteur()) {
				return -1;
			}
			else if(forme1.getHauteur() > forme2.getHauteur()) {
				return 1;
			}
		}
		// Compare le num�ro des noeuds.
		else if(choixTri.equals("TriOriginal")) {
			if(gauche.getNumNoeud() < droite.getNumNoeud()) {
				return -1;
			}
			else if(gauche.getNumNoeud() > droite.getNumNoeud()) {
				return 1;
			}
		}
		// Retourne 0 si les attributs des deux formes sont égals.
		return 0;
	}
}
