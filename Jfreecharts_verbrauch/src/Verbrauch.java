
public class Verbrauch {
	
	private long utst;
	private double verbrauch;
	
	public Verbrauch(long utst, double verbrauch) {
		super();
		this.utst = utst;
		this.verbrauch = verbrauch;
	}

	public long getUtst() {
		return utst;
	}

	public void setUtst(long utst) {
		this.utst = utst;
	}

	public double getVerbrauch() {
		return verbrauch;
	}

	public void setVerbrauch(double verbrauch) {
		this.verbrauch = verbrauch;
	}
	
	public void isVerbrauch(Verbrauch verbrauch){
		
		this.utst = verbrauch.getUtst();
		this.verbrauch = verbrauch.getVerbrauch();
		
		
	}

	@Override
	public String toString() {
		return "Verbrauch [utst=" + utst + ", verbrauch=" + verbrauch + "]";
	}
	
	
	
	

}
