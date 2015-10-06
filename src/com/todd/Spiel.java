package com.todd;

import java.util.List;

import com.todd.Zug;

public class Spiel {

	private final int SPIEL_GEWINN = 100;
	private final int SPIEL_VERLUST = -100;

	public Spiel() {
	}

	public int play(final int pTiefe, final Spielfeld pFeld, final SpielerRing pSpielerRing,
			final Spieler pSpielerAmZug, final Spieler pBewerteterSpieler) {

		int maxGewonnen = 0;
		if (pTiefe <= 10) {
			if (pSpielerAmZug.equals(pBewerteterSpieler)) {
				maxGewonnen = this.SPIEL_VERLUST;
			} else {
				maxGewonnen = this.SPIEL_GEWINN;
			}
			List<Zug> zuege = pFeld.getAlleZuege(pSpielerAmZug);
			Zug besterZug = null;
			for (Zug zug : zuege) {
				Spielfeld neuesFeld = zug.doIt(pFeld, pSpielerAmZug);
				int gewonnen = neuesFeld.gewonnen(pSpielerAmZug, pBewerteterSpieler);
				// this.printZug(pTiefe, zug, gewonnen);

				if ((gewonnen == this.SPIEL_GEWINN) || (gewonnen == this.SPIEL_VERLUST)) {
					// gewonnen -> keine weitere Aktion mehr notwendig
					besterZug = zug;
				} else {
					Spieler naechsterSpieler = pSpielerRing.nextSpieler(pSpielerAmZug);
					gewonnen = this.play(pTiefe + 1, neuesFeld, pSpielerRing, naechsterSpieler, pBewerteterSpieler);
				}
				if (pSpielerAmZug.equals(pBewerteterSpieler)) {
					maxGewonnen = Math.max(maxGewonnen, gewonnen);
					if ((pTiefe == 1) && (gewonnen == this.SPIEL_GEWINN)) {
						System.out.println("Siegzug: " + zug.toString());
					}
				} else {
					maxGewonnen = Math.min(maxGewonnen, gewonnen);
				}
			} // for
				// this.printSummary(pTiefe, maxGewonnen, besterZug);
		}
		return maxGewonnen;
	}

	private void printSummary(final int pTiefe, final int pMaxGewonnen, final Zug pBesterZug) {
		this.indent(pTiefe);
		System.out.println(pTiefe + ") ====== max gewonnen: " + pMaxGewonnen + "; bester Zug: "
				+ ((pBesterZug != null) ? pBesterZug.toString() : "---"));
	}

	private void printZug(final int pTiefe, final Zug pZug, final int pGewonnen) {
		this.indent(pTiefe);
		System.out.println(pTiefe + ") Zug: " + pZug.toString() + " - gewonnen: " + pGewonnen);
	}

	private void indent(final int pTiefe) {
		for (int i = 0; i < pTiefe; i++) {
			System.out.print("    ");
		}
	}
}
