
public class Kunde {
	
	private int svnr;
	private String geburtsdatum;
	private String vorname;
	private String nachname;
	


	public Kunde(int svnr, String geburtsdatum, String vorname, String nachname) {
		
		super();
		this.svnr = svnr;
		this.geburtsdatum = geburtsdatum;
		this.vorname = vorname;
		this.nachname = nachname;
		
	}



	@Override
	public String toString() {
		return "Kunde [svnr=" + svnr + ", geburtsdatum=" + geburtsdatum
				+ ", vorname=" + vorname + ", nachname=" + nachname + "]";
	}
	

}
