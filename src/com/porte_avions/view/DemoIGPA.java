package com.porte_avions.view;

import com.porte_avions.prog.Terminal;

public class DemoIGPA{
    public static void main(final String[] a){
	final IGPA igpa = new IGPA(4,2); // cree un objet IGPA avec 4 colonnes et 2 lignes
	// declaration des images utilises
	igpa.declarerImage(5,"eau.png");
	igpa.declarerImage(10,"porte-avion-eau.png");
	// declaration de la carte initiale
	final int[][] init={{5,5},{5,10},{5,5},{5,5}};
	igpa.definirTerrain(init);
	igpa.creerFenetre();
		Terminal.writeStringln("Tapez enter pour continuer");
		Terminal.readString();
	// deplacement du porte-avion: 2 changements de case + reaffichage
	igpa.modifierCase(1,1,5);
	igpa.modifierCase(2,0,10);
	igpa.reafficher();
		Terminal.writeStringln("Tapez enter pour continuer");
		Terminal.readString();
	// il pleut des porte-avions
	igpa.modifierCase(0,0,10);
	igpa.modifierCase(1,1,10);
	igpa.modifierCase(3,1,10);
	// mais on n'a pas encore reaffiche
		Terminal.writeStringln("Tapez enter pour continuer");
		Terminal.readString();
	// et maintenant, on affiche.
	igpa.reafficher();
		Terminal.writeStringln("Tapez enter pour continuer");
		Terminal.readString();
	// on ferme la fenêtre
	igpa.fermer();
    }
}
