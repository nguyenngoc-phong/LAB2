/******************************************************
Cours: LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: AlgoTri.java
Date cr��: 2015-06-07
Date dern. modif. 2015-06-12
*******************************************************
Le code de cette classe est bas� sur l'exemple d'algorithme de tri de liste cha�n�e sur Internet de Program Creek sur la page suivante :

http://www.programcreek.com/2012/11/leetcode-solution-merge-sort-linkedlist-in-java/

*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2013-06-07 Version initiale
2013-06-08 Ajout de l'ent�te et des commentaires
2013-06-12 Ajout du tri par type de forme et par l'ordre original. Correction et optimisation de la classe.
*******************************************************/

/**
 * Cette classe s'occupe de trier les noeuds selon les param�tres des formes contenues � l'int�rieur.
 * @author Ngoc-Phong Nguyen
 * @date 2015/06/07
 */
public class AlgoTri {
	private String choixTri;
	private boolean triInverse; // Bool�en permettant de d�terminer si le tri est en ordre d�croissant ou non.
	
	/**
	 * Constructeur
	 */
	public AlgoTri() {
		choixTri = null;
		triInverse = false;
	}
	
	/**
	 * Trie les noeuds selon le choix de tri envoy� en param�tres.
	 * Elle divise la liste cha�n�e re�u en param�tres en noeud individuel et les compare entre elles par la suite.
	 * @param noeudTete : noeud-t�te de la liste cha�n�e
	 * @param choixTri : type de tri choisi
	 * @param triInverse : bool�en d�terminant si le tri est d�croissant ou non.
	 * @return
	 */
	public Noeud trier(Noeud noeudTete, String choixTri, boolean triInverse) {
		// Initialisation de choixTri et triInverse seulement lors du premier appel de la m�thode trier().
		if (choixTri != null) {
			this.choixTri = choixTri;
			this.triInverse = triInverse;
		}
		
		// Si le noeud re�u en param�tre est nul ou qu'il n'a pas de noeud suivant, retourner la noeud.
		if (noeudTete == null || noeudTete.noeudSuivant() == null) {
			return noeudTete;
		}
		
		// Comptabilise le nombre de noeuds total dans la liste cha�n�e du noeud re�u en param�tres.
		int nbNoeuds = 0;
		Noeud n1 = noeudTete;
		while (n1 != null) {
			nbNoeuds++;
			n1 = n1.noeudSuivant();
		}
		
		// D�termine o� est le milieu de la liste cha�n�e et la divise en deux en enlevant la r�f�rence de la partie de gauche � celle de droite.
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
		
		
		// Redivise la partie de gauche et celle de droite en plus petites parties en r�appellant r�cursivement la m�thode trier().
		gauche = trier(gauche, null, false);
		droite = trier(droite, null, false);
		
		// Fusionne la partie de gauche avec la partie de droite et les r�arrange selon le choix de tri en appelant la m�thode echangerNoeuds().
		Noeud noeudTrie = echangerNoeuds(gauche, droite);
		
		return noeudTrie;
	}
	
