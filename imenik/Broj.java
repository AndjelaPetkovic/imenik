package imenik;

public class Broj {
	
	/**
	 * Klasa Broj, pravi objekte koji sluzi kao vrednosti u HashMapi
	 * Imenik.
	 */
	
	private String pozivni, lokalni;

	public Broj(String pozivni, String lokalni) {		
		this.pozivni = pozivni;
		this.lokalni = lokalni;
	}

	public String getPozivni() {
		return pozivni;
	}
	public String getLokalni() {
		return lokalni;
	}
	
	public String toString() {
		return "(" + pozivni + ")" + lokalni;
	}

}
