package vs.shimu.drawable;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.counter.PointsCounter;
import vs.shimu.counter.RatioCounter;

public class PlayerCounter implements Drawable {
	
	int x,y;
	PointsCounter pC;
	RatioCounter rC;
	
	public PlayerCounter(int x,int y, PointsCounter pointsCounter, RatioCounter ratioCounter) {
		this.x = x;
		this.y = y;
		pC = pointsCounter;
		rC = ratioCounter;
	}
	
	@Override
	public Color getColor() {
		return Color.WHITE;
	}
	
	public PointsCounter getPointsCounter() {
		return pC;
	}
	
	public RatioCounter getRatioCounter() {
		return rC;
	}
	
	public String toString() {
		return pC.toString() + ", " + rC.toString();
	}

	@Override
	public void render(Graphics2D g) {
		g.drawString(toString(), x, y);
	}

}
