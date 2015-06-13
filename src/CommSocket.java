/******************************************************
Cours:  LOG121
Session: E2015
Groupe: 01
Projet: Laboratoire #2
�tudiant(e)s: Carole Fabeleu, Richard Kantchil et Ngoc-Phong Nguyen


Professeur : Francis Cardinal
Nom du fichier: CommSocket.java
Date cr��: 2015-05-13
Date dern. modif. 2015-06-12
*******************************************************
Cette classe est bas� sur la classe CommBase de Patrice Boucher de cette application et l'exemple sur Internet de la documentation d'Oracle sur la page suivante:

https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html

*******************************************************
Historique des modifications
*******************************************************
*@author Ngoc-Phong Nguyen
2015-05-13 Version initiale
2015-05-18 Ajout de la demande de nom d'h�te / num�ro de port
2015-05-25 Ajout de la variable isActif et optimisation de start()
2015-05-28 Ajout de la gestion d'exception
2015-06-12 Ajout des exceptions NullPointerException et IllegalArgumentException � la m�thode start(). Modification de l'ent�te.
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
	 * D�marre la communication avec le serveur.
	 * @throws Alerte des erreurs DNS si le nom d'h�te et/ou le num�ro de port sont invalides.
	 * @throws Alerte si le serveur ne r�pond pas � la requ�te de l'utilisateur.
	 */
	public void start() {
		
		// Demande � l'utilisateur du nom d'h�te et du num�ro de port du serveur.
		String userIn = JOptionPane.showInputDialog(null, "Veuillez entrez le nom d'h�te et le num�ro de port du serveur de formes.", "Connection au serveur de formes", JOptionPane.QUESTION_MESSAGE);
		
		try {
			//S�paration de la cha�ne de caract�res que l'utilisateur a �crit comme nom d'h�te et num�ro de port.
			String[] parameters = userIn.split(":");  
			hote = parameters[0];
			port = Integer.parseInt(parameters[1]);
			
			socket = new Socket(hote, port);
			
			// Cr�ation des canaux de communication pour envoyer et recevoir de l'information au serveur.
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			isActif = true;
		}
		catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Le nom d'h�te et le num�ro de port entr�s sont invalides.", "Erreur DNS", JOptionPane.ERROR_MESSAGE);
		}
		catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Le nom d'h�te entr� est invalide.", "Erreur DNS", JOptionPane.ERROR_MESSAGE);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Le num�ro de port entr� est invalide.", "Erreur DNS", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Le serveur " + hote + " ne r�pond pas sur le port " + port + ".", "Aucune r�ponse du serveur", JOptionPane.ERROR_MESSAGE);
		}
		catch (NullPointerException e) {
		}
		catch (IllegalArgumentException e) {
		}
	}
	
	/**
	 * Envoie une commande au serveur pour que celui-ci nous retourne une forme quelconque.
	 * @return Retourne la cha�ne de caract�res contenant les informations de la forme.
	 * @throws Alerte si le serveur se ferme d'une fa�on impr�vue.
	 */
	public String getChaineForme() {
		out.println("GET");
		try {
			chaineIn = in.readLine(); // Sert � �viter la ligne "commande> ".
			chaineIn = in.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Terminaison impr�vue du serveur " + hote + ".", "Terminaison impr�vue du serveur", JOptionPane.ERROR_MESSAGE);
			isActif = false;
		}
		return chaineIn;
	}

	/**
	 * Arr�te la communication avec le serveur.
	 */
	public void stop() {
		if(isActif) {
			out.println("END");
			isActif = false;
		}
	}

	/**
	 * Accesseur de l'�tat de la connexion au serveur.
	 * @return L'�tat de la connexion au serveur.
	 */
	public boolean isActif() {
		return isActif;
	}

}
