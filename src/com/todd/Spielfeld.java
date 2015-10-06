package com.todd;

import java.util.ArrayList;
import java.util.List;

import com.todd.Zug;

/**
 * Dies ist das Spielfeld.
 *
 * @author christian
 *
 */
public class Spielfeld {

	private final int SPIELFELD_LAENGE_WAAGRECHT = 7;
	private final int SPIELFELD_LAENGE_SENKRECHT = 6;
	private final ESpielfigur[][] feld;
	private SpielerRing spielerRing;

	public Spielfeld() {
		// initialisiere das Spielfeld
		// CHECKSTYLE:OFF
		this.feld = new ESpielfigur[][] { // int[x][y]
				{ ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT,
						ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT }, // int[0][0-5]
				{ ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT,
						ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT }, // int[1][0-5]
				{ ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT,
						ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT }, // int[2][0-5]
				{ ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT,
						ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT }, // int[3][0-5]
				{ ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT,
						ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT }, // int[4][0-5]
				{ ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT,
						ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT }, // int[5][0-5]
				{ ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT,
						ESpielfigur.UNDEFINIERT, ESpielfigur.UNDEFINIERT } // int[6][0-5]
		};
		this.spielerRing = new SpielerRing();
	}

	public Spielfeld copy() {
		Spielfeld copyFeld = new Spielfeld();
		for (int i = 0; i < this.SPIELFELD_LAENGE_WAAGRECHT; i++) {
			for (int j = 0; j < this.SPIELFELD_LAENGE_SENKRECHT; j++) {
				ESpielfigur figur = this.getFeld(i, j);
				copyFeld.setFeld(i, j, figur);
			}
		}
		return copyFeld;
	}

	/**
	 * liefert den Inhalt eines gesuchten Felds.
	 *
	 * @param pX
	 *            x-Koordinate des Felds
	 * @param pY
	 *            y-Koordinate des Felds
	 * @return Inhalt des Felds
	 */
	public ESpielfigur getFeld(final int pX, final int pY) {
		ESpielfigur feldInhalt = ESpielfigur.UNDEFINIERT;
		if ((pX >= 0) && (pX < this.SPIELFELD_LAENGE_WAAGRECHT)) {
			if ((pY >= 0) && (pY < this.SPIELFELD_LAENGE_SENKRECHT)) {
				feldInhalt = this.feld[pX][pY];
			}
		}
		return feldInhalt;
	}

	/**
	 * belegt ein bestimmtes Feld mit einem Wert.
	 *
	 * @param pX
	 *            x-Koordinate
	 * @param pY
	 *            y-Koordinate
	 * @param spieler
	 *            Wert für das zu belegende Feld
	 */
	public void setFeld(final int pX, final int pY, final ESpielfigur farbe) {
		if ((pX >= 0) && (pX < this.SPIELFELD_LAENGE_WAAGRECHT)) {
			if ((pY >= 0) && (pY < this.SPIELFELD_LAENGE_SENKRECHT)) {
				this.feld[pX][pY] = farbe;
			}
		}
	}

	public void add(final Spieler pSpieler) {
		this.spielerRing.add(pSpieler);
	}

	public void setAktuellerSpieler(final Spieler pSpieler) {
		this.spielerRing.setAktuellerSpieler(pSpieler);
	}

	public Spieler nextSpieler(final Spieler pSpieler) {
		return this.spielerRing.nextSpieler(pSpieler);
	}

	public Spieler getAktuellerSpieler() {
		return this.spielerRing.getAktuellerSpieler();
	}

	/**
	 * sucht alle momentan möglichen Züge.
	 *
	 * @return Liste aller möglichen Züge
	 */
	public List<Zug> getAlleZuege(final Spieler pSpieler) {
		List<Zug> zuege = new ArrayList<Zug>();
		for (int i = 0; i < this.SPIELFELD_LAENGE_WAAGRECHT; i++) {
			for (int j = 0; j < this.SPIELFELD_LAENGE_SENKRECHT; j++) {
				ESpielfigur currentField = this.getFeld(i, j);
				if (currentField == ESpielfigur.UNDEFINIERT) {
					Zug zug = new Zug(i, j, pSpieler);
					zuege.add(zug);
					break;
				}
			}
		}
		return zuege;
	}

