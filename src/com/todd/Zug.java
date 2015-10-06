package com.todd;

public class Zug {

	private final int ROT = 1;
	private final int GELB = 2;

	private int x;
	private int y;
	private Spieler spieler;

	public Zug(final int pX, final int pY, final Spieler pSpieler) {
		this.x = pX;
		this.y = pY;
		this.spieler = pSpieler;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void undoIt(final Spielfeld pFeld) {
		// this.perform(pFeld, null);
	}

	public Spielfeld doIt(final Spielfeld pFeld, final Spieler pSpieler) {
		return this.perform(pFeld, pSpieler);
	}

	public Spielfeld perform(final Spielfeld pFeld, final Spieler pSpieler) {
		Spielfeld neuesFeld = pFeld.copy();
		int x = this.getX();
		int y = this.getY();
		if (pSpieler == null) {
			neuesFeld.setFeld(x, y, ESpielfigur.UNDEFINIERT);
		} else {
			neuesFeld.setFeld(x, y, pSpieler.getFarbe());
		}
		return neuesFeld;
	}

	@Override
	public String toString() {
		String toString = "(" + this.x + "," + this.y + ") -> ";
		switch (this.spieler.getFarbe()) {
		case ROT:
			toString += "Rot";
			break;
		case GELB:
			toString += "Gelb";
			break;
		default:
			throw new IllegalStateException("falsche Belegung!");
		}
		return toString;
	}

}
