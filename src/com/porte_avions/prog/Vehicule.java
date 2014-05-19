package com.porte_avions.prog;

public abstract class Vehicule {
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

		if (position == 0) {
			temp += "en mer";
		} else if (position == 1) {
			temp += "en vol";
		} else if (position == 2) {
			temp += "sur le porte-avions";
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

}
