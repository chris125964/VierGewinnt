package test;

import org.junit.Test;

import com.todd.ESpielfigur;
import com.todd.Spieler;
import com.todd.Zug;

public class ZugUTest {

	@Test
	public void testGetSet() {
		Zug zug = new Zug(3, 5, new Spieler("Christian", ESpielfigur.GELB));

	}
}
