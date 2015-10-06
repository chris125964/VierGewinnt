package com.todd;

import java.util.List;

import com.todd.Spiel;
import com.todd.Zug;

public class Main {

	public static void main(final String[] args) {

		System.out.println("Hier entsteht 4 gewinnt!");
		Spielfeld feld = new Spielfeld();
		Spieler spieler1 = new Spieler("Christian", ESpielfigur.ROT);
		Spieler spieler2 = new Spieler("Angelika", ESpielfigur.GELB);
		feld.add(spieler1);
		feld.add(spieler2);
		feld.setAktuellerSpieler(spieler1);
		feld.setFeld(0, 0, spieler2.getFarbe());
		feld.setFeld(2, 0, spieler1.getFarbe());
		feld.setFeld(2, 1, spieler2.getFarbe());
		feld.setFeld(3, 0, spieler1.getFarbe());
		feld.setFeld(3, 1, spieler2.getFarbe());
		feld.print();
		List<Zug> zuege = feld.getAlleZuege(spieler1);

		Spiel spiel = new Spiel();
		SpielerRing spielerRing = new SpielerRing();
		spielerRing.add(spieler1);
		spielerRing.add(spieler2);
		spiel.play(1, feld, spielerRing, spieler1, spieler1);
	}
}
