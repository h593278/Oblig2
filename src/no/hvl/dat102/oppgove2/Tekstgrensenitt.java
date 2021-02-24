package no.hvl.dat102.oppgove2;

import java.util.Scanner;

import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Tekstgrensenitt {// Klasse for inn/ut terminal
	// Hvis du vil lage meny, kan du også legge det inn i Tekstgrensesnitt
	// leser opplysningene om et medlem fra tastatur
	public static Medlem lesMedlem() {
		Scanner tastatur = new Scanner(System.in);

		System.out.print("Navn: ");
		String navn = tastatur.nextLine();

		KjedetMengde<Hobby> hobbyer = new KjedetMengde();
		hobbyer = LeggTilhobby(hobbyer);
		Medlem m = new Medlem(navn, hobbyer, -1);
		// skrivHobbyListe(m);
		return m;
	} // f.eks. bruke Scanner.

	// Skriver ut hobbylisten for et medlem
	public static void skrivHobbyListe(Medlem medlem) {
		// System.out.println("Alle hobbyene ");
		System.out.println(medlem.getHobbyer().toString());
	}

	public static KjedetMengde<Hobby> LeggTilhobby(KjedetMengde<Hobby> hobbyer) {

		Scanner tastatur = new Scanner(System.in);
		System.out.print("Navn på hobby: ");

		hobbyer.leggTil(new Hobby(tastatur.nextLine()));

		System.out.print("Tast 1 for flere hobbyer og 0 for avslutt ");
		int videre = tastatur.nextInt();
		if (videre == 1) {
			hobbyer = LeggTilhobby(hobbyer);
		}
		return hobbyer;
	}

	public static void skrivParListe(Datakontakt arkiv) {
		int antall= 0;
		System.out.println(" ");
		System.out.println("PARNAVN\t\tHOBBYER");
		System.out.println(" ");

		for (int i=0; i<arkiv.antall(); i++) {
			if (arkiv.getMedlemer()[i].getStatusIndeks()>i) {
				antall++;
				System.out.println(arkiv.getMedlemer()[arkiv.getMedlemer()[i].getStatusIndeks()].getNavn() + 
						" og " + arkiv.getMedlemer()[i].toString());
			}
		}
		System.out.println("Antall par funnet: " + antall);

		
		/*
		 * skriver ut på skjermen en oversikt over medlemmer som er koblet til hverandre
		 * i medlemstabellen til enhver tid. Et slikt par skal kun vises én gang på
		 * utskriftlisten. Metoden skriver også ut antall par som er funnet. }
		 */
	}

	public static void main(String[] args) {
		Datakontakt medlemer = new Datakontakt();
		Scanner tastatur = new Scanner(System.in);

		int videre = 1;
		// Legge til medlemer
		while (videre == 1) {
			medlemer.leggTilMedlem(lesMedlem());

			System.out.print("Vil du legge til fleire personer? trykk 1: ");
			videre = tastatur.nextInt();
		}

		// Legge til Partnerskap
		for (int i = 0; i < medlemer.antall(); i++) {
			String navn = medlemer.getMedlemer()[i].getNavn();
			medlemer.finnPartnerFor(navn);
		}
		skrivParListe(medlemer);
		
		System.out.println("Finn indeks med søk på namn:  ");
		tastatur.nextLine();
		String navn1 = tastatur.nextLine();
		System.out.println(medlemer.finnMedlemsIndeks(navn1));
		
		System.out.println("Namn på person du vil slette: ");
		String navn2 = tastatur.nextLine();
		medlemer.slettMedlem(navn2);
		
		skrivParListe(medlemer);
	}
}
