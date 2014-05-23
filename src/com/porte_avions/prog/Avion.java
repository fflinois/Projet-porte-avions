package com.porte_avions.prog;

public abstract class Avion extends Vehicule{
	
	public Avion(final String natioAvion, final String nomAvion,
			final int nivCarbuAvion, final int maxCarbuAvion,
			final int distDeplaceMaxiAvion,
			final int consoAvion, final int posXAvion, final int posYAvion,
			final int typeOfAvion) {
		super(natioAvion, nomAvion, nivCarbuAvion, maxCarbuAvion, consoAvion,
				distDeplaceMaxiAvion,
				posXAvion, posYAvion, typeOfAvion);
	}

	@Override
	public boolean isDeplacable(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		int nbrAvionCase = 5;
		if (super.isDeplacable(carteJeu, newPosX, newPosY)) {
			nbrAvionCase = carteJeu.getTableauCases()[newPosX][newPosY]
					.getNbrAvion();
		}
		return nbrAvionCase < 5;
	}

	public boolean zoneEnvolLibre(final Carte carteJeu) {
		return carteJeu.getTableauCases()[posX][posY].getNbrAvion() < 5;
	}

	public boolean decoler(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		boolean aDecole = false;
		final PorteAvions porteAvionsCase = carteJeu.getTableauCases()[posX][posY]
				.getPorteAvions();

		if (isDeplacable(carteJeu, newPosX, newPosY)
				&& zoneEnvolLibre(carteJeu)) {
			position = 1;
			carteJeu.miseAJour(newPosX, newPosY, this);
			brulerCarburant(newPosX, newPosY);
			posX = newPosX;
			posY = newPosY;
			aDecole = true;
			porteAvionsCase.supprAvion(this);
		}
		return aDecole;
	}

	public boolean atterrir(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		final int typeCase = carteJeu.getTableauCases()[newPosX][newPosY]
				.getType();
		
		boolean aAterri = false;
		PorteAvions porteAvionsCase = null;
		
		if ((typeCase == 3 || typeCase == 6 || typeCase == 7)){
			porteAvionsCase = carteJeu.getTableauCases()[newPosX][newPosY].getPorteAvions();
		}
		
		if (isDeplacable(carteJeu, newPosX, newPosY)
				&& placeDispoSurCase(carteJeu, newPosX, newPosY)
				&& (typeCase == 1 || ((typeCase == 3 || typeCase == 6 || typeCase == 7)
						&& porteAvionsCase.getNbrAvionsABord() < porteAvionsCase
								.getCapaciteMax() && porteAvionsCase
							.isUnAmi(this)))) {
			position = 2;
			carteJeu.miseAJour(newPosX, newPosY, this);
			brulerCarburant(newPosX, newPosY);
			posX = newPosX;
			posY = newPosY;
			aAterri = true;
			porteAvionsCase.addAvion(this);
		}

			return aAterri;
	}
	
	public boolean placeDispoSurCase(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		return carteJeu.getTableauCases()[newPosX][newPosY].getNbrAvion() < 5;
	}

}
