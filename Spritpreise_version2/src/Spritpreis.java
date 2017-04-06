
public class Spritpreis {
	
	int tankstelle;
	double preis;
	long utx;
	
	public Spritpreis(int tankstelle, double preis, long utx) {
		super();
		this.tankstelle = tankstelle;
		this.preis = preis;
		this.utx = utx;
	}

	public int getTankstelle() {
		return tankstelle;
	}

	public void setTankstelle(int tankstelle) {
		this.tankstelle = tankstelle;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public long getUtx() {
		return utx;
	}

	public void setUtx(long utx) {
		this.utx = utx;
	}

	@Override
	public String toString() {
		return "Spritpreis [tankstelle=" + tankstelle + ", preis=" + preis
				+ ", utx=" + utx + "]";
	}

	

}
