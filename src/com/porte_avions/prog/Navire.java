package com.porte_avions.prog;

public abstract class Navire extends Vehicule {

	public Navire(final String natioNavire, final String nomNavire,
			final int nivCarbuNavire, final int maxCarbuNavire,
			final int consoNavire, final int distDeplaceMaxiNavire,
			final int posXNavire, final int posYNavire,
			final int typeOfNavire) {
		super(natioNavire, nomNavire, nivCarbuNavire, maxCarbuNavire,
				consoNavire, distDeplaceMaxiNavire, posXNavire, posYNavire,
				typeOfNavire);
	}

	@Override
	public boolean isDeplacable(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		int typeCase;
		boolean isDeplacable = false;
		if (super.isDeplacable(carteJeu, newPosX, newPosY)) {
			typeCase = carteJeu.getTableauCases()[newPosX][newPosY].getType();
			if (typeCase != 1
					&& (typeCase != 3 || (typeCase == 3 && newPosX == posX && newPosY == posY))) {
				isDeplacable = true;
			}
		}
		return isDeplacable;
	}
}
