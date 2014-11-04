package vs.shimu.counter;

import java.awt.Color;
import java.awt.Graphics2D;

public class SimpleCounter implements Counter {
	
	int x,y;
	int count;
	String name;

	public SimpleCounter(int x, int y, String name, int startValue) {
		count = startValue;
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	@Override
	public void addTo(int add) {
		count=count+add;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public Color getColor() {
		return Color.white;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(getColor());
		g.drawString(getName() + ": " + count, x, y);
	}

}
