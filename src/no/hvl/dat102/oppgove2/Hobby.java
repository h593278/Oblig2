package no.hvl.dat102.oppgove2;
public class Hobby {
	private String hobbyNavn;

	public Hobby(String hobby) {
		hobbyNavn = hobby;
	}

	public String toString(){
		//� returnerer hobbynavnet med �<� foran og �>� bak som
		// String (Eksempel: <tegne, male>)
		// Bmrk: Kan ogs� ha uten parenteser, men fors�k med parenteser.
		return hobbyNavn;
	}
	
	public String getHobbyNavn() {
		return hobbyNavn;
	}
	
	public void setHobbyNavn(String hobbyNavn) {
		this.hobbyNavn=hobbyNavn;
	}

	public boolean equals(Object hobby2) {
		if (this==hobby2) 
			return true;
		if (hobby2==null)
			return false;
		if (hobby2.getClass()!=this.getClass())
			return false;
		// evntuelt fylle ut f�rst med "standard" kode
		// som vi ofte har med overkj�ring av
		// equals-metoden
		Hobby hobbyDenAndre = (Hobby) hobby2;
		return (hobbyNavn.equals(hobbyDenAndre.getHobbyNavn()));
	}
}// end Hobby