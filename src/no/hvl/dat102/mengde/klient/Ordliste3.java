package no.hvl.dat102.mengde.klient;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class Ordliste3 {

	public static void main(String[] args) {

		MengdeADT<String> ordListe1 = new KjedetMengde<String>();

		String[] ord1 = { "God", "dag", "Hans", "Hansen", "Hansaby", "Olsen", "Ole", "buss", "rute", "Bergen" };

		String[] ord2 = { "God", "Hei", "Eva", "Oslo", "Førde", "Olsen", };

		for (int i = 0; i < ord1.length; i++) {
			ordListe1.leggTil(ord1[i]);
		}

		MengdeADT<String> ordListe2 = new KjedetMengde<String>();

		for (int i = 0; i < ord2.length; i++) {
			ordListe2.leggTil(ord2[i]);
		}

		// Lager Differanse av de to ordlistene

		MengdeADT<String> ordListeDifferans = new KjedetMengde<String>();

		ordListeDifferans = ordListe1.differens(ordListe2);

		System.out.println("Utskrift av Differansen mellom m1/m2");
		String hentStreng = "";
		while (!ordListeDifferans.erTom()) {
			hentStreng = ordListeDifferans.fjernTilfeldig();
			System.out.println(hentStreng);
		}
		
		// Lager unionen av de to ordlistene
		MengdeADT<String> ordListeBegge = new KjedetMengde<String>();

		ordListeBegge = ordListe1.union(ordListe2);

		System.out.println("Utskrift av unionen av begge ordlistene");
		hentStreng = "";
		while (!ordListeBegge.erTom()) {
			hentStreng = ordListeBegge.fjernTilfeldig();
			System.out.println(hentStreng);
		}
		
		// Lager snitt av de to ordlistene
		MengdeADT<String> ordListeSnitt = new KjedetMengde<String>();

		ordListeSnitt = ordListe1.snitt(ordListe2);

		System.out.println("Utskrift av Snitt av begge ordlistene");
		hentStreng = "";
		while (!ordListeSnitt.erTom()) {
			hentStreng = ordListeSnitt.fjernTilfeldig();
			System.out.println(hentStreng);
		}
	}
}
