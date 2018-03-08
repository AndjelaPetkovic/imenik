package imenik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Imenik {
	
	/**
	 * Klasa Imenik. Ima samo jedan atribut, HashMapu gde su kljucevi
	 * objekti klase Osoba a vrednosti objekti klase Broj.
	 * Konstruktor nije definisan, te program sam pokrece podrazumevani
	 * konstruktor, koji u nasem slucaju samo inicijalizuje praznu
	 * HashMapu.
	 * Metode ove klase sluze za njeno lakse koriscenje, a String 
	 * reprenzentacija ispisuje sve kontakte koji se nalaze u imeniku 
	 * (dakle sve parove kljuc-vrednost).
	 */
	
	private HashMap<Osoba, Broj> imenik = new HashMap<>();
	
	public void dodajKontakt(Osoba o, Broj b) {
		imenik.put(o, b);
	}

	public Broj nadjiBroj(Osoba o) {
		return imenik.get(o);
	}
	
	public boolean sadrziOsobu(Osoba o) {
			return imenik.containsKey(o);
	}
	
	public String toString() {
		List<Osoba> osobe = new ArrayList<Osoba>(imenik.keySet());
		Collections.sort(osobe);
		StringBuilder sb = new StringBuilder();
		for (Osoba osoba : osobe) {
			sb.append(osoba + ", broj telefona: " + imenik.get(osoba) + "\n");
		}
		return sb.toString();		
	}
		
}
