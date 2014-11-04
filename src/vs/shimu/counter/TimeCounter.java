package vs.shimu.counter;

import java.awt.Color;
import java.awt.Graphics2D;


public class TimeCounter implements Counter {
	
	private int x;
	private int y;
	
	public TimeCounter(int start, int x, int y) {
		this.x = x;
		this.y = y;
		time = start;
	}

	int time;

	@Override
	public void addTo(int add) {
		time+=add;
	}

	@Override
	public int getCount() {
		return time;
	}

	@Override
	public void setCount(int count) {
		time = count;
	}
	
	public Color getColor() {
		return Color.white;
	}
	
	public String toString() {
		return getName() + ": " + getCount();
	}
	
	public String getName() {
		return "Time";
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(getColor());
		g.drawString(toString(), x, y);
	}

}
