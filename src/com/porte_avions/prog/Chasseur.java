package com.porte_avions.prog;

public class Chasseur extends Avion {
	public Chasseur(final String natioChasseur, final String nomChasseur,
			final int nivCarbuChasseur, final int maxCarbuChasseur,
			final int consoChasseur, final int distDeplaceMaxiChasseur,
			final int posXChasseur,
			final int posYChasseur, final int typeOfChasseur) {
		super(natioChasseur, nomChasseur, nivCarbuChasseur, maxCarbuChasseur,
				consoChasseur, distDeplaceMaxiChasseur, posXChasseur,
				posYChasseur, typeOfChasseur);
	}

	@Override
	boolean atterrir() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean decoler() {
		// TODO Auto-generated method stub
		return false;
	}


}
