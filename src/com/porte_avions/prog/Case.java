package com.porte_avions.prog;

import java.util.ArrayList;

public class Case {
	protected int type;
	protected ArrayList<Vehicule> listeVehicule;

	public Case(final int typeCase, final int nbrAvionCase) {
		type = typeCase;
		listeVehicule = new ArrayList<Vehicule>();
	}

	public int getType() {
		return type;
	}

	public void setType(final int type) {
		this.type = type;
	}

	public int getNbrAvion() {
		int nbrAvion = 0;
		for (final Vehicule v : listeVehicule) {
			if (v.getTypeOf() == 1) {
				nbrAvion += 1;
			}
		}
		return nbrAvion;
	}

	public PorteAvions getPorteAvions() {
		PorteAvions porteAvions = null;
		for (final Vehicule v : listeVehicule) {
			if (v.getTypeOf() == 0) {
				porteAvions = (PorteAvions) v;
			}
		}
		return porteAvions;
	}

	public void ajouterVehicule(final Vehicule v) {
		listeVehicule.add(v);
	}

	public void supprVehicule(final Vehicule v) {
		listeVehicule.remove(v);
	}

}