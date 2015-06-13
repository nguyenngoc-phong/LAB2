/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: DecompChaine.java
Date cr��: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Le code des Pattern p1 et p2 sont bas�s sur l'exemple pr�sent� sur la page de l'�nonc� du laboratoire 1:

https://cours.etsmtl.ca/log121/

Cliquez sur l'onglet Laboratoires du menu � gauche de l'�cran, puis sur le lien "�nonc� du lab 1" dans le tableau des laboratoires sur "Laboratoires"
et finalement sur le lien "cet exemple" sous le point 6 des contraintes de laboratoire.
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Ajout des attributs et de decomposerChaine()
2015-05-28 Ajout des attributs nseq, forme, tabParametres et MAX_TABPARAMETRES et de la m�thode rafraichirDonnees. Optimation de decomposerChaine().
2015-05-18 Modification de l'ent�te
*******************************************************/
import java.util.regex.*;

/**
 * Cette classe s'occupe de d�composer la cha�ne de caract�res re�u du serveur et d'en extraire les param�tres.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class DecompChaine {
	private Pattern p1; // Pattern pour la cha�ne en g�n�ral.
	private Pattern p2; // Pattern pour les param�tres entre les balises.
	private Matcher m;
	private int nseq;
	private String type;
	private int[] tabParametres;
	private static final int MAX_TABPARAMETRES = 4;

	/**
	 * Constructeur
	 */
	public DecompChaine() {
		this.p1 = Pattern.compile("(.*)\\s<(.*)>\\s(.*)</\\2>");
		this.p2 = Pattern.compile("\\s");
		this.tabParametres = new int[MAX_TABPARAMETRES];
	}
	
	/**
	 * D�compose la cha�ne de caract�res re�u du serveur et en extrait les param�tres.
	 * @param chaineIn : La cha�ne de caract�res re�u du serveur.
	 */
	public void decomposerChaine(String chaineIn) {
		reinitialiserParametres();
		
		m = p1.matcher(chaineIn);
		if (m.find()) {
			nseq = Integer.parseInt(m.group(1));
			type = m.group(2);
			
			String[] tabTemp = p2.split(m.group(3));
			
			int i = 0;
	        for(String s : tabTemp) {
	        	tabParametres[i] = Integer.parseInt(s);
	            i++;
	        }
		}
	}
	
	/**
	 * R�initialise les param�tres � z�ro.
	 */
	private void reinitialiserParametres() {
		nseq = 0;
		type = null;
		tabParametres = new int[MAX_TABPARAMETRES];
	}
	
	/**
	 * Accesseur du num�ro de s�quence extraite de la cha�ne.
	 * @return Le num�ro de s�quence extraite de la cha�ne.
	 */
	public int getNseq() {
		return nseq;
	}
	
	/**
	 * Accesseur du type de forme extrait de la cha�ne.
	 * @return Le type de forme extrait de la cha�ne.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Accesseur des param�tres entre les balises extraits de la cha�ne.
	 * @return Les param�tres entre les balises extraits de la cha�ne.
	 */
	public int getParametre(int i) {
		return tabParametres[i];
	}
}
