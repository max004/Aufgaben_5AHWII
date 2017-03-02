
public class Kondition {
	
	private int kid;
	private int vid;
	private String gegenstand;
	
	public Kondition(int kid, int vid, String gegenstand){
		
		this.kid = kid;
		this.vid = vid;
		this.gegenstand = gegenstand;
		
	}

	@Override
	public String toString() {
		return "Kondition [kid=" + kid + ", vid=" + vid + ", gegenstand="
				+ gegenstand + "]";
	}
	
	

}
