/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: DecompChaine.java
Date créé: 2015-05-13
*******************************************************
Le code des Pattern p1 et p2 sont basés sur l'exemple présenté sur la page de l'énoncé du laboratoire 1:

https://cours.etsmtl.ca/log121/

Cliquez sur l'onglet Laboratoires du menu à gauche de l'écran, puis sur le lien "Énoncé du lab 1" dans le tableau des laboratoires sur "Laboratoires"
et finalement sur le lien "cet exemple" sous le point 6 des contraintes de laboratoire.
*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Ajout des attributs et de decomposerChaine()
2015-05-28 Ajout des attributs nseq, forme, tabParametres et MAX_TABPARAMETRES et de la méthode rafraichirDonnees. Optimation de decomposerChaine().
*******************************************************/
import java.util.regex.*;

/**
 * Cette classe s'occupe de décomposer la chaîne de caractères reçu du serveur et d'en extraire les paramètres.
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class DecompChaine {
	private Pattern p1; // Pattern pour la chaîne en général.
	private Pattern p2; // Pattern pour les paramètres entre les balises.
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
	 * Décompose la chaîne de caractères reçu du serveur et en extrait les paramètres.
	 * @param chaineIn : La chaîne de caractères reçu du serveur.
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
	 * Réinitialise les paramètres à zéro.
	 */
	private void reinitialiserParametres() {
		nseq = 0;
		type = null;
		tabParametres = new int[MAX_TABPARAMETRES];
	}
	
	/**
	 * Accesseur du numéro de séquence extraite de la chaîne.
	 * @return Le numéro de séquence extraite de la chaîne.
	 */
	public int getNseq() {
		return nseq;
	}
	
	/**
	 * Accesseur du type de forme extrait de la chaîne.
	 * @return Le type de forme extrait de la chaîne.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Accesseur des paramètres entre les balises extraits de la chaîne.
	 * @return Les paramètres entre les balises extraits de la chaîne.
	 */
	public int getParametre(int i) {
		return tabParametres[i];
	}
}
