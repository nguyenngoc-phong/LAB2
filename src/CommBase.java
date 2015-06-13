/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
Étudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: CommBase.java
Date créé: 2013-05-03
Date dern. modif. 2015-06-12
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Ngoc-Phong Nguyen
2015-05-13 Ajout de CommSocket et modification de creerCommunication() pour que celui-ci communique avec le serveur.
2015-05-25 Modification de stop() pour qu'il appelle aussi commSocket.stop()
2015-05-28 Optimisation de creerCommunication()
2015-06-12 Optimisation de la classe et modification de l'entête
*@author Carole Fabeleu
2015-05-13 Modification de la méthode pour recevoir seulement 10 formes max. avant de fermer la connexion.
*******************************************************/  

import java.beans.PropertyChangeListener;
import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'exécution parallèle.
 */
public class CommBase {
	
	private final int DELAI = 1000;
	private SwingWorker threadComm =null;
	private PropertyChangeListener listenerChaineIn = null;
	private PropertyChangeListener listenerIsActif = null;
	private boolean isActif = false;
	
	private CommSocket commSocket;
	
	/**
	 * Constructeur
	 */
	public CommBase(){
		commSocket = new CommSocket();
	}
	
	/**
	 * Définir le récepteur de l'information reçue dans la communication avec le serveur
	 * @param listener sera alerté lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListenerChaineIn(PropertyChangeListener listener){
		this.listenerChaineIn = listener;
	}
	
	
	/**
	 * Définir le récepteur de l'état de la connexion au serveur
	 * @param listener sera alerté lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListenerIsActif(PropertyChangeListener listener){
		this.listenerIsActif = listener;
	}
	
	/**
	 * Démarre la communication et obtenir formes
	 */
	public void obtenirFormes(){
		creerCommunication();
	}
	
	/**
	 * Arrête la communication
	 */
	public void stop(){
		if(threadComm!=null) {
			threadComm.cancel(true); 
			commSocket.stop(); // Arrête la communication avec le serveur.
			
			if(listenerIsActif != null) {
				threadComm.firePropertyChange("FIN-CONNEXION", null, null); // Information le récepteur de la fin de la connexion
			}
		}
		isActif = false;
	}
	
	/**
	 * Créer le nécessaire pour la communication avec le serveur
	 */
	protected void creerCommunication() {
		
		commSocket.start(); // Démarre la communication avec le serveur.
		
		// Crée seulement si la connexion au serveur fut un succès.
		if (commSocket.isActif()) {
			
			// Crée un fil d'exécusion parallèle au fil courant,
			
			threadComm = new SwingWorker(){
				
				@Override
				protected Object doInBackground() throws Exception {
					int nbFormes = 1;
					while(commSocket.isActif() && nbFormes <= 10) { // Boucle tant que la connexion au serveur est active et qu'on a moins de 10 formes
						
						Thread.sleep(DELAI);
						
						// C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
						
	 					//La méthode suivante alerte l'observateur 
						if(listenerChaineIn!=null){
						   firePropertyChange("ENVOIE-FORME", null, commSocket.getChaineForme()); 
						}
						nbFormes++;
					}
					stop(); // Arrête le fil d'exécution si la connexion au serveur est arrêtée.
					return null;
				}
			};
			if(listenerChaineIn!=null) {
				   threadComm.addPropertyChangeListener(listenerChaineIn); // La méthode "propertyChange" de FentetrePrincipale sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 
			}
			if(listenerIsActif!=null) {
				threadComm.addPropertyChangeListener(listenerIsActif); // La méthode "propertyChange" de MenuFenetre sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 
			}
			threadComm.execute(); // Lance le fil d'exécution parallèle.
			isActif = true;

		}

	}
	
	/**
	 * @return si le fil d'exécution parallèle est actif
	 */
	public boolean isActif(){
		return isActif;
	}
}
