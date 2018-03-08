package imenik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	/**
	 * Main metoda aplikacije imenik. Sadrzi GUI i razne pomocne 
	 * funkcije.
	 * Po pokretanju programa otvara se prozor sa tri polja
	 * i nekoliko dugmadi. Moze se uneti ime, prezime i broj osobe
	 * koju zelite da sacuvate u imenik, pritiskanjem dugmeta 'Unesi'
	 * to mozete postici. Program proverava ispravnost podataka te ne 
	 * moze da se unesu brojevi kod imena ili tekst kod u polju za broj.
	 * Dugme 'Ocisti polja' brise sve ispisano, 'Listaj' sluzi da ispise
	 * sve kontakte sto se nalaze u imeniku, a ako zelite da nadjete neki
	 * kontakt po imenu unesete odgovarajuce podatke i pritisnete dugme
	 * 'Pretraga po imenu'.
	 */
	
	private static Imenik imenik = new Imenik();
	
	private static Label imeLb, prezLb, brojLb;
	private static TextField imeTf, prezTf, brojTf;
	private static Button unesiBtn, ocistiBtn, pretraziBtn, listajBtn;

	public static void main(String[] args) {

		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Imenik");
		VBox root = new VBox(5);
		createGui(root);
		root.setPadding(new Insets(5));
		
		Scene scene = new Scene(root, 400, 600);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public void createGui(VBox root) {
		
		imeLb = new Label("Ime:");	
		prezLb = new Label("Prezime: ");
		brojLb = new Label("Broj telefona: ");
		
		imeTf = new TextField();
		imeTf.setMaxWidth(Double.MAX_VALUE);
		prezTf = new TextField();
		prezTf.setMaxWidth(Double.MAX_VALUE);
		brojTf = new TextField();
		brojTf.setMaxWidth(Double.MAX_VALUE);
		
		unesiBtn = new Button("Unesi");
		unesiBtn.setMaxWidth(Double.MAX_VALUE);
		unesiBtn.setPrefHeight(40);
		ocistiBtn = new Button("Ocisti polja");
		ocistiBtn.setMaxWidth(Double.MAX_VALUE);
		ocistiBtn.setPrefHeight(40);
		pretraziBtn = new Button("Pretraga po imenu");
		pretraziBtn.setMaxWidth(Double.MAX_VALUE);
		pretraziBtn.setPrefHeight(40);
		listajBtn = new Button("Listaj");
		listajBtn.setMaxWidth(Double.MAX_VALUE);
		listajBtn.setPrefHeight(40);
		
		TextArea podaci = new TextArea();
		podaci.setEditable(false);
		
		Label poruka = new Label("");
		
		unesi(poruka);
		ocisti(podaci, poruka);
		pretrazi(podaci, poruka);
		listaj(podaci, poruka);
		
		root.getChildren().addAll(imeLb, imeTf, prezLb, prezTf, brojLb, brojTf,
								  unesiBtn, ocistiBtn, pretraziBtn, listajBtn,
								  podaci, poruka);
	}
	
	public void unesi(Label poruka) {
		
		unesiBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				poruka.setText("");
				String ime = imeTf.getText();
				String prezime = prezTf.getText();
				String brojDelovi = brojTf.getText();
				
				if (proveriText(ime) && proveriText(prezime) && proveriBroj(brojDelovi)) {
					Osoba osoba = new Osoba(ime, prezime);
				
					String[] ceoBroj = brojDelovi.split(" ");
					Broj broj = new Broj(ceoBroj[0], ceoBroj[1]);
					
					if (!(imenik.sadrziOsobu(osoba))) {
						imenik.dodajKontakt(osoba, broj);
						poruka.setTextFill(Color.PURPLE);
						poruka.setText(osoba + " je dodat-a u imenik.");
					}
					else {
						poruka.setTextFill(Color.BLACK);
						poruka.setText(osoba + " vec postoji u imeniku.");
					}
				}
				else {
					poruka.setTextFill(Color.RED);
					poruka.setText("Greska pri dodavanju.");
				}			
			}			
		});		
	}
	
	public boolean proveriText(String textZaProveru) {
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]+(\\s[A-Z]{1}[a-z]+)*");
		Matcher m = p.matcher(textZaProveru);
		
		return m.matches();
	}
	
	public boolean proveriBroj(String brojZaProveru) {
		Pattern p = Pattern.compile("\\d{3}\\s\\d{6,7}");
		Matcher m = p.matcher(brojZaProveru);
		
		return m.matches();		
	}
	
	public void ocisti(TextArea podaci, Label poruka) {
		
		ocistiBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				podaci.clear();
				poruka.setText("");
				imeTf.clear();
				prezTf.clear();
				brojTf.clear();				
			}			
		});		
	}
	
	public void pretrazi(TextArea podaci, Label poruka) {
		
		pretraziBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				brojTf.clear();
				poruka.setText("");
				String ime = imeTf.getText();
				String prezime = prezTf.getText();
								
				if (proveriText(ime) && proveriText(prezime)) {
						Osoba osoba = new Osoba(ime, prezime);
						
						if (imenik.sadrziOsobu(osoba)) {
							podaci.setText("Broj telefona osobe " + osoba + " je " + imenik.nadjiBroj(osoba));
						}
						else {
							poruka.setTextFill(Color.RED);
							poruka.setText("Osoba " + osoba + " nije u imeniku.");
						}
					}
				else {
					poruka.setTextFill(Color.RED);
					poruka.setText("Unesite ispavno podatke.");
				}
			}		
		});		
	}
	
	public void listaj(TextArea podaci, Label poruka) {
		
		listajBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				podaci.clear();
				poruka.setText("");
				imeTf.clear();
				prezTf.clear();
				brojTf.clear();	
				
				if (imenik.toString().equals("")) {
					poruka.setTextFill(Color.RED);
					poruka.setText("Imenik je prazan.");				
				}
				else {
					podaci.setText(imenik.toString());
				}			
			}			
		});		
	}
}
