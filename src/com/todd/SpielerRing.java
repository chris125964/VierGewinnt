package com.todd;

import java.util.ArrayList;

import com.todd.Spieler;

public class SpielerRing {

	private ArrayList<Spieler> spielerListe;
	private int currentIndex;

	public SpielerRing() {
		this.currentIndex = -1;
		this.spielerListe = new ArrayList<Spieler>();
	}

	public void add(final Spieler pSpieler) {
		this.spielerListe.add(pSpieler);
	}

	public void setAktuellerSpieler(final Spieler pSpieler) {
		int index = 0;
		for (Spieler spieler : this.spielerListe) {
			if (pSpieler.equals(spieler)) {
				this.currentIndex = index;
				break;
			}
			++index;
		}
	}

	public int getSpielerIndex(final Spieler pSpieler) {
		int index = 0;
		for (Spieler spieler : this.spielerListe) {
			if (pSpieler.equals(spieler)) {
				break;
			}
			++index;
		}
		return index;
	}

	public Spieler getAktuellerSpieler() {
		if (this.currentIndex < 0) {
			return null;
		}
		return this.spielerListe.get(this.currentIndex);
	}

	public Spieler nextSpieler(final Spieler pSpieler) {
		int index = this.getSpielerIndex(pSpieler);
		if ((index + 1) == this.spielerListe.size()) {
			// Ende der Liste wurde erreicht
			index = 0;
		} else {
			++index;
		}
		return this.spielerListe.get(index);
	}

	public Spieler previousSpieler() {
		if ((this.currentIndex == 0)) {
			// Anfang der Liste wurde erreicht
			this.currentIndex = this.spielerListe.size() - 1;
		} else {
			--this.currentIndex;
		}
		return this.spielerListe.get(this.currentIndex);
	}

}