	/**
	 * Fusionne deux listes cha�n�es ensemble en r�arrangeant les r�f�rences au noeud suivant dans les noeuds eux-m�me.
	 * @param gauche : la premi�re liste cha�n�e � fusionner.
	 * @param droite : la deuxi�me liste cha�n�e � fusionner.
	 * @return
	 */
	private Noeud echangerNoeuds(Noeud gauche, Noeud droite) {
		// Initialise deux noeuds temporaires : l'une pour garder en m�moire la liste cha�n�e tri�e et l'autre pour parcourir celle-ci.
		Noeud noeudTete = new Noeud(-1, null);
		Noeud n = noeudTete;
		
		// Boucle pour fusionner les deux noeuds tant et aussi longtemps que l'une d'entre elles n'est pas nulle.
		while (gauche != null && droite != null) {
			// Comparer les noeuds-t�te de la partie de gauche et celle de droite
			int resultat = comparerNoeuds(gauche, droite);
			/*
			 *  Si la partie de gauche est plus petite que celle de droite ou que celle-ci est plus grande que celle de droite mais que le tri est en ordre d�croissant,
			 *  ajoute le noeud-t�te de la partie de gauche � la fin de la liste cha�n�e tri�e et avance sa liste cha�n�e d'un noeud.
			 */
			if ((resultat == -1 && !triInverse) || (resultat == 1 && triInverse)) {
				n.setNoeudSuivant(new Noeud(gauche.getNumNoeud(), gauche.getForme()));
				gauche = gauche.noeudSuivant();
			}
			/*
			 *  Si la partie de droite est plus petite que celle de gauche ou que celle-ci est plus grande que celle de gauche mais que le tri est en ordre d�croissant,
			 *  ajoute le noeud-t�te de la partie de droite � la fin de la liste cha�n�e tri�e et avance sa liste cha�n�e d'un noeud.
			 */
			else if ((resultat == 1 && !triInverse) || (resultat == -1 && triInverse)){
				n.setNoeudSuivant(new Noeud(droite.getNumNoeud(), droite.getForme()));
				droite = droite.noeudSuivant();
			}
			/*
			 *  Dans le cas o� la partie de gauche est �gale � la partie de droite,
			 *  ajoute le noeud-t�te de la partie de gauche puis celle de la partie de droite � la fin de la liste cha�n�e tri�e et avance leurs listes cha�n�es d'un noeud.
			 */
			else {
				n.setNoeudSuivant(new Noeud(gauche.getNumNoeud(), gauche.getForme()));
				n = n.noeudSuivant();
				n.setNoeudSuivant(new Noeud(droite.getNumNoeud(), droite.getForme()));
				gauche = gauche.noeudSuivant();
				droite = droite.noeudSuivant();
			}
			// Avance l'it�rateur de la liste cha�n�e tri�e d'un noeud.
			n = n.noeudSuivant();
		}

		// Ajoute ce qui reste de la partie de droite � la fin cha�n�e tri�e si la partie de gauche est nulle.
		if (gauche == null) {
			n.setNoeudSuivant(droite);
		}
		// Ajoute ce qui reste de la partie de gauche � la fin cha�n�e tri�e si la partie de droite est nulle.
		else {
			n.setNoeudSuivant(gauche);
		}
		
		// Retourne la liste chain�e tri�e selon le deuxi�me noeud puisque le premier ne servait qu'� conserver la liste.
		return noeudTete.noeudSuivant();
	}
	
	/**
	 * Comparer les formes contenues dans deux noeuds entre eux selon le tri choisi et retourne le r�sulat.
	 * @param gauche : Le premier noeud � comparer.
	 * @param droite : Le deuxi�me noeud � comparer.
	 * @return -1 si l'attribut de la forme du premier noeud est plus petit que celui du deuxi�me, 1 si c'est l'inverse et 0 si les deux attributs sont �gals.
	 */
	private int comparerNoeuds(Noeud gauche, Noeud droite) {
		// Cherche les formes contenues dans les deux noeuds.
		Forme forme1 = gauche.getForme();
		Forme forme2 = droite.getForme();
		
		// Compare le num�ro de s�quence des formes.
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
		// Compare le type de forme entre deux formes.
		else if(choixTri.equals("TriTypeForme")) {
			// Si la 1ere forme est carr� et l'autre ne l'est pas, retourne -1.
			if(forme1 instanceof Carre && !(forme2 instanceof Carre)) {
				return -1;
			}
			// Si la 1ere forme est rectangulaire.
			else if(forme1 instanceof Rectangle && !(forme2 instanceof Rectangle)) {
				// Si la 2e forme est carr�, retourne 1.
				if(forme2 instanceof Carre) {
					return 1;
				}
				// Autrement, si la 2e forme n'est pas carr�, retourne -1.
				else {
					return -1;
				}
			}
			// Si la 1ere forme est circulaire.
			else if(forme1 instanceof Cercle && !(forme2 instanceof Cercle)) {
				// Si la 2e forme est carr� ou rectangulaire, retourne 1.
				if(forme2 instanceof Carre || forme2 instanceof Rectangle) {
					return 1;
				}
				// Autrement, si la 2e forme n'est pas carr� ou rectangulaire, retourne -1.
				else {
					return -1;
				}
			}
			// Si la 1ere forme est elliptique.
			else if(forme1 instanceof Ovale && !(forme2 instanceof Ovale)) {
				// Si la 2e forme est lin�aire, retourne -1.
				if(forme2 instanceof Ligne) {
					return -1;
				}
				// Autrement, si la 2e forme n'est pas lin�aire, retourne 1.
				else {
					return 1;
				}
			}
			// Autrement, si la 1ere forme est lin�aire et l'autre ne l'est pas, retourne 1.
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
		// Compare les num�ros des noeuds.
		else if(choixTri.equals("TriOriginal")) {
			if(gauche.getNumNoeud() < droite.getNumNoeud()) {
				return -1;
			}
			else if(gauche.getNumNoeud() > droite.getNumNoeud()) {
				return 1;
			}
		}
		// Retourne 0 si les attributs des deux formes sont �gals.
		return 0;
	}
}
