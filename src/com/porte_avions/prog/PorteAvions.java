package com.porte_avions.prog;

import java.util.ArrayList;

public class PorteAvions extends Navire {

	protected int capaciteMax;
	protected int nbrAvionsABord = 0;
	protected ArrayList<Avion> listeAvions;

	public PorteAvions(final String natioPorteAvions,
			final String nomPorteAvions, final int nivCarbuPorteAvions,
			final int maxCarbuPorteAvions, final int consoPorteAvions,
			final int distDeplaceMaxiPorteAvions,
			final int posXPorteAvions, final int posYPorteAvions,
			final int capacitePorteAvions, final int typeOfPorteAvions) {
		super(natioPorteAvions, nomPorteAvions, nivCarbuPorteAvions,
				maxCarbuPorteAvions, consoPorteAvions,
				distDeplaceMaxiPorteAvions, posXPorteAvions,
				posYPorteAvions, typeOfPorteAvions);
		capaciteMax = capacitePorteAvions;
		listeAvions = new ArrayList<Avion>();
	}

	@Override
	public boolean seDeplacer(final Carte carteJeu, final int newPosX,
			final int newPosY) {
		final boolean deplace = false;
		super.seDeplacer(carteJeu, newPosX, newPosY);
		modifPositionAvionABord(newPosX, newPosY);
		return deplace;
	}

	@Override
	public String toString(){
		String temp = super.toString();
		temp += "\navec " + nbrAvionsABord + " avion(s) à bord";
		return temp;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public int getNbrAvionsABord() {
		return nbrAvionsABord;
	}

	public boolean modifPositionAvionABord(final int newPosX,final int newPosY){
		for (final Avion avion : listeAvions) {
			avion.setPosX(newPosX);
			avion.setPosY(newPosY);
		}
		return true;
	}

	public boolean addAvion(final Avion avion) {
		listeAvions.add(avion);
		nbrAvionsABord += 1;
		return true;
	}

	public boolean supprAvion(final Avion avion) {
		listeAvions.remove(avion);
		nbrAvionsABord -= 1;
		return true;
	}
}
