
public class Versicherung {
	
	private String gruendungsdatum;
	private String name;
	private int vid;
	
	public Versicherung(String gruendungsdatum, String name, int vid) {
		super();
		this.gruendungsdatum = gruendungsdatum;
		this.name = name;
		this.vid = vid;
	}

	@Override
	public String toString() {
		return "Versicherung [gruendungsdatum=" + gruendungsdatum + ", name="
				+ name + ", vid=" + vid + "]";
	}
	
	

}
