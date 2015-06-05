/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: CreationFormes.java
Date créé: 2015-05-13
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Ajout de la méthode creerForme()
2015-05-28 Optimisation de creerForme()
*******************************************************/

/**
 * Cette classe s'occupe de la création des formes.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class CreationFormes {
	private Forme f;
	private DecompChaine decompChaine; // Le décomposeur de chaîne de caractères.

	/**
	 * Constructeur
	 */
	public CreationFormes() {
		decompChaine = new DecompChaine();
	}

	/**
	 * Crée une forme selon la chaîne de caractères reçue.
	 * @param chaineIn : La chaîne de caractères reçue du serveur.
	 * @return La forme nouvellement créée.
	 */
	public Forme creerForme(String chaineIn) {
		try {
			decompChaine.decomposerChaine(chaineIn);
			int nseq = decompChaine.getNseq();
			String type = decompChaine.getType();
			
			// Crée une forme différente selon le type de forme trouvé dans la chaîne de caractères.
			if (type.equals("CARRE")) {
				int x1 = decompChaine.getParametre(0);
				int y1 = decompChaine.getParametre(1);
				int x2 = decompChaine.getParametre(2);
				int y2 = decompChaine.getParametre(3);
				f = new Carre(nseq, x1, y1, x2, y2);
			}
			else if (type.equals("RECTANGLE")) {
				int x1 = decompChaine.getParametre(0);
				int y1 = decompChaine.getParametre(1);
				int x2 = decompChaine.getParametre(2);
				int y2 = decompChaine.getParametre(3);
				f = new Rectangle(nseq, x1, y1, x2, y2);
			}
			else if (type.equals("CERCLE")) {
				int centreX = decompChaine.getParametre(0);
				int centreY = decompChaine.getParametre(1);
				int rayon = decompChaine.getParametre(2);
				f = new Cercle(nseq, centreX, centreY, rayon);
			}
			else if (type.equals("OVALE")) {
				int centreX = decompChaine.getParametre(0);
				int centreY = decompChaine.getParametre(1);
				int rayonH = decompChaine.getParametre(2);
				int rayonV = decompChaine.getParametre(3);
				f = new Ovale(nseq, centreX, centreY, rayonH, rayonV);
			}
			else if (type.equals("LIGNE")) {
				int x1 = decompChaine.getParametre(0);
				int y1 = decompChaine.getParametre(1);
				int x2 = decompChaine.getParametre(2);
				int y2 = decompChaine.getParametre(3);
				f = new Ligne(nseq, x1, y1, x2, y2);
			}
		}
		catch (NullPointerException e) {
		}
		
		return f;
	}
}
