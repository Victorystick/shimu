package vs.shimu.counter;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.Entity;

public class PointsCounter implements Counter {
	
	private Entity owner;
	private int points;
	private int x;
	private int y;

	public PointsCounter(Entity owner, int positionX, int positionY) {
		x=positionX;
		y=positionY;
		this.owner = owner;
		points = 0;
	}
	
//	public String toString() {
//		return getName() + ": " + getCount();
//		
//	}
	
	public String getName() {
		return (!owner.toString().equals("Player")) ? owner.toString() : "Points";
	}
	
	public String toString(){
		//return "Points: " + points;
		return "" + points;
	}
	
	public void addTo(int add) {
		points += add;
	}

	@Override
	public int getCount() {
		return points;
	}

	@Override
	public void setCount(int count) {
		points = count;
	}
	
	public Color getColor() {
		return Color.white;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(getColor());
		g.drawString(toString(), x, y);
	}
}
