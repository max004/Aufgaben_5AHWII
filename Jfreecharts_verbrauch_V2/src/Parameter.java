
public class Parameter {

	private double pitch;
	private double d;
	private long startx;
	private long endx;
	
	public Parameter(double pitch, double d, long startx, long endx) {
		super();
		this.pitch = pitch;
		this.d = d;
		this.startx = startx;
		this.endx = endx;
	}

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

	public long getStartx() {
		return startx;
	}

	public void setStartx(long startx) {
		this.startx = startx;
	}

	public long getEndx() {
		return endx;
	}

	public void setEndx(long endx) {
		this.endx = endx;
	}

	@Override
	public String toString() {
		return "Parameter [pitch=" + pitch + ", d=" + d + ", startx=" + startx
				+ ", endx=" + endx + "]";
	}
	
	
	
	
	
	
}
