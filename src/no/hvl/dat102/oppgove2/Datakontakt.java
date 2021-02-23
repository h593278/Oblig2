package no.hvl.dat102.oppgove2;

public class Datakontakt {
	private Medlem[] medlemer;
	private int antallMedlemmer;

	public void leggTilMedlem(Medlem person) {
		medlemer[antallMedlemmer] = person;
		antallMedlemmer++;
	}

	public boolean slettMedlem(String medlemsnavn) {
		int pos = finnMedlemsIndeks(medlemsnavn);
		//Kjekke om han finnes
		if (pos !=-1) {
			//Kjekke om han har ein ven, og fjerner han som venn
			int indeks = medlemer[pos].getStatusIndeks();
			if (indeks !=-1) {
				medlemer[indeks].setStatusIndeks(-1);
			}
			//Sletter den aktuelle personen
			antallMedlemmer--;			
			medlemer[pos]=medlemer[antallMedlemmer];
			medlemer[antallMedlemmer]=null;
			
			//Kjekker og endrere venen til den som blir flytta på
			int indeks2=medlemer[pos].getStatusIndeks();
			if (indeks2 !=-1) {
				medlemer[indeks2].setStatusIndeks(pos);
			}
			return true;
		}
		return false;
	}
	
	public int finnMedlemsIndeks(String medlemsnavn) {
		for (int i = 0; i < antallMedlemmer; i++) {
			if (medlemer[antallMedlemmer].getNavn().equals(medlemsnavn)) {
				return i;
			}
		}
		return -1;
	}

	public int finnPartnerFor(String medlemsnavn) {
		int pos = finnMedlemsIndeks(medlemsnavn);

		if (pos != -1 && medlemer[pos].getStatusIndeks() == -1) {
			for (int i = 0; i < antallMedlemmer; i++) {
				if (pos != i && medlemer[i].getStatusIndeks() == -1 && medlemer[pos].passerTil(medlemer[i])) {
					medlemer[pos].setStatusIndeks(i);
					medlemer[i].setStatusIndeks(pos);
					return i;
				}
			}
		}
		return -1;
	}
	
	public void tilbakestillStausIndeks(String medlemsnavn) {
		int pos = finnMedlemsIndeks(medlemsnavn);
		int indeks = medlemer[pos].getStatusIndeks();
		if (pos != -1 &&  indeks != -1) {
			medlemer[pos].setStatusIndeks(-1);
			medlemer[indeks].setStatusIndeks(-1);
		}
	}
}
