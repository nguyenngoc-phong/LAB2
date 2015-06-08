
public class Noeud {
	private Forme forme;
	private Noeud noeudSuivant;
	
	public Noeud(Forme forme) {
		this.forme = forme;
		this.noeudSuivant = null;
	}
	
	public Noeud noeudSuivant() {
		return noeudSuivant;
	}
	
	public void setNoeudSuivant(Noeud noeudSuivant) {
		this.noeudSuivant = noeudSuivant;
	}
	
	public Forme getForme() {
		return forme;
	}
}
