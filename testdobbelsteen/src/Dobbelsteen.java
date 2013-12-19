public class Dobbelsteen {
	private int worp, worp1, worp2;

	public Dobbelsteen() {
		worp = 0;
	}

	public int getWorp() {
		return worp;
	}

	public int gooi() {
		worp = (int) (6 * Math.random() + 1);
		return worp;
	}

	public int getWorp1() {
		return worp1;
	}

	public void setWorp1(int worp1) {
		this.worp1 = worp1;
	}
	
	public int getWorp2() {
		return worp2;
	}

	public void setWorp2(int worp2) {
		this.worp2 = worp2;
	}
}