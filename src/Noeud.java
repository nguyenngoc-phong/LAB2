
public class Noeud {
	private int numNoeud;
	private Forme forme;
	private Noeud noeudSuivant;
	
	public Noeud(int numNoeud, Forme forme) {
		this.numNoeud = numNoeud;
		this.forme = forme;
		this.noeudSuivant = null;
	}
	
	public Noeud noeudSuivant() {
		return noeudSuivant;
	}
	
	public void setNoeudSuivant(Noeud noeudSuivant) {
		this.noeudSuivant = noeudSuivant;
	}
	
	public int getNumNoeud() {
		return numNoeud;
	}
	
	public Forme getForme() {
		return forme;
	}
}
