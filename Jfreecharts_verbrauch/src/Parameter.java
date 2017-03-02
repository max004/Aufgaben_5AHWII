
public class Parameter {

	private double pitch;
	private double d;
	public double getPitch() {
		return pitch;
	}
	public void setPitch(double pitch) {
		this.pitch = pitch;
	}
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	public Parameter(double pitch, double d) {
		super();
		this.pitch = pitch;
		this.d = d;
	}
	@Override
	public String toString() {
		return "Parameter [pitch=" + pitch + ", d=" + d + "]";
	}
	
	
	
}
