package com.porte_avions.prog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.porte_avions.view.IGPA;

public class Partie {
	protected ArrayList<Vehicule> listeVehiculesA;
	protected ArrayList<Vehicule> listeVehiculesB;
	protected Carte carte;
	protected IGPA igpa;
	protected int[] tabMenu;
	protected int[] tabMenuInit;
	protected int positionXClic;
	protected int positionYClic;
	protected int posX;
	protected int posY;

	public Partie() {
		carte = new Carte(initCarte(10, 10));
		tabMenu = new int[5];
		tabMenuInit = new int[5];
		for (int i = 0; i < tabMenuInit.length; i++) {
			tabMenuInit[i] = 105;
		}
		listeVehiculesA = new ArrayList<Vehicule>();
		igpa = new IGPA(10, 10, this);
		initIGPA();
	}

	/*
	 * ###############################################################
	 * INITIALISATION DU JEU
	 * 
	 * 
	 * Initialisation de la carte de jeu
	 */

	public int[][] initCarte(final int largeur, final int hauteur) {
		final int[][] init = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				init[i][j] = 2;
			}
		}
		for (int i = 5; i < 10; i++) {
			init[i][9] = 1;
		}
		return init;
	}

	/*
	 * Initialisation de l'interface graphique declaration des images de
	 * l'interface
	 */

	public void initIGPA() {
		igpa.declarerImage(1, "sable.png");
		igpa.declarerImage(2, "eau.png");
		igpa.declarerImage(3, "porte-avion-eau.png");
		igpa.declarerImage(4, "avion-eau.png");
		igpa.declarerImage(5, "avions-eau.png");
		igpa.declarerImage(6, "porte-avion-avion-eau.png");
		igpa.declarerImage(7, "porte-avion-avions-eau.png");
		igpa.declarerImage(8, "avion-terre.png");
		igpa.declarerImage(9, "avions-terre.png");
		igpa.declarerImage(30, "porte-avion-eau-sel.png");
		igpa.declarerImage(40, "avion-eau-sel.png");
		igpa.declarerImage(50, "avions-eau-sel.png");
		igpa.declarerImage(60, "porte-avion-avion-eau-sel.png");
		igpa.declarerImage(70, "porte-avion-avions-eau-sel.png");
		igpa.declarerImage(80, "avion-terre-sel.png");
		igpa.declarerImage(90, "avions-terre-sel.png");
		igpa.declarerImage(101, "deplacerPA.png");
		igpa.declarerImage(102, "enVol.png");
		igpa.declarerImage(103, "decolle.png");
		igpa.declarerImage(104, "atterrir.png");
		igpa.declarerImage(105, "noir.png");
		igpa.declarerImage(106, "exit.png");
	}

	/*
	 * Initialisation du jeu
	 */
	public void initialisation() {
		int choix = 0;
		String tempText;
		String nom;
		boolean isPlacable;

		choix = saisieEntier(1, 10, "Nombre de porte-avions ?");

		for (int i = 0; i < choix; i++) {
			System.out.println("Nom du porte-avions" + (i + 1));
			nom = Terminal.readString();
			PorteAvions PA;
			do {
				System.out.println("Position du porte-avions " + nom);
				recuperationCoordSourisSurCarte(34, 548, 54, 534);
				PA = new PorteAvions("Francais", nom, 100, 100, 1, 1,
						positionXClic, positionYClic, 10, 0);
				isPlacable = PA.isPlacable(carte, positionXClic, positionYClic);
				if (!isPlacable) {
					System.out.println("Cet emplacement est impossible");
				}
			} while (!isPlacable);

			ajouterVehicule(PA);
			carte.init(positionXClic, positionYClic, PA);
		}

		for (final Vehicule v : listeVehiculesA) {
			if (v.getTypeOf() == 0) {
				final PorteAvions PA = (PorteAvions) v;
				tempText = "Voulez vous ajouter des avions dans le "
						+ v.getNom() + "\n1 -> OUI\n2 -> NON";
				choix = saisieEntier(1, 2, tempText);

				if (choix == 1) {
					System.out.println("Combien d'avion?");
					choix = Terminal.readInt();
					for (int i = 0; i < choix; i++) {
						System.out.println("Nom de l'avions" + (i + 1));
						nom = Terminal.readString();
						final Avion chasseur = new Chasseur("Francais", nom,
								100, 100, 8, 1, PA.getPosX(), PA.getPosY(), 1);
						chasseur.setPosition(2);
						PA.addAvion(chasseur);
					}
				}
			}

		}
		igpa.definirTerrain(carte.getTableauCasesInt());
		igpa.creerFenetre();
		igpa.reafficher();
	}

	/*
	 * FIN INITIALISATION DU JEU
	 * ###############################################################
	 */

	/*
	 * ###############################################################
	 * DEROULEMENT DU JEU
	 * 
	 * 
	 * Methode de jeu principale
	 */
	public void jouer() {

		boolean encore = true;
		int choix = 0;
		int compteurJeu = 0;
		int compteurTour = 1;
		int nbrIcone;
		final int nbrCase = carte.getLargeur();
		final String[] infoPourClic = { "Sélectionner une case",
				"en cliquant sur", "celle-ci" };

		igpa.definirTerrain(carte.getTableauCasesInt());
		igpa.definirMenu(tabMenuInit);
		igpa.creerFenetre();
		igpa.reafficher();
		initialisation();

		while (encore) {

			// System.out.println("Tour " + compteurTour + " :");
			// System.out.println(listeVehiculesA.get(compteurJeu));
			igpa.setTextAAfficher(listeVehiculesA.get(compteurJeu).toString()
					.split("\n"));
			carte.selectionCase(listeVehiculesA.get(compteurJeu));
			rafraichirIGPA();

			nbrIcone = affichageMenu(listeVehiculesA.get(compteurJeu));
			igpa.definirMenu(tabMenu);
			rafraichirIGPA();
			choix = recuperationCoordSourisSurMenu(Coord.xMenuMin(nbrCase),
					Coord.xMenuMax(nbrCase), Coord.yMenuMin(nbrCase),
					Coord.yMenuMax(nbrCase, nbrIcone));
			igpa.definirMenu(tabMenuInit);
			igpa.setTextAAfficher(infoPourClic);
			rafraichirIGPA();

			if (choix == 0) {

				encore = false;

			} else {
				recuperationCoordSourisSurCarte(Coord.xCarteMin(nbrCase),
						Coord.xCarteMax(nbrCase), Coord.yCarteMin(nbrCase),
						Coord.yCarteMax(nbrCase));
				carte.deselectionCase(listeVehiculesA.get(compteurJeu));
				if (choix == 1) {	
					
					deplacerVehicule(listeVehiculesA.get(compteurJeu));
				
				} else if (choix == 2) {
					if (isPorteAvions(listeVehiculesA.get(compteurJeu))) {
						faireDecolerAvion((PorteAvions) listeVehiculesA
								.get(compteurJeu));
					} else {
						faireAtterrirAvion((Avion) listeVehiculesA
								.get(compteurJeu));
					}
				}
			}
			rafraichirIGPA();
			
			if (compteurJeu >= listeVehiculesA.size() - 1) {
				compteurJeu = 0;
			} else {
				compteurJeu++;
			}

			compteurTour++;
		}
		igpa.fermer();
	}


	public void deplacerVehicule(final Vehicule vehicule) {
		if (isPorteAvions(vehicule)) {
			((PorteAvions) vehicule).seDeplacer(carte,
					positionXClic, positionYClic);
		} else {
			((Avion) vehicule).seDeplacer(carte,
					positionXClic, positionYClic);
		}
	}

	public void faireDecolerAvion(final PorteAvions porteAvions){
		if (porteAvions.listeAvions.get(porteAvions.listeAvions.size() - 1)
				.decoler(carte, positionXClic, positionYClic)) {
			ajouterVehicule(selectionChasseurDansPorteAvions(porteAvions));
		}		
	}
	
	public Chasseur selectionChasseurDansPorteAvions(
			final PorteAvions porteAvions) {
		final Chasseur chasseur = (Chasseur) porteAvions.listeAvions
				.get(porteAvions.listeAvions.size() - 1);
		return chasseur;
	}

	public void faireAtterrirAvion(final Avion avion){
		if (avion.atterrir(carte, positionXClic,
				positionYClic)) {
			supprVehicule(avion);
		}
	}

	public void rafraichirIGPA() {
		igpa.definirTerrain(carte.getTableauCasesInt());
		igpa.reafficher();
	}

	public boolean isPorteAvions(final Vehicule vehicule) {
		return vehicule.getTypeOf() == 0;
	}

	public boolean avionABord(final PorteAvions porteAvions) {
		return porteAvions.getNbrAvionsABord() > 0;
	}

	public boolean ajouterVehicule(final Vehicule vehicule) {
		boolean vehiculeAjoute = false;
		if (vehicule != null) {
			listeVehiculesA.add(vehicule);
			vehiculeAjoute = true;
		}
		return vehiculeAjoute;
	}

	public boolean supprVehicule(final Vehicule v) {
		final boolean vehiculeSupprime = true;
		listeVehiculesA.remove(v);
		return vehiculeSupprime;
	}

	public void saisieNomVehicule(final String[] tableauNom, final String text) {
		for (int i = 0; i < tableauNom.length; i++) {
			System.out.println(text + (i + 1));
			tableauNom[i] = Terminal.readString();
		}
	}

	public int saisieEntier(final int borneInf, final int borneSup,
			final String text) {
		int choix = -1;
		boolean erreur = true;

		while (erreur) {
			try {
				System.out.println(text);
				choix = Terminal.readInt();
				if (choix < borneInf || choix > borneSup) {
					System.out.println("Vous devez saisir un entier entre\n"
							+ borneInf + " et " + borneSup);
				} else {
					erreur = false;
				}
			} catch (final TerminalException e) {
				System.out.println("Vous devez saisir un entier");
			}
		}
		return choix;
	}


	public int affichageMenu(final Vehicule vehicule) {
		int nbrIcone = 0;
		final int[] tabMenuPorteAvions = { 106, 101, 105, 105, 105 };
		final int[] tabMenuPorteAvionsAvecAvions = { 106, 101, 103, 105, 105 };
		final int[] tabMenuAvion = { 106, 102, 104, 105, 105 };

		if (vehicule.getTypeOf() == 0) {
			if (avionABord((PorteAvions) vehicule)) {
				tabMenu = tabMenuPorteAvionsAvecAvions;
				nbrIcone = 3;
			} else {
				tabMenu = tabMenuPorteAvions;
				nbrIcone = 2;
			}
		} else if (vehicule.getTypeOf() == 1) {
			tabMenu = tabMenuAvion;
			nbrIcone = 2;
		}
		return nbrIcone;
	}

	private int menu(final String text, final int[] tabChoix) {
		int choix = -1;
		boolean bonChoix = false;
		while (!bonChoix) {
			System.out.println(text);
			choix = Terminal.readInt();
			for (final int i : tabChoix) {
				if (choix == i) {
					bonChoix = true;
				}
			}
		}
		return choix;
	}
	/**
	 * 
	 * @param xMin : coordonnée mini en X de la zone cliquable
	 * @param xMax : coordonnée maxi en X de la zone cliquable
	 * @param yMin : coordonnée mini en Y de la zone cliquable
	 * @param yMax : coordonnée mini en Y de la zone cliquable
	 */
	public void gestionClicSouris(final int xMin, final int xMax,
			final int yMin, final int yMax) {

		igpa.setVisible(true);
		boolean horsZone = true;
		while (horsZone) {

			final MouseAdapter MA = new MouseAdapter() {
				@Override
				public void mouseClicked(final MouseEvent e) {

					positionXClic = e.getX();
					positionYClic = e.getY();
					synchronized (igpa) {
						igpa.notify();
					}
				}
			};

			igpa.addMouseListener(MA);

			synchronized (igpa) {
				try {
					igpa.wait();
				} catch (final InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			igpa.removeMouseListener(MA);

			if (isHorsZone(xMin, yMin, xMax, yMax)) {
				horsZone = false;
			}
		}
	}

	public void recuperationCoordSourisSurCarte(final int xMin, final int xMax,
			final int yMin, final int yMax) {
		gestionClicSouris(xMin, yMin, xMax, yMax);
		conversionPositionClicSurCarte(positionXClic, positionYClic);
	}

	public int recuperationCoordSourisSurMenu(final int xMin, final int xMax,
			final int yMin, final int yMax) {
		gestionClicSouris(xMin, yMin, xMax, yMax);
		return conversionPositionClicSurMenu(positionYClic);
	}

	public void conversionPositionClicSurCarte(final int positionXClic,
			final int positionYClic) {
		final int tempX = (positionXClic - 34) / 48;
		this.positionXClic = tempX;
		final int tempY = (positionYClic - 54) / 48;
		this.positionYClic = tempY;
	}

	public int conversionPositionClicSurMenu(final int positionYClic) {
		int tempY;
		tempY = (positionYClic - 150 - 20) / 45;
		this.positionYClic = tempY;
		return tempY;
	}


	public boolean isHorsZone(final int xMin, final int xMax, final int yMin,
			final int yMax) {
		return positionXClic >= xMin && positionXClic <= xMax
				&& positionYClic >= yMin && positionYClic <= yMax;
	}
}
