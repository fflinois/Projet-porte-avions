package com.porte_avions.prog;

import java.io.Serializable;

public abstract class Vehicule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4005336996134735948L;
	protected String nationalite;
	protected String nom;
	protected int niveauCarburant;
	protected int maximumCarburant;
	protected int distDeplaceMaxi;
	protected int conso;
	protected int posX;
	protected int posY;
	protected int typeOf;
	protected int etat; // defini si le vehicule est detruit, intact ...
	protected int position; // defini si le vehicule est en 0 -> mer, en 1
							// ->vol, 2 -> sur PA...

	public Vehicule(final String natioVehicule, final String nomVehicule,
			final int nivCarbuVehicule, final int maxCarbuVehicule,
			final int consoVehicule, final int distDeplaceMaxiVehicule,
			final int posXVehicule,
			final int posYVehicule, final int typeOfVehicule) {
		nationalite = natioVehicule;
		nom = nomVehicule;
		niveauCarburant = nivCarbuVehicule;
		maximumCarburant = maxCarbuVehicule;
		distDeplaceMaxi = distDeplaceMaxiVehicule;
		conso = consoVehicule;
		posX = posXVehicule;
		posY = posYVehicule;
		typeOf = typeOfVehicule;
		etat = 5;
	}

	public boolean isPlacable(final Carte carteJeu, final int PosX,
			final int PosY) {
		return carteJeu.getTableauCasesInt()[posX][posY] != 1
				&& carteJeu.getTableauCasesInt()[posX][posY] != 3;
	}

	public boolean isDeplacable(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		return isDansRayonAction(newPosX, newPosY)
				&& aAssezDeCarburant(newPosX, newPosY)
				&& isDansCarte(carteJeu, newPosX, newPosY);
	}

	private boolean isDansCarte(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		return newPosX <= carteJeu.getLargeur() - 1 && newPosX >= 0
				&& newPosY <= carteJeu.getHauteur() - 1 && newPosY >= 0;
	}

	private boolean isDansRayonAction(final int newPosX, final int newPosY) {
		return newPosX <= posX + distDeplaceMaxi
				&& newPosX >= posX - distDeplaceMaxi
				&& newPosY <= posY + distDeplaceMaxi
				&& newPosY >= posY - distDeplaceMaxi;
	}

	public boolean seDeplacer(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		boolean deplace = false;
		if (isDeplacable(carteJeu, newPosX, newPosY)) {
			carteJeu.miseAJour(newPosX, newPosY, this);
			brulerCarburant(newPosX, newPosY);
			posX = newPosX;
			posY = newPosY;
			deplace = true;
		} else {
			System.out.println("Déplacement impossible");
		}
		return deplace;
	}

	public boolean attaquer(final Carte carteJeu, final int posX, final int posY) {
		boolean attaque = false;
		final int nbrVehiculeSurCase = carteJeu.getTableauCases()[posX][posY]
				.getNbrVehicule();
		final Vehicule[] listeVehicules = carteJeu.getTableauCases()[posX][posY]
				.getVehicule();
		for (int i = 0; i < nbrVehiculeSurCase; i++) {
			if (!isUnAmi(listeVehicules[i])) {
				System.out.println("ATTAQUE");
				attaque = true;
				listeVehicules[i].PerdrePointDeVie();
			}
		}
		return attaque;
	}

	public boolean aAssezDeCarburant(final int newPosX, final int newPosY) {
		boolean aAssez = false;
		if (niveauCarburant
				- Math.max(Math.abs(newPosX - posX), Math.abs(newPosY - posY))
				* conso > 0) {
			aAssez = true;
		}
		return aAssez;
	}

	public void brulerCarburant(final int newPosX, final int newPosY) {
		if (typeOf == 1 && newPosX == posX && newPosY == posY) {
			niveauCarburant -= conso;
		} else {
			niveauCarburant -= Math.max(Math.abs(newPosX - posX),
					Math.abs(newPosY - posY))
					* conso;
		}
	}

	public void PerdrePointDeVie() {
		etat--;
	}

	@Override
	public String toString() {
		String temp = "";
		if (typeOf == 0) {
			temp = "Le porte-avions ";
		} else if (typeOf == 1) {
			temp = "L'avion ";
		} else {
			temp = "Le véhicule ";
		}

		temp += "\n" + nationalite + ", " + nom + "\nà " + niveauCarburant
				+ " unité(s) de carburant\nil est ";

		temp += positionVehicule();
		temp += etatVehicule();

		return temp;
	}

	public String positionVehicule() {
		String temp = "";
		if (position == 0) {
			temp = "en mer";
		} else if (position == 1) {
			temp = "en vol";
		} else if (position == 2) {
			temp = "sur le porte-avions";
		}
		return temp + "\n";
	}

	public String etatVehicule() {
		String temp = "";
		switch (etat) {
		case 0:
			temp = "Détruit";
			break;
		case 1:
			temp = "Presque détruit";
			break;
		case 2:
			temp = "Très endommagé";
			break;
		case 3:
			temp = "Endommagé";
			break;
		case 4:
			temp = "Touché";
			break;
		case 5:
			temp = "Parfait état";
			break;
		}
		return temp;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(final int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(final int posY) {
		this.posY = posY;
	}

	public int getTypeOf() {
		return typeOf;
	}

	public String getNationalite() {
		return nationalite;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(final int position) {
		this.position = position;
	}

	public String getNom() {
		return nom;
	}

	public int getEtat() {
		return etat;
	}

	public boolean isUnAmi(final Vehicule vehicule) {
		return vehicule.getNationalite().equals(nationalite);
	}

}
