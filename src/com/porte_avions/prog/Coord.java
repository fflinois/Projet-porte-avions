package com.porte_avions.prog;

public class Coord {

	final static int largeurCase = 48;
	final static int largeurBord = 24;
	final static int margeAutourIcone = 62;
	final static int hauteurZoneTexteMenu = 150;
	final static int hauteurBarreHauteFenetre = 30;
	final static int largeurBarreCoteFenetre = 10;
	final static int hauteurIconeMenu = 45;
	final static int largeurIcone = 75;

	public static int xMenuMin(final int nombreCase) {
		return nombreCase * largeurCase + 2 * largeurBord + margeAutourIcone
				+ largeurBarreCoteFenetre;
	}

	public static int xMenuMax(final int nombreCase) {
		return nombreCase * largeurCase + 2 * largeurBord + margeAutourIcone
				+ largeurIcone + largeurBarreCoteFenetre;
	}

	public static int yMenuMin(final int nombreCase) {
		return hauteurZoneTexteMenu;
	}

	public static int yMenuMax(final int nombreCase, final int nombreIcone) {
		return hauteurBarreHauteFenetre + nombreCase * largeurCase + 2
				* largeurBord;
	}

	public static int xCarteMin(final int nombreCase) {
		return largeurBarreCoteFenetre + largeurBord;
	}

	public static int xCarteMax(final int nombreCase) {
		return nombreCase * largeurCase + largeurBord + largeurBarreCoteFenetre;
	}

	public static int yCarteMin(final int nombreCase) {
		return hauteurBarreHauteFenetre + largeurBord;
	}

	public static int yCarteMax(final int nombreCase) {
		return hauteurBarreHauteFenetre + nombreCase * largeurCase
				+ largeurBord;
	}
}
