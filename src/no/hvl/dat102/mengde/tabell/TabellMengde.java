package no.hvl.dat102.mengde.tabell;

import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.*;
import no.hvl.dat102.mengde.kjedet.KjedetIterator;

public class TabellMengde<T> implements MengdeADT<T> {
	// ADT-en Mengde implementert som tabell
	//
	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private int antall;
	private T[] tab;
	
	public TabellMengde() {
		this(STDK);
	}

	public TabellMengde(int start) {
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}
	
	private T[] getTab() {
		return tab;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tab.length) {
				utvidKapasitet();
			}
			tab[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		T svar = null;
		int indeks = tilf.nextInt(antall);
		svar = tab[indeks];
		tab[indeks] = tab[antall - 1];
		antall--;

		return svar;
	}

	@Override
	public T fjern(T element) {
		// Søker etter og fjerner element. Returnerer null-ref ved ikke-funn

		if (erTom())
			throw new EmptyCollectionException("mengde");
		
		boolean funnet = false;
		T svar = null;
		
		for (int i=0; i<antall && !funnet; i++) {
			if (tab[i].equals(element)) {
				svar=tab[i];
				funnet=true;
				
				antall--;
				tab[i]=tab[antall];
				tab[antall]=null;
			}
		}
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
			}
		}
		return (funnet);
	}

	@Override
	public boolean equals(Object m2) {
		//T element;
		
		if (this == m2)
			return true;
		if (m2 == null)
			return false;
		if (getClass() != m2.getClass())
			return false;
		
		TabellMengde<T> m3 =(TabellMengde) m2;
		//Kjekke antall
		if (antall()!=m3.antall()) {
			return false;
		} 
		TabellIterator<T> iterator = new TabellIterator<T>(tab, antall);
		while (iterator.hasNext()) {
			T element=iterator.next();
			if(!m3.inneholder(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	@Override

	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new TabellMengde<T>();
		T element = null;

		TabellIterator<T> iterator1 = new TabellIterator<T>(((TabellMengde<T>)m2).getTab(), m2.antall());
		while (iterator1.hasNext()) {
			element=iterator1.next();
			((TabellMengde<T>) begge).settInn(element);
		}
		//begge.leggTilAlle(m2);
		
		
		TabellIterator<T> iterator = new TabellIterator<T>(tab, antall);
		while (iterator.hasNext()) {
			element=iterator.next();
			if (!m2.inneholder(element))
			((TabellMengde<T>) begge).settInn(element);
		}
		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new TabellMengde<T>();
		T element = null;
		
		TabellIterator<T> iterator = new TabellIterator<T>(tab, antall);
		while (iterator.hasNext()) {
			element=iterator.next();
			if(m2.inneholder(element)) {
				((TabellMengde<T>) snittM).settInn(element);
			}
				
		}
		
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new TabellMengde<T>();
		T element;
		TabellIterator<T> iterator = new TabellIterator<T>(tab, antall);
		while (iterator.hasNext()) {
			element=iterator.next();
			if(!m2.inneholder(element)) {
				((TabellMengde<T>) differensM).settInn(element);
			}
				
		}
		/*
		 * Fyll ut
		 * 
		 * if (!m2.inneholder(element)) ((TabellMengde<T>) differensM).settInn(element);
		 */

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return false;
	}

	@Override
	public Iterator<T> oppramser() {
		return new TabellIterator<T>(tab, antall);
	}

	private void settInn(T element) {
		if (antall == tab.length) {
			utvidKapasitet();
		}
		tab[antall] = element;
		antall++;
	}
	
	public String toString() {
		String svar = "";
		for (int i=0; i<antall; i++) {
			svar+=tab[i]+ " ";
		}
		return svar;
	}

}// class
