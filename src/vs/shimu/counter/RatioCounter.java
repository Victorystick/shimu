package vs.shimu.counter;

import java.awt.Color;
import java.awt.Graphics2D;

public class RatioCounter implements Counter {
	
	int x,y;
	Counter c1,c2;

	public RatioCounter(int x, int y, Counter over, Counter under) {
		this.x = x;
		this.y = y;
		this.c1 = over;
		this.c2 = under;
	}

	@Override
	public void addTo(int add) {
		return;
	}

	@Override
	public int getCount() {
		return Math.round(c1.getCount()/(float)c2.getCount());
	}
	
	public float getRatio(){
		float res = roundTwoDecimals(c1.getCount()/(float)c2.getCount());
		return (res > 1500) ? 1500 : res;
	}

	@Override
	public void setCount(int count) {
		return;
	}

	@Override
	public Color getColor() {
		return Color.white;
	}
	
	public String getName() {
		return c1.getName() + "/" + c2.getName();
	}
	
	public String toString() {
//		return "Ratio: " + roundTwoDecimals(c1.getCount()/(float)c2.getCount());
		//return "" + Math.floor(c1.getCount()/(float)c2.getCount())
		return "" + Math.floor(getRatio());
	}
	
	float roundTwoDecimals(float f) {
		f=f*100;
		Math.round(f);
    	return f/100f;
}

	@Override
	public void render(Graphics2D g) {
		g.setColor(getColor());
		g.drawString(toString(), x, y);
	}

}
