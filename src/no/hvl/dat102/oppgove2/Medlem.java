package no.hvl.dat102.oppgove2;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	private String navn;
	private KjedetMengde<Hobby> hobbyer;
	private int statusIndeks;

	//… Konstruktør
	public Medlem(String navn, KjedetMengde<Hobby> hobbyer,int statusIndeks) {
		this.navn=navn;
		this.hobbyer=hobbyer;
		this.statusIndeks=statusIndeks;		//-1 om han ikkje er kobla
	}

	//… Andre metoder
	
	
	public boolean passerTil(Medlem medlem2) {
		return medlem2.getHobbyer().equals(getHobbyer());
	}
	
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public KjedetMengde<Hobby> getHobbyer() {
		return hobbyer;
	}

	public void setHobbyer(KjedetMengde<Hobby> hobbyer) {
		this.hobbyer = hobbyer;
	}

	public int getStatusIndeks() {
		return statusIndeks;
	}

	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}

	public String toString() {
		return navn + "\t<" + hobbyer.toString() + "> ";
	}
	
	
	public static void main(String[] args) {
		KjedetMengde<Hobby> hob = new KjedetMengde<>();
		KjedetMengde<Hobby> hobOdd = new KjedetMengde<>();

		Hobby hob1 = new Hobby("Summing");
		Hobby hob2 = new Hobby("Sykkling");
		Hobby hob3 = new Hobby("Skule");

		
		hob.leggTil(hob1);
		hob.leggTil(hob2);
		hob.leggTil(hob3);
		hobOdd.leggTil(hob1);
		
		
		Medlem ole = new Medlem("Ole", hob, 1);
		Medlem odd = new Medlem("odd", hob, 0);

		System.out.println(ole.toString());
		System.out.println(odd.toString());
		System.out.println(ole.passerTil(odd));


		//ole.passerTil(odd);
	}
}
