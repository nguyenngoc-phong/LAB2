/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: CreationFormes.java
Date cr��: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Ajout de la m�thode creerForme()
2015-05-28 Optimisation de creerForme()
2015-05-18 Modification de l'ent�te
*******************************************************/

/**
 * Cette classe s'occupe de la cr�ation des formes.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class CreationFormes {
	private Forme f;
	private DecompChaine decompChaine; // Le d�composeur de cha�ne de caract�res.

	/**
	 * Constructeur
	 */
	public CreationFormes() {
		decompChaine = new DecompChaine();
	}

	/**
	 * Cr�e une forme selon la cha�ne de caract�res re�ue.
	 * @param chaineIn : La cha�ne de caract�res re�ue du serveur.
	 * @return La forme nouvellement cr��e.
	 */
	public Forme creerForme(String chaineIn) {
		try {
			decompChaine.decomposerChaine(chaineIn);
			int nseq = decompChaine.getNseq();
			String type = decompChaine.getType();
			
			// Cr�e une forme diff�rente selon le type de forme trouv� dans la cha�ne de caract�res.
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
