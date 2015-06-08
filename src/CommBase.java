/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Ngoc-Phong Nguyen
2015-05-13 Ajout de CommSocket et modification de creerCommunication() pour que celui-ci communique avec le serveur.
2015-05-25 Modification de stop() pour qu'il appelle aussi commSocket.stop()
2015-05-28 Optimisation de creerCommunication()
*******************************************************/  

import java.beans.PropertyChangeListener;
import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'exécution parallèle.
 */
public class CommBase {
	
	private final int DELAI = 1000;
	private SwingWorker threadComm =null;
	private PropertyChangeListener listener = null;
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
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * Démarre la communication
	 */
	public void start(){
		creerCommunication();
	}
	
	/**
	 * Arrête la communication
	 */
	public void stop(){
		if(threadComm!=null) {
			threadComm.cancel(true); 
			commSocket.stop(); // Arrête la communication avec le serveur.
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
			
			// Cr��e un fil d'exécusion parallèle au fil courant,
			
			threadComm = new SwingWorker(){
				
				@Override
				protected Object doInBackground() throws Exception {
					
					while(commSocket.isActif()) { // Boucle tant que la connexion au serveur est active.
						
						Thread.sleep(DELAI);
						
						// C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
						
	 					//La méthode suivante alerte l'observateur 
						if(listener!=null){
						   firePropertyChange("ENVOIE-FORME", null, commSocket.getChaineForme()); 
						}
					}
					stop(); // Arrête le fil d'exécution si la connexion au serveur est arrêtée.
					return null;
				}
			};
			if(listener!=null) {
				   threadComm.addPropertyChangeListener(listener); // La méthode "propertyChange" de ApplicationFormes sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 		
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
