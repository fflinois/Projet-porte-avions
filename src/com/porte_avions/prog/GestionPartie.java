package com.porte_avions.prog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestionPartie {
	Partie partie;

	public GestionPartie() {
		partie = new Partie();
	}

	public void lancerJeu() {
		int choix;
		System.out
				.println("voulez final vous recuperer une final partie sauvegardée");
		choix = Terminal.readInt();
		
		if (choix == 1) {
			lireSauvegarde();
		}else {
			partie.jouer(false);
		}

		
		System.out.println("enregistrer ?");
		choix = Terminal.readInt();

		if (choix == 1) {
			enregistrerSauvegarde();
		}
	}

	public void enregistrerSauvegarde() {
		final File sauvegarde = new File("save.sav");
		FileOutputStream stream;
		ObjectOutputStream objectOutputStream;

		try {
			stream = new FileOutputStream("save.sav");
			objectOutputStream = new ObjectOutputStream(stream);
			objectOutputStream.writeObject(partie);
			objectOutputStream.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean lireSauvegarde() {
		final File sauvegarde = new File("save.sav");
		Partie partie;

		if (sauvegarde.exists()) {
			FileInputStream stream;
			ObjectInputStream objectInputStream;

			try {
				stream = new FileInputStream("save.sav");
				objectInputStream = new ObjectInputStream(stream);
				partie = (Partie) objectInputStream.readObject();
				partie.jouer(true);
				objectInputStream.close();
			} catch (final FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return true;
	}


}
