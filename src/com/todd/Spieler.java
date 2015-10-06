package com.todd;

import com.todd.ESpielfigur;

public class Spieler {

	private String name;
	private ESpielfigur farbe;

	public Spieler(final String pName, final ESpielfigur pFarbe) {
		super();
		this.name = pName;
		this.farbe = pFarbe;
	}

	public ESpielfigur getFarbe() {
		return this.farbe;
	}

	public void setFarbe(final ESpielfigur farbe) {
		this.farbe = farbe;
	}

	public String getName() {
		return this.name;
	}
}
