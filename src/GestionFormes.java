/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: GestionFormes.java
Date créé: 2015-05-13
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
*******************************************************/
import ca.etsmtl.log.util.IDLogger;

/**
 * Cette classe s'occupe de gérer le tableau dans lequel les formes sont gardés.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class GestionFormes {
	private CreationFormes creationFormes;
	private Noeud noeudTete;
	private final int MAX_FORMES = 10;
	private int nbNoeuds;
	private IDLogger logger;
	
	/**
	 * Constructeur
	 */
	public GestionFormes() {
		creationFormes = new CreationFormes();
		noeudTete = null;
		nbNoeuds = 0;
		logger = IDLogger.getInstance();
	}
	
	/**
	 * Ajoute une nouvelle forme au tableau de formes. Supprime la plus vieille forme lorsque le tableau est plein.
	 * @param chaineIn : chaîne de caractères contenant les paramètre de la nouvelle forme
	 */
	public void ajouterForme(String chaineIn) {
		Forme forme = creationFormes.creerForme(chaineIn);
		
		if (noeudTete == null) {
			noeudTete = new Noeud(forme);
		}
		else {
			Noeud n = noeudTete;
			while (n.noeudSuivant() != null) {
				n = n.noeudSuivant();
			}
			n.setNoeudSuivant(new Noeud(forme));
		}
		
		if (nbNoeuds < MAX_FORMES) {
			nbNoeuds++;
		}
		else {
			noeudTete = noeudTete.noeudSuivant();
		}
		
		logger.logID(forme.getNseq());
	}

	/**
	 * Accesseur du clone du tableau de formes listeFormes[]
	 * @return Un clone du tableau de formes listeFormes[]
	 */
	public Noeud getNoeudTete() {
		return noeudTete;
	}
}
