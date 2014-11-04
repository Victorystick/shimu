package vs.shimu.drawable;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Drawable {
	public Color getColor();
	public void render(Graphics2D g);
}
