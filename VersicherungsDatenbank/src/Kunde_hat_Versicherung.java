
public class Kunde_hat_Versicherung {
	
	private int vid;
	private int svnr;
	private String von;
	private String bis;
	
	
	public Kunde_hat_Versicherung(int vid, int svnr, String von, String bis) {
		super();
		this.vid = vid;
		this.svnr = svnr;
		this.von = von;
		this.bis = bis;
	}

	@Override
	public String toString() {
		return "Kunde_hat_Versicherung [vid=" + vid + ", svnr=" + svnr
				+ ", von=" + von + ", bis=" + bis + "]";
	}
	
	

}
