package no.hvl.dat102.adt;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;

public abstract class MengdeADTTest {
	//Referanse til Mengde
	private MengdeADT<Integer> mengde1;
	private MengdeADT<Integer> mengde2;
	private MengdeADT<Integer> mengdeSvar;

	
	//Testdata
	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	private Integer e4 = 5;
	private Integer e5 = 6;
	private Integer e6 = 7;
	private Integer e7 = 8;



	protected abstract MengdeADT<Integer> reset();
	
	/**
	 * Henter ein ny mengde for kvar test.
	 * 
	 * @return
	 */
	@BeforeEach
	public void setuo() {
		mengde1 = reset();
		mengde2 = reset();
		mengdeSvar = reset();
	}
	
	/**
	 * Test på at en ny mengde er tom.
	 */
	@Test
	public void nyStabelErTom() {
		assertTrue(mengde1.erTom());
	}
	
	/**
	 * Tester på legTil og Fjern
	 */
	@Test
	public void legTilSlett() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		
		try {
			assertEquals(e0, mengde1.fjern(e0));
			assertEquals(e2, mengde1.fjern(e2));
			assertEquals(e3, mengde1.fjern(e3));
		} catch (EmptyCollectionException e) {
			fail("Fjern feilet uventet " + e.getMessage());
		}
	}
	
	/*
	 * Tester leggTilAlle
	 */
	@Test
	public void TestleggTilAlle() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
	
		mengdeSvar.leggTilAlle(mengde1);
		
		assertEquals(mengde1, mengdeSvar);
	}
	/*
	 * Tester union som er like
	 */
	@Test
	public void unionLike() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		
		mengde2.leggTil(e0);
		mengde2.leggTil(e1);
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		
		mengdeSvar.leggTilAlle(mengde1);
		mengdeSvar.leggTilAlle(mengde2);
		
		assertEquals(mengdeSvar,mengde1.union(mengde2));
	}
	
	/*
	 * Tester union som er ulike
	 */
	@Test
	public void unionUlike() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		mengde2.leggTil(e6);
		mengde2.leggTil(e7);
		
		mengdeSvar.leggTilAlle(mengde1);
		mengdeSvar.leggTilAlle(mengde2);
		
		assertEquals(mengdeSvar,mengde1.union(mengde2));
	}
	
	/*
	 * Tester Snitt som er like
	 */
	@Test
	public void snittLike() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		
		mengde2.leggTil(e0);
		mengde2.leggTil(e1);
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		
		mengdeSvar.leggTil(e0);
		mengdeSvar.leggTil(e1);
		
		assertTrue(mengdeSvar.equals(mengde1.snitt(mengde2)));
	}
	
	/*
	 * Tester snitt som er ulike
	 */
	@Test
	public void snittUlike() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		mengde2.leggTil(e6);
		mengde2.leggTil(e7);
		
		assertTrue(mengdeSvar.equals(mengde1.snitt(mengde2)));
	}
	
	/*
	 * Tester differanse som er ulike
	 */
	@Test
	public void differanseUlike() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		mengde2.leggTil(e6);
		mengde2.leggTil(e7);
		
		mengdeSvar.leggTilAlle(mengde1);
		
		assertTrue(mengdeSvar.equals(mengde1.differens(mengde2)));
	}
	
	/*
	 * Tester differanse som er like
	 */
	@Test
	public void diferanseLike() {
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		
		mengde2.leggTil(e0);
		mengde2.leggTil(e1);
		mengde2.leggTil(e4);
		mengde2.leggTil(e5);
		
		mengdeSvar.leggTil(e2);
		mengdeSvar.leggTil(e3);

		assertEquals(mengdeSvar,mengde1.differens(mengde2));
	}
}
