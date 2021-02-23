package no.hvl.dat102.mengde.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat = null;

		int valg = rand.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;

		// Kjekke om det er det fuste elemente
		if (start.getElement().equals(element)) {
			resultat = start.getElement();
			start = start.getNeste();
			funnet = true;
			antall--;
		} else {
			forgjenger = start;
			aktuell = start.getNeste();
			for (int i = 1; i < antall && !funnet; i++) {
				if (aktuell.getElement().equals(element)) {
					resultat = aktuell.getElement();
					// Kjekke om det er det siste elemente
					if (aktuell.getNeste() == null) {
						forgjenger.setNeste(null);
					} else { // Om elementet er inni
						forgjenger.setNeste(aktuell.getNeste());
					}
					funnet = true;
					antall--;
				} else {
					forgjenger = aktuell;
					aktuell = aktuell.getNeste();
				}
			}
		}
		return resultat;
	}//

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		KjedetMengde<T> m2 = (KjedetMengde) obj;
		// KjedetMengde other = (KjedetMengde) obj;
		if (antall != m2.antall) // Antal elementer
			return false;
		KjedetIterator<T> iterator = new KjedetIterator<T>(start);
		// Kjekke om alle elementa er i this.
		while (iterator.hasNext()) {
			T v = iterator.next();
			if (!m2.inneholder(v)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new KjedetMengde<T>();
		LinearNode<T> aktuell = start;
		T element = null;

		// Legger inn alle elementene fra gjeldene mengde
		while (aktuell != null) {
			((KjedetMengde<T>) begge).settInn(aktuell.getElement());
			aktuell = aktuell.getNeste();
		}

		// Legge inn elementene fra m2 som ikkje alt finnes i mengden
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (!this.inneholder(element)) { // Kjekke om han alt finnes
				((KjedetMengde<T>) begge).settInn(element);
			}
		}
		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<T>();
		T element;
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (this.inneholder(element)) {
				((KjedetMengde<T>) snittM).settInn(element);
			}
		}
		/*
		 * 
		 * 
		 * if (this.inneholder(element)) ((KjedetMengde<T>) snittM).settInn(element);
		 */
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<T>();
		T element;
		Iterator<T> teller = this.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (!m2.inneholder(element)) {
				((KjedetMengde<T>) differensM).settInn(element);
			}
		}

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return erUnderMengde;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}
	
	public String toString() {
		LinearNode<T> aktuell = start;
		String svar = "";
		for (int i = 0; i < antall && aktuell!=null; i++) {
			svar += aktuell.getElement().toString();
			if (i<antall-1) {
				svar+= ", ";
			}
			aktuell = aktuell.getNeste();
		}
		return svar;
	}

}// class
