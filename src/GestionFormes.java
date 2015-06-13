/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: GestionFormes.java
Date créé: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Le code du IDLogger est basé sur l'exemple présenté sur la page de l'énoncé du laboratoire 1: https://cours.etsmtl.ca/log121/
Cliquez sur l'onglet Laboratoires du menu à gauche de l'écran, puis sur le lien "Énoncé du lab 1" dans le tableau des laboratoires sur "Laboratoires".
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Optimisation de la méthode ajouterForme() pour que les nouvelles formes s'ajoute AU DESSUS des plus vieilles
2015-05-25 Ajout de IDLogger et de ses méthodes
2015-06-08 Modification du tableau de foormes pour une liste chaînée de formes et ajout de AlgoTri.
2015-06-12 Optimisation de la classe. Modification de l'entête.
*******************************************************/
import ca.etsmtl.log.util.IDLogger;

/**
 * Cette classe s'occupe de gérer la liste chaînée dans lequel les formes sont gardés.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class GestionFormes {
	private CreationFormes creationFormes;
	private Noeud noeudTete;
	private final int MAX_FORMES = 10;
	private int nbNoeuds;
	private IDLogger logger;
	private AlgoTri algoTri;
	
	/**
	 * Constructeur
	 */
	public GestionFormes() {
		creationFormes = new CreationFormes();
		noeudTete = null;
		nbNoeuds = 0;
		logger = IDLogger.getInstance();
		algoTri = new AlgoTri();
	}
	
	/**
	 * Ajoute une nouvelle forme à la liste chaînée de formes. Supprime la plus vieille forme lorsque la liste chaînée est plein.
	 * @param chaineIn : chaîne de caractères contenant les paramètre de la nouvelle forme
	 */
	public void ajouterForme(String chaineIn) {
		Forme forme = creationFormes.creerForme(chaineIn);
		
		if (noeudTete == null) {
			noeudTete = new Noeud(nbNoeuds, forme);
		}
		else {
			Noeud n = noeudTete;
			while (n.noeudSuivant() != null) {
				n = n.noeudSuivant();
			}
			n.setNoeudSuivant(new Noeud(nbNoeuds, forme));
		}
		
		if (nbNoeuds >= MAX_FORMES){
			noeudTete = noeudTete.noeudSuivant();
		}
		
		logger.logID(forme.getNseq());
		nbNoeuds++;
	}
	
	/**
	 * Trie les noeuds de la liste chaînée de formes selon le choix de tri.
	 * Cette méthode peut aussi trier en ordre inverse.
	 * @param choixTri : choix de tri selectionné dans le menu MenuFenêtre.
	 * @param triInverse : booléen déterminant si le tri se fait en ordre inverse ou non.
	 */
	public void trierNoeuds(String choixTri, boolean triInverse) {
		noeudTete = algoTri.trier(noeudTete, choixTri, triInverse);
	}

	/**
	 * Accesseur du clone du tableau de formes listeFormes[]
	 * @return Un clone du tableau de formes listeFormes[]
	 */
	public Noeud getNoeudTete() {
		return noeudTete;
	}
}
