/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: CommBase.java
Date cr��: 2013-05-03
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
2015-06-12 Optimisation de la classe et modification de l'ent�te
*@author Carole Fabeleu
2015-05-13 Modification de la m�thode pour recevoir seulement 10 formes max. avant de fermer la connexion.
*******************************************************/  

import java.beans.PropertyChangeListener;
import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'ex�cution parall�le.
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
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le serveur
	 * @param listener sera alert� lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListenerChaineIn(PropertyChangeListener listener){
		this.listenerChaineIn = listener;
	}
	
	
	/**
	 * D�finir le r�cepteur de l'�tat de la connexion au serveur
	 * @param listener sera alert� lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListenerIsActif(PropertyChangeListener listener){
		this.listenerIsActif = listener;
	}
	
	/**
	 * D�marre la communication et obtenir formes
	 */
	public void obtenirFormes(){
		creerCommunication();
	}
	
	/**
	 * Arr�te la communication
	 */
	public void stop(){
		if(threadComm!=null) {
			threadComm.cancel(true); 
			commSocket.stop(); // Arr�te la communication avec le serveur.
			
			if(listenerIsActif != null) {
				threadComm.firePropertyChange("FIN-CONNEXION", null, null); // Information le r�cepteur de la fin de la connexion
			}
		}
		isActif = false;
	}
	
	/**
	 * Cr�er le n�cessaire pour la communication avec le serveur
	 */
	protected void creerCommunication() {
		
		commSocket.start(); // D�marre la communication avec le serveur.
		
		// Cr�e seulement si la connexion au serveur fut un succ�s.
		if (commSocket.isActif()) {
			
			// Cr�e un fil d'ex�cusion parall�le au fil courant,
			
			threadComm = new SwingWorker(){
				
				@Override
				protected Object doInBackground() throws Exception {
					int nbFormes = 1;
					while(commSocket.isActif() && nbFormes <= 10) { // Boucle tant que la connexion au serveur est active et qu'on a moins de 10 formes
						
						Thread.sleep(DELAI);
						
						// C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
						
	 					//La m�thode suivante alerte l'observateur 
						if(listenerChaineIn!=null){
						   firePropertyChange("ENVOIE-FORME", null, commSocket.getChaineForme()); 
						}
						nbFormes++;
					}
					stop(); // Arr�te le fil d'ex�cution si la connexion au serveur est arr�t�e.
					return null;
				}
			};
			if(listenerChaineIn!=null) {
				   threadComm.addPropertyChangeListener(listenerChaineIn); // La m�thode "propertyChange" de FentetrePrincipale sera donc appel�e lorsque le SwinkWorker invoquera la m�thode "firePropertyChanger" 
			}
			if(listenerIsActif!=null) {
				threadComm.addPropertyChangeListener(listenerIsActif); // La m�thode "propertyChange" de MenuFenetre sera donc appel�e lorsque le SwinkWorker invoquera la m�thode "firePropertyChanger" 
			}
			threadComm.execute(); // Lance le fil d'ex�cution parall�le.
			isActif = true;

		}

	}
	
	/**
	 * @return si le fil d'ex�cution parall�le est actif
	 */
	public boolean isActif(){
		return isActif;
	}
}
