/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: CommSocket.java
Date créé: 2015-05-13
*******************************************************
Cette classe est basé sur la classe CommBase de Patrice Boucher de cette application et l'exemple sur Internet de la documentation d'Oracle sur la page suivante:

https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html

*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Ajout de la demande de nom d'hôte / numéro de port
2015-05-25 Ajout de la variable isActif et optimisation de start()
2015-05-28 Ajout de la gestion d'exception
*******************************************************/
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

/**
 * Cette classe s'occupe de la communication avec le serveur a travers un Socket TCP/IP
 * @author Ngoc-Phong Nguyen
 * @date 2015/05/13
 */
public class CommSocket {
	
	private String hote;
	private int port;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String chaineIn;
	private boolean isActif = false;
	
	/**
	 * Constructeur
	 */
	public CommSocket() {
	}

	/**
	 * Démarre la communication avec le serveur.
	 * @throws Alerte des erreurs DNS si le nom d'hôte et/ou le numéro de port sont invalides.
	 * @throws Alerte si le serveur ne répond pas à la requête de l'utilisateur.
	 */
	public void start() {
		
		// Demande à l'utilisateur du nom d'hôte et du numéro de port du serveur.
		String userIn = JOptionPane.showInputDialog(null, "Veuillez entrez le nom d'hôte et le numéro de port du serveur de formes.", "Connection au serveur de formes", JOptionPane.QUESTION_MESSAGE);
		
		try {
			//Séparation de la chaîne de caractères que l'utilisateur a écrit comme nom d'hôte et numéro de port.
			String[] parameters = userIn.split(":");  
			hote = parameters[0];
			port = Integer.parseInt(parameters[1]);
			
			socket = new Socket(hote, port);
			
			// Création des canaux de communication pour envoyer et recevoir de l'information au serveur.
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			isActif = true;
		}
		catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Le nom d'hôte et le numéro de port entrés sont invalides.", "Erreur DNS", JOptionPane.ERROR_MESSAGE);
		}
		catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Le nom d'hôte entré est invalide.", "Erreur DNS", JOptionPane.ERROR_MESSAGE);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Le numéro de port entré est invalide.", "Erreur DNS", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Le serveur " + hote + " ne répond pas sur le port " + port + ".", "Aucune réponse du serveur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Envoie une commande au serveur pour que celui-ci nous retourne une forme quelconque.
	 * @return Retourne la chaîne de caractères contenant les informations de la forme.
	 * @throws Alerte si le serveur se ferme d'une façon imprévue.
	 */
	public String getChaineForme() {
		out.println("GET");
		try {
			chaineIn = in.readLine(); // Sert à éviter la ligne "commande> ".
			chaineIn = in.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Terminaison imprévue du serveur " + hote + ".", "Terminaison imprévue du serveur", JOptionPane.ERROR_MESSAGE);
			isActif = false;
		}
		return chaineIn;
	}

	/**
	 * Arrête la communication avec le serveur.
	 */
	public void stop() {
		if(isActif) {
			out.println("END");
			isActif = false;
		}
	}

	/**
	 * Accesseur de l'état de la connexion au serveur.
	 * @return L'état de la connexion au serveur.
	 */
	public boolean isActif() {
		return isActif;
	}

}