	public int gewonnen(final Spieler pSpieler, final Spieler pBewerteterSpieler) {
		int gewonnen = 0;
		for (int i = 0; i < this.SPIELFELD_LAENGE_SENKRECHT; i++) {
			for (int j = 0; j < this.SPIELFELD_LAENGE_WAAGRECHT; j++) {
				ESpielfigur spieler = this.getFeld(i, j);
				if (spieler == pSpieler.getFarbe()) {
					for (int dx = -1; dx <= 1; dx++) {
						for (int dy = -1; dy <= 1; dy++) {
							if ((dx != 0) || (dy != 0)) {
								gewonnen = this.gewonnenProRichtung(pSpieler, pBewerteterSpieler, i, j, dx, dy);
								if ((gewonnen == 100) || (gewonnen == -100)) {
									break;
								}
							}
						}
						if ((gewonnen == 100) || (gewonnen == -100)) {
							break;
						}
					}
				}
				if ((gewonnen == 100) || (gewonnen == -100)) {
					break;
				}
			}
			if ((gewonnen == 100) || (gewonnen == -100)) {
				break;
			}
		}
		return gewonnen;
	}

	private int gewonnenProRichtung(final Spieler pSpieler, final Spieler pBewerteterSpieler, final int x, final int y,
			final int dx, final int dy) {
		int gewonnen = 0;
		ESpielfigur farbe = pSpieler.getFarbe();
		if (this.getFeld(x + dx, y + dy) == farbe) {
			if (this.getFeld(x + (2 * dx), y + (2 * dy)) == farbe) {
				if (this.getFeld(x + (3 * dx), y + (3 * dy)) == farbe) {
					if (pSpieler.equals(pBewerteterSpieler)) {
						gewonnen = 100;
					} else {
						gewonnen = -100;
					}
				}
			}
		}
		return gewonnen;
	}

	public int play(final String pAktuellerZug, final int tiefe, final Spieler pSpielerAmZug, final Spieler pSpieler,
			final List<Zug> pGewinnZuege) {
		int gewonnen = 0;
		List<Zug> zuege = this.getAlleZuege(pSpielerAmZug);
		int aktuelleZugNr = 1;
		int bewertungAktuellerZug = -1000;
		for (Zug zug : zuege) {
			String aktuellerZug = pAktuellerZug + "." + String.format("%d", aktuelleZugNr);
			Spielfeld neuesFeld = zug.doIt(this, pSpielerAmZug);
			gewonnen = this.gewonnen(pSpieler, null);
			System.out.println("Tiefe: " + String.format("%02d", tiefe) + "; Zug " + aktuellerZug + ": Spieler "
					+ pSpielerAmZug.getName() + " ist dran und zieht " + zug.toString() + "; gewonnen: " + gewonnen);
			if (gewonnen == 100) {
				pGewinnZuege.add(zug);
				break;
			}
			pGewinnZuege.add(zug);
			if (tiefe != 3) {
				// ändere die Spielerreihenfolge
				Spieler aktuellerSpieler = this.nextSpieler(pSpielerAmZug);
				gewonnen = this.play(aktuellerZug, tiefe + 1, aktuellerSpieler, pSpieler, pGewinnZuege);
				if (gewonnen == 100) {
					// break;
				}
				bewertungAktuellerZug = Math.max(bewertungAktuellerZug, gewonnen);
			}
			zug.undoIt(this);
			pGewinnZuege.remove(pGewinnZuege.size() - 1);
			++aktuelleZugNr;
		} // for
		System.out.println("Maximum der Bewertung: " + bewertungAktuellerZug);
		return gewonnen;
	}

	public void print() {
		// int i = 0;
		for (int i = this.SPIELFELD_LAENGE_SENKRECHT - 1; i >= 0; i--) {
			System.out.print("Zeile " + i + ":    ");
			for (int j = 0; j < this.SPIELFELD_LAENGE_WAAGRECHT; j++) {
				ESpielfigur currentField = this.getFeld(j, i);
				if (currentField == ESpielfigur.UNDEFINIERT) {
					System.out.print("-");
				} else if (currentField == ESpielfigur.ROT) {
					System.out.print("X");
				} else {
					System.out.print("O");
				}
				System.out.print("   ");
			}
			System.out.println("");
		}
		System.out.println("            0   1   2   3   4   5   6");
	}
}
