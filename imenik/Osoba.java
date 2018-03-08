package imenik;

import java.util.Objects;

public class Osoba implements Comparable<Osoba>{
	
	/**
	 * Klasa Osoba, sluzi za pravljenje objekata Osoba, koji su kljucevi
	 * u HashMapi Imenik. 
	 * Posto sluze kao kljucevi metode equals i hashCode se moraju
	 * predefinisati za ovu klasu.
	 * Implementira interfejs Comparable, a objekti se sortiraju po
	 * prezimenu, potom po imenu. 
	 */
	
	private String ime, prezime;

	public Osoba(String ime, String prezime) {		
		this.ime = ime;
		this.prezime = prezime;	
	}

	public String getIme() {
		return ime;
	}
	public String getPrezime() {
		return prezime;
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}
	
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Osoba))
			return false;
		return compareTo((Osoba)obj) == 0;
	}

	@Override
	public int compareTo(Osoba o) {		
		int rez = prezime.compareTo(o.prezime);
		if (rez == 0)
			return ime.compareTo(o.ime);
		return rez;
	}
	
	public int hashCode() {
		return Objects.hash(prezime, ime);
	}	

}
