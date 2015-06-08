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
	
	public Noeud trier(Noeud noeudTete, String choixTri, boolean triInverse) {
		if (choixTri != null) {
			this.choixTri = choixTri;
			this.triInverse = triInverse;
		}
		
		if (noeudTete == null || noeudTete.noeudSuivant() == null) {
			return noeudTete;
		}
		
		int nbNoeuds = 0;
		Noeud n1 = noeudTete;
		while (n1 != null) {
			nbNoeuds++;
			n1 = n1.noeudSuivant();
		}

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
		
		gauche = trier(gauche, null, false);
		droite = trier(droite, null, false);
		
		Noeud noeudTrie = echangerNoeuds(gauche, droite);
		
		return noeudTrie;
	}
	
	private Noeud echangerNoeuds(Noeud gauche, Noeud droite) {
		Noeud noeudTete = new Noeud(null);
		Noeud n = noeudTete;
		
		while (gauche != null && droite != null) {
			int resultat = comparerNoeuds(gauche, droite);
			if ((resultat == -1 && !triInverse) || (resultat == 1 && triInverse)) {
				n.setNoeudSuivant(new Noeud(gauche.getForme()));
				gauche = gauche.noeudSuivant();
			}
			else if ((resultat == 1 && !triInverse) || (resultat == -1 && triInverse)){
				n.setNoeudSuivant(new Noeud(gauche.getForme()));
				droite = droite.noeudSuivant();
			}
			else {
				n.setNoeudSuivant(new Noeud(gauche.getForme()));
				n = n.noeudSuivant();
				n.setNoeudSuivant(new Noeud(droite.getForme()));
				gauche = gauche.noeudSuivant();
				droite = droite.noeudSuivant();
			}
			n = n.noeudSuivant();
		}
		
		if (gauche == null) {
			n.setNoeudSuivant(droite);
		}
		else {
			n.setNoeudSuivant(gauche);
		}
		
		return noeudTete.noeudSuivant();
	}
	
	private int comparerNoeuds(Noeud gauche, Noeud droite) {
		Forme forme1 = gauche.getForme();
		Forme forme2 = droite.getForme();
		
		if(choixTri.equals("TriNseq")) {
			if(forme1.getNseq() < forme2.getNseq()) {
				return -1;
			}
			else if(forme1.getNseq() > forme2.getNseq()) {
				return 1;
			}
		}
		else if(choixTri.equals("TriAire")) {
			if(forme1.getAire() < forme2.getAire()) {
				return -1;
			}
			else if(forme1.getAire() > forme2.getAire()) {
				return 1;
			}
		}
		else if(choixTri.equals("TriPlusGrandeDistance")) {
			if(forme1.getPlusGrandeDistance() < forme2.getPlusGrandeDistance()) {
				return -1;
			}
			else if(forme1.getPlusGrandeDistance() > forme2.getPlusGrandeDistance()) {
				return 1;
			}
		}
		else if(choixTri.equals("TriLargeur")) {
			if(forme1.getLargeur() < forme2.getLargeur()) {
				return -1;
			}
			else if(forme1.getLargeur() > forme2.getLargeur()) {
				return 1;
			}
		}
		else if(choixTri.equals("TriHauteur")) {
			if(forme1.getHauteur() < forme2.getHauteur()) {
				return -1;
			}
			else if(forme1.getHauteur() > forme2.getHauteur()) {
				return 1;
			}
		}
		return 0;
	}
}
