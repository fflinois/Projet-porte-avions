package com.porte_avions.prog;

public class Carte {
	protected int largeur;
	protected int hauteur;
	protected Case[][] tableauCases;

	public Carte(final int[][] tableauCasesCarte) {
		largeur = tableauCasesCarte.length;
		hauteur = tableauCasesCarte[0].length;
		tableauCases = new Case[largeur][hauteur];
		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < hauteur; j++) {
				tableauCases[i][j] = new Case(tableauCasesCarte[i][j], 0);
			}
		}
	}

	public void init(final int PosX, final int PosY, final Vehicule v) {
		tableauCases[PosX][PosY].setType(3);
		tableauCases[PosX][PosY].ajouterVehicule(v);
	}
	
	public void selectionCase(final Vehicule v) {
		final int typeOfCase = tableauCases[v.getPosX()][v.getPosY()].getType();
		int typeOfNewCase = 0;

		switch (typeOfCase) {
		case 3:
			typeOfNewCase = 30;
			break;
		case 4:
			typeOfNewCase = 40;
			break;
		case 5:
			typeOfNewCase = 50;
			break;
		case 6:
			typeOfNewCase = 60;
			break;
		case 7:
			typeOfNewCase = 70;
			break;
		case 8:
			typeOfNewCase = 80;
			break;
		case 9:
			typeOfNewCase = 90;
			break;
		default:
			break;
		}
		tableauCases[v.getPosX()][v.getPosY()].setType(typeOfNewCase);
	}


	public void deselectionCase(final Vehicule v) {
		final int typeOfCase = tableauCases[v.getPosX()][v.getPosY()].getType();
		int typeOfNewCase = 0;

		switch (typeOfCase) {
		case 30:
			typeOfNewCase = 3;
			break;
		case 40:
			typeOfNewCase = 4;
			break;
		case 50:
			typeOfNewCase = 5;
			break;
		case 60:
			typeOfNewCase = 6;
			break;
		case 70:
			typeOfNewCase = 7;
			break;
		case 80:
			typeOfNewCase = 8;
			break;
		case 90:
			typeOfNewCase = 9;
			break;
		default:
			break;
		}
		tableauCases[v.getPosX()][v.getPosY()].setType(typeOfNewCase);
	}


	public void miseAJour(final int newPosX, final int newPosY, final Vehicule v) {

		int typeOfNewCase = tableauCases[newPosX][newPosY].getType();
		int typeOfLastCase = tableauCases[v.getPosX()][v.getPosY()].getType();

		if (newPosX != v.getPosX() || newPosY != v.getPosY()) {

			switch (v.getTypeOf()) {
			case 0: // porte avions
				switch (tableauCases[newPosX][newPosY].getType()) {
				case 2:
					typeOfNewCase = 3;
					break;
				case 3:
					typeOfNewCase = 3;
					break;
				case 4:
					typeOfNewCase = 6;
					break;
				case 5:
					typeOfNewCase = 7;
					break;
				default:
					break;
				}

				switch (tableauCases[v.getPosX()][v.getPosY()].getType()) {
				case 3:
					typeOfLastCase = 2;
					break;
				case 6:
					typeOfLastCase = 4;
					break;
				case 7:
					typeOfLastCase = 5;
					break;
				default:
					break;
				}

				break;

			case 1: // avions
				switch (tableauCases[newPosX][newPosY].getType()) {
				case 1:
					typeOfNewCase = 8;
					break;
				case 2:
					typeOfNewCase = 4;
					break;
				case 3:
					typeOfNewCase = 6;
					break;
				case 4:
					typeOfNewCase = 5;
					break;
				case 6:
					typeOfNewCase = 7;
					break;
				case 8:
					typeOfNewCase = 9;
					break;
				default:
					break;
				}

				switch (tableauCases[v.getPosX()][v.getPosY()].getType()) {
				case 8:
					typeOfLastCase = 1;
					break;
				case 4:
					typeOfLastCase = 2;
					break;
				case 6:
					typeOfLastCase = 3;
					break;
				case 5:
					typeOfLastCase = 4;
					break;
				case 7:
					typeOfLastCase = 6;
					break;
				case 9:
					typeOfLastCase = 8;
					break;
				default:
					break;
				}

				break;
			default:
				break;
			}

			if (v.getPosition() != 2) {
				tableauCases[newPosX][newPosY].setType(typeOfNewCase);
				tableauCases[newPosX][newPosY].ajouterVehicule(v);

			}
			tableauCases[v.getPosX()][v.getPosY()].setType(typeOfLastCase);
			tableauCases[v.getPosX()][v.getPosY()].supprVehicule(v);

		} else {
			if (v.getPosition() == 2) {
				switch (tableauCases[newPosX][newPosY].getType()) {
				case 6:
					typeOfNewCase = 3;
					break;
				case 7:
					typeOfNewCase = 6;
					break;
				case 8:
					typeOfNewCase = 1;
					break;
				case 9:
					typeOfNewCase = 8;
					break;
				default:
					break;
				}
			}
		}
	}

	public void suppression(final int newPosX, final int newPosY,
			final Vehicule v) {
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public Case[][] getTableauCases() {
		return tableauCases;
	}

	public int[][] getTableauCasesInt() {
		final int[][] tableauTemp = new int[largeur][hauteur];
		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < hauteur; j++) {
				tableauTemp[i][j] = tableauCases[i][j].getType();
			}
		}
		return tableauTemp;
	}

}
