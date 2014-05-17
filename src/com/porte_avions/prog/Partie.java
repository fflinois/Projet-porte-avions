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
	protected int positionXClic;
	protected int positionYClic;

	public Partie() {
		carte = new Carte(initCarte(10, 10));
		listeVehiculesA = new ArrayList<Vehicule>();
		igpa = new IGPA(10, 10, this);
		initIGPA();
	}

	/*
	 * Methode de jeu principale
	 */
	public void jouer() {

		boolean encore = true;
		final int newX;
		final int newY;
		int choix = 0;
		int compteurJeu = 0;
		int compteurTour = 1;

		igpa.definirTerrain(carte.getTableauCasesInt());
		igpa.creerFenetre();
		igpa.reafficher();
		initialisation();

		while (encore) {

			System.out.println("Tour " + compteurTour + " :");
			System.out.println(listeVehiculesA.get(compteurJeu));
			if (listeVehiculesA.get(compteurJeu).getTypeOf() == 0) {
				final PorteAvions PA = (PorteAvions) listeVehiculesA
						.get(compteurJeu);
				choix = menuPorteAvions(PA);
				if (choix == 0) {
					encore = false;
				} else if (choix == 1) {
					choixPositionAuClic();
					if (PA.seDeplacer(carte, positionXClic, positionYClic)) {
						igpa.definirTerrain(carte.getTableauCasesInt());
						igpa.reafficher();
					}
				} else if (choix == 2) {
					choixPositionAuClic();
					final Chasseur CHA = (Chasseur) PA.listeAvions
							.get(PA.listeAvions.size() - 1);
					if (PA.listeAvions.get(PA.listeAvions.size() - 1).decoler(
							carte, positionXClic, positionYClic)) {
						ajouterVehicule(CHA);
						igpa.definirTerrain(carte.getTableauCasesInt());
						igpa.reafficher();
					}
				}
			} else if (listeVehiculesA.get(compteurJeu).getTypeOf() == 1) {
				final Chasseur CHA = (Chasseur) listeVehiculesA
						.get(compteurJeu);
				choix = menuChasseur(CHA);
				if (choix == 0) {
					encore = false;
				} else if (choix == 1) {
					choixPositionAuClic();
					if (CHA.seDeplacer(carte, positionXClic, positionYClic)) {
						igpa.definirTerrain(carte.getTableauCasesInt());
						igpa.reafficher();
					}
				} else if (choix == 2) {
					choixPositionAuClic();
					if (CHA.atterrir(carte, positionXClic, positionYClic)) {
						igpa.definirTerrain(carte.getTableauCasesInt());
						igpa.reafficher();
					}
				}
			}

			if (compteurJeu == listeVehiculesA.size() - 1) {
				compteurJeu = 0;
			} else {
				compteurJeu++;
			}

			compteurTour++;

		}

		igpa.fermer();
	}



	/*
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
	 * Initialisation de l'interface graphique
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
	}

	/*
	 * Initialisation du jeu
	 */
	public void initialisation() {
		int choix;
		String tempText;
		String nom;
		boolean isPlacable;

		System.out.println("nombre de porte-avions ?");
		choix = Terminal.readInt();

		for (int i = 0; i < choix; i++) {
			System.out.println("Nom du porte-avions" + (i + 1));
			nom = Terminal.readString();
			PorteAvions PA;
			do {
				System.out.println("Position du porte-avions " + nom);
				choixPositionAuClic();
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
						+ v.getNom()
					+ "\n1 -> OUI\n2 -> NON";
			final int[] tabChoix = { 1, 2 };
			choix = menu(tempText, tabChoix);
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

	public boolean ajouterVehicule(final Vehicule vehicule) {
		boolean vehiculeAjoute = false;

		if (vehicule != null) {
			listeVehiculesA.add(vehicule);
			vehiculeAjoute = true;
		}
		return vehiculeAjoute;
	}


	public int menuPorteAvions(final PorteAvions PA) {
		int choix = 0;
		boolean bonChoix = false;

		while (!bonChoix) {
			System.out.println("Que voulez vous faire avec le porte-avions " + PA.getNom() +" ?");
			System.out.println("1 -> Déplacer");
			if (PA.getNbrAvionsABord() != 0) {
				System.out.println("2 -> Faire décoler un avion");
			}
			System.out.println("0 -> Quitter le jeu");
			choix = Terminal.readInt();
			if (choix != 0 && choix != 1 && choix != 2) {
				System.out.println("Erreur de saisie !!!");
			} else {
				bonChoix = true;
			}
		}
		return choix;
	}
	
	public int menuChasseur(final Chasseur CHA) {
		int choix = 0;
		boolean bonChoix = false;

		while (!bonChoix) {
			System.out.println("Que voulez vous faire avec le chasseur "+ CHA.getNom() +" ?");
			System.out.println("1 -> Déplacer" );
			System.out.println("2 -> Atterrir");
			System.out.println("0 -> Quitter le jeu");
			choix = Terminal.readInt();
			if (choix != 0 && choix != 1 && choix != 2) {
				System.out.println("Erreur de saisie !!!");
			} else {
				bonChoix = true;
			}
		}
		return choix;
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

	public void choixPositionAuClic() {

		System.out.println("Cliquez sur une case");
		igpa.setVisible(true);
		final MouseAdapter MA = new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				setPositionXClic(e.getX());
				setPositionYClic(e.getY());

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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		igpa.removeMouseListener(MA);
	}


	public int getPositionXClic() {
		return positionXClic;
	}

	public void setPositionXClic(final int positionXClic) {
		final int tempX = (positionXClic - 24) / 48;
		this.positionXClic = tempX;
	}

	public int getPositionYClic() {
		return positionYClic;
	}

	public void setPositionYClic(final int positionYClic) {
		final int tempY = (positionYClic - 24) / 48;
		this.positionYClic = tempY;
	}

}
