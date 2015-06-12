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
	//
	private SwingWorker threadComm =null;
	private PropertyChangeListener listener = null;
	private PropertyChangeListener listenerMenu = null;
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
	
	public void setPropertyChangeListenerMenu(PropertyChangeListener listener){
		this.listenerMenu = listener;
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
			threadComm.firePropertyChange("FIN-CONNEXION", null, null);
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
					int compteur = 0;
					while(commSocket.isActif() && compteur <10) { // Boucle tant que la connexion au serveur est active et qu'on a moins de 10 formes
						
						Thread.sleep(DELAI);
						
						// C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
						
	 					//La méthode suivante alerte l'observateur 
						if(listener!=null){
						   firePropertyChange("ENVOIE-FORME", null, commSocket.getChaineForme()); 
						}
						compteur++;
					}
					stop(); // Arrête le fil d'exécution si la connexion au serveur est arrêtée.
					return null;
				}
			};
			if(listener!=null) {
				   threadComm.addPropertyChangeListener(listener); // La méthode "propertyChange" de ApplicationFormes sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 
				   threadComm.addPropertyChangeListener(listenerMenu);
			}
			threadComm.execute(); // Lance le fil d'exécution parallèle.
			isActif = true;
			
		}
		
	}
	
	
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 1 pour le cas de numSeqCroissant
	 * afin que les formes soient affich�es  par ordre de s�quence croissant
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParNumSeqCroissant(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 1); 
		}
		
	}
	
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 2 pour le cas de numSeqCroissant
	 * afin que les formes soient affich�es  par ordre de s�quence d�croissant
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParNumSeqDecroissant(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 2); 
		}
	}
	

	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 3 pour le cas de ParAireCroissant
	 * afin que les formes soient affich�es  par aire de forme croissante
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParAireCroissant(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 3); 
		}	
	}

	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 4 pour le cas de ParAireDeCroissant
	 * afin que les formes soient affich�es  par aire de forme decroissante
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParAireDecroissant(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 4); 
		}
		
	}
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 5 pour le cas de ParTypeForme
	 * afin que les formes soient affich�es  par type de forme dans un certain ordre
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParTypeForme(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 5); 
		}
		
	}
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 6 pour le cas de ParTypeFormeInverse
	 * afin que les formes soient affich�es  par type de forme dans l'ordre inverse
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParTypeFormeInverse(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 6); 
		}
		
	}
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 7 pour le cas de ParDistanceCroissante
	 * afin que les formes soient affich�es  par distance croissante maximale
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParDistanceCroissante(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 7); 
		}
		
	}
	
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 5 pour le cas de ParTypeForme
	 * afin que les formes soient affich�es  par type de forme dans un certain ordre
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParLargeur(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 8); 
		}
		
	}
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 6 pour le cas de ParTypeFormeInverse
	 * afin que les formes soient affich�es  par type de forme dans l'ordre inverse
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParLargeurInverse(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 9); 
		}
		
	}
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 7 pour le cas de ParDistanceCroissante
	 * afin que les formes soient affich�es  par distance croissante maximale
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParHauteur(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 10); 
		}
		
	}
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 7 pour le cas de ParDistanceCroissante
	 * afin que les formes soient affich�es  par distance croissante maximale
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParHauteurInverse(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 11); 
		}
		
	}
	/**
	 * alerte l'observateur avec pour nom "TRI" et indique 7 pour le cas de ParDistanceCroissante
	 * afin que les formes soient affich�es  par distance croissante maximale
	 * @author: Fabeleu Carole
	 */
	@Deprecated
	public void afficherParOrdreOriginal(){
		if(listener!=null) {
			threadComm.firePropertyChange("TRI", null, 12); 
		}
		
	}
	
	/**
	 * @return si le fil d'exécution parallèle est actif
	 */
	public boolean isActif(){
		return isActif;
	}
}
